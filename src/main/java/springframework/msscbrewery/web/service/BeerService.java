package springframework.msscbrewery.web.service;

import springframework.msscbrewery.web.model.BeerDto;

import java.util.UUID;

public interface BeerService {
	BeerDto getBeerById(UUID beerId);

	BeerDto saveNewBeer(BeerDto beerDto);
}
