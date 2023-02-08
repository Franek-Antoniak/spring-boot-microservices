package springframework.msscbeer.events;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import springframework.msscbeer.web.model.BeerDto;

import java.io.Serializable;

@Data
@Builder
@RequiredArgsConstructor
public class BeerEvent implements Serializable {
	private final BeerDto beerDto;
	private final long serialVersionUID = 1694761522409927845L;
}
