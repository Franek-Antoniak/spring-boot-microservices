package springframework.brewery.model.brewery.model.events;

import lombok.NoArgsConstructor;
import springframework.brewery.model.brewery.model.BeerDto;

@NoArgsConstructor
public class BrewBeerEvent extends BeerEvent {
	public BrewBeerEvent(BeerDto beerDto) {
		super(beerDto);
	}
}
