package springframework.msscbeerorder.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springframework.brewery.model.BeerOrderDto;
import springframework.brewery.model.BeerOrderPagedList;
import springframework.msscbeerorder.domain.BeerOrder;
import springframework.msscbeerorder.domain.BeerOrderStatusEnum;
import springframework.msscbeerorder.domain.Customer;
import springframework.msscbeerorder.repositories.BeerOrderRepository;
import springframework.msscbeerorder.repositories.CustomerRepository;
import springframework.msscbeerorder.web.mappers.BeerOrderMapper;

import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BeerOrderServiceImpl implements BeerOrderService {

	private final BeerOrderRepository beerOrderRepository;
	private final CustomerRepository customerRepository;
	private final BeerOrderMapper beerOrderMapper;
	private final ApplicationEventPublisher publisher;

	@Override
	public BeerOrderPagedList listOrders(UUID customerId, Pageable pageable) {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new RuntimeException("Customer Not Found"));
		Page<BeerOrder> beerOrderPage = beerOrderRepository.findAllByCustomer(customer, pageable);

		return new BeerOrderPagedList(beerOrderPage.stream()
				.map(beerOrderMapper::beerOrderToDto)
				.collect(Collectors.toList()), PageRequest.of(beerOrderPage.getPageable()
				.getPageNumber(), beerOrderPage.getPageable()
				.getPageSize()), beerOrderPage.getTotalElements());
	}

	@Transactional
	@Override
	public BeerOrderDto placeOrder(UUID customerId, BeerOrderDto beerOrderDto) {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new RuntimeException("Customer Not Found"));
		BeerOrder beerOrder = beerOrderMapper.dtoToBeerOrder(beerOrderDto);
		beerOrder.setCustomer(customer);
		beerOrder.getBeerOrderLines()
				.forEach(line -> line.setBeerOrder(beerOrder));

		BeerOrder savedBeerOrder = beerOrderRepository.saveAndFlush(beerOrder);

		log.debug("Saved Beer Order: " + beerOrder.getId());

		// todo impl
		//  publisher.publishEvent(new NewBeerOrderEvent(savedBeerOrder));

		return beerOrderMapper.beerOrderToDto(savedBeerOrder);
	}

	@Override
	public BeerOrderDto getOrderById(UUID customerId, UUID orderId) {
		return beerOrderMapper.beerOrderToDto(getOrder(customerId, orderId));
	}

	@Override
	@Transactional
	public void pickupOrder(UUID customerId, UUID orderId) {
		BeerOrder beerOrder = getOrder(customerId, orderId);
		beerOrder.setOrderStatus(BeerOrderStatusEnum.PICKED_UP);
		beerOrderRepository.save(beerOrder);
	}

	private BeerOrder getOrder(UUID customerId, UUID orderId) {
		return beerOrderRepository.findByIdAndCustomer_Id(orderId, customerId)
				.orElseThrow(() -> new RuntimeException("Order Not Found"));
	}
}
