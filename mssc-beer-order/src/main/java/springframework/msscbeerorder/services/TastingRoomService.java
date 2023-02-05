package springframework.msscbeerorder.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springframework.msscbeerorder.domain.Customer;
import springframework.msscbeerorder.repositories.CustomerRepository;
import springframework.msscbeerorder.web.model.BeerOrderDto;
import springframework.msscbeerorder.web.model.BeerOrderLineDto;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import static springframework.msscbeerorder.bootstrap.BeerOrderBootStrap.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class TastingRoomService {

	private final CustomerRepository customerRepository;
	private final BeerOrderService beerOrderService;
	private final List<String> beerUpcs = List.of(BEER_1_UPC, BEER_2_UPC, BEER_3_UPC);

	@Transactional
	@Scheduled(fixedRate = 2000) //run every 2 seconds
	public void placeTastingRoomOrder() {

		List<Customer> customerList = customerRepository.findAllByCustomerNameLike(TASTING_ROOM);

		if (customerList.size() == 1) { //should be just one
			doPlaceOrder(customerList.get(0));
		} else {
			log.error("Too many or too few tasting room customers found");
		}
	}

	private void doPlaceOrder(Customer customer) {
		String beerUpcToOrder = getRandomBeerUpc();

		BeerOrderLineDto beerOrderLine = BeerOrderLineDto.builder()
				.upc(beerUpcToOrder)
				.orderQuantity(new Random().nextInt(6)) //todo externalize value to property
				.build();

		List<BeerOrderLineDto> beerOrderLineList = List.of(beerOrderLine);

		BeerOrderDto beerOrder = BeerOrderDto.builder()
				.customerId(customer.getId())
				.customerRef(UUID.randomUUID()
						.toString())
				.beerOrderLines(beerOrderLineList)
				.build();

		beerOrderService.placeOrder(customer.getId(), beerOrder);
	}

	private String getRandomBeerUpc() {
		return beerUpcs.get(new Random().nextInt(beerUpcs.size()));
	}
}
