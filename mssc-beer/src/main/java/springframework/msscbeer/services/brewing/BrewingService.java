package springframework.msscbeer.services.brewing;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import springframework.brewery.model.events.BrewBeerEvent;
import springframework.msscbeer.domain.Beer;
import springframework.msscbeer.repository.BeerRepository;
import springframework.msscbeer.services.inventory.BeerInventoryService;
import springframework.msscbeer.web.mappers.BeerMapper;

import java.util.List;

import static springframework.msscbeer.config.JmsConfig.BREWING_REQUEST_QUEUE;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrewingService {
	private final BeerMapper beerMapper;
	private final BeerRepository beerRepository;
	private final BeerInventoryService beerInventoryService;
	private final JmsTemplate jmsTemplate;

	@Scheduled(fixedRate = 5000)
	public void checkForLowInventory() {
		List<Beer> beers = beerRepository.findAll();

		beers.forEach(beer -> {
			Integer invQOH = beerInventoryService.getOnHandInventory(beer.getId());

			log.debug("Min on hand is: " + beer.getMinOnHand());
			log.debug("Inventory is: " + invQOH);

			if (beer.getMinOnHand() >= invQOH) {
				jmsTemplate.convertAndSend(BREWING_REQUEST_QUEUE, new BrewBeerEvent(beerMapper.beerToBeerDto(beer)));
			}
		});
	}
}
