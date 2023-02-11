package springframework.msscbeer.services;

import org.springframework.data.domain.PageRequest;
import springframework.brewery.model.BeerDto;
import springframework.brewery.model.BeerPagedList;
import springframework.msscbeer.domain.BeerStyleEnum;

import java.util.UUID;


public interface BeerService {
	BeerDto getBeerById(UUID beerId, Boolean showInventoryOnHand);

	BeerDto saveNewBeer(BeerDto beerDto);

	BeerDto updateBeer(UUID beerId, BeerDto beerDto);

	BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest of, Boolean showInventoryOnHand);

	BeerDto getBeerByUpc(String upc, Boolean showInventoryOnHand);
}
