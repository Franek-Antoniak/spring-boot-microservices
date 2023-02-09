package springframework.msscbeer.events;

import springframework.msscbeer.web.model.BeerDto;


public class NewInventoryEvent extends BeerEvent {
	public NewInventoryEvent(BeerDto beerDto) {
		super(beerDto);
	}
}
