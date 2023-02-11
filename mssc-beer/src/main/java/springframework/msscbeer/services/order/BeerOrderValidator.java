package springframework.msscbeer.services.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import springframework.brewery.model.BeerOrderDto;
import springframework.msscbeer.repository.BeerRepository;

import java.util.concurrent.atomic.AtomicInteger;

@Component
@RequiredArgsConstructor
@Slf4j
public class BeerOrderValidator {
	private final BeerRepository beerRepository;

	public Boolean validateOrder(BeerOrderDto beerOrderDto) {
		AtomicInteger beersNotFound = new AtomicInteger();

		beerOrderDto.getBeerOrderLines()
		            .forEach(orderLine -> {
			            if (beerRepository.findByUpc(orderLine.getUpc())
			                              .isEmpty()) {
				            beersNotFound.incrementAndGet();
			            }
		            });
		return beersNotFound.get() == 0;
	}

}
