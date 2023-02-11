package springframework.msscbeerorder.services.beer;


import springframework.brewery.model.BeerDto;

import java.util.UUID;

public interface BeerService {
	BeerDto getBeerByUpc(String upc);

	BeerDto getBeerById(UUID beerId);
}
