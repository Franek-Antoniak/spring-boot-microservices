package springframework.msscbeerservice.services;

import org.springframework.data.domain.PageRequest;
import springframework.msscbeerservice.web.model.BeerDto;
import springframework.msscbeerservice.web.model.BeerPagedList;
import springframework.msscbeerservice.web.model.BeerStyleEnum;

import java.util.UUID;


public interface BeerService {
	BeerDto getBeerById(UUID beerId, Boolean showInventoryOnHand);

	BeerDto saveNewBeer(BeerDto beerDto);

	BeerDto updateBeer(UUID beerId, BeerDto beerDto);

	BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest of, Boolean showInventoryOnHand);
}
