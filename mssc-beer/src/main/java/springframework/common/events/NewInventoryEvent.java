package springframework.common.events;

import lombok.NoArgsConstructor;
import springframework.msscbeer.web.model.BeerDto;

@NoArgsConstructor
public class NewInventoryEvent extends BeerEvent {
	public NewInventoryEvent(BeerDto beerDto) {
		super(beerDto);
	}
}
