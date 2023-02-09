package springframework.msscbeer.events;

import springframework.msscbeer.web.model.BeerDto;

public class BrewBeerEvent extends BeerEvent {
	public BrewBeerEvent(BeerDto beerDto) {
		super(beerDto);
	}
}
