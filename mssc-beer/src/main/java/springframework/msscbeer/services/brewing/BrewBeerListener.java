package springframework.msscbeer.services.brewing;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springframework.brewery.model.BeerDto;
import springframework.brewery.model.events.BrewBeerEvent;
import springframework.brewery.model.events.NewInventoryEvent;
import springframework.msscbeer.domain.Beer;
import springframework.msscbeer.repository.BeerRepository;
import springframework.msscbeer.web.controller.NotFoundException;

import static springframework.msscbeer.config.JmsConfig.BREWING_REQUEST_QUEUE;
import static springframework.msscbeer.config.JmsConfig.NEW_INVENTORY_QUEUE;

@Service
@RequiredArgsConstructor
@Slf4j
public class BrewBeerListener {
	private final BeerRepository beerRepository;
	private final JmsTemplate jmsTemplate;

	@Transactional
	@JmsListener(destination = BREWING_REQUEST_QUEUE)
	public void listen(BrewBeerEvent brewBeerEvent) {
		BeerDto beerDto = brewBeerEvent.getBeerDto();
		Beer beer = beerRepository.findById(beerDto.getId())
		                          .orElseThrow(NotFoundException::new);
		beerDto.setQuantityOnHand(beer.getQuantityToBrew());
		NewInventoryEvent newInventoryEvent = new NewInventoryEvent(beerDto);

		log.debug("Brewed beer " + beer.getMinOnHand() + " : QOH: " + beerDto.getQuantityOnHand());

		jmsTemplate.convertAndSend(NEW_INVENTORY_QUEUE, newInventoryEvent);
	}
}
