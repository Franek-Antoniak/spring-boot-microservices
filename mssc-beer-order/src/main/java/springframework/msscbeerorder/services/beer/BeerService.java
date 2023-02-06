package springframework.msscbeerorder.services.beer;

import springframework.msscbeerorder.web.model.BeerDto;

import java.util.UUID;

public interface BeerService {
	BeerDto getBeerByUpc(String upc);

	BeerDto getBeerById(UUID beerId);
}
