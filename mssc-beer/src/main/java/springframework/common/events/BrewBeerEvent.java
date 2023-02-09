package springframework.common.events;

import lombok.NoArgsConstructor;
import springframework.msscbeer.web.model.BeerDto;

@NoArgsConstructor
public class BrewBeerEvent extends BeerEvent {
	public BrewBeerEvent(BeerDto beerDto) {
		super(beerDto);
	}
}
