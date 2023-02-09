package springframework.msscbeerinventory.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import springframework.common.events.NewInventoryEvent;
import springframework.msscbeerinventory.domain.BeerInventory;
import springframework.msscbeerinventory.repositories.BeerInventoryRepository;

import static springframework.msscbeerinventory.config.JmsConfig.NEW_INVENTORY_QUEUE;

@Slf4j
@Component
@RequiredArgsConstructor
public class NewInventoryListener {
	private final BeerInventoryRepository beerInventoryRepository;

	@JmsListener(destination = NEW_INVENTORY_QUEUE)
	public void listen(NewInventoryEvent event) {
		log.debug("Got Inventory: " + event.toString());

		beerInventoryRepository.save(BeerInventory.builder()
		                                          .beerId(event.getBeerDto()
		                                                       .getId())
		                                          .upc(event.getBeerDto()
		                                                    .getUpc())
		                                          .quantityOnHand(event.getBeerDto()
		                                                               .getQuantityOnHand())
		                                          .build());
	}
}
