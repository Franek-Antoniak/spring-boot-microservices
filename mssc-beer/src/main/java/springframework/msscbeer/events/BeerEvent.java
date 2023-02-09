package springframework.msscbeer.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import springframework.msscbeer.web.model.BeerDto;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeerEvent implements Serializable {
	private final long serialVersionUID = 1694761522409927845L;
	private BeerDto beerDto;
}
