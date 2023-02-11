package springframework.msscbeerorder.services;

import springframework.msscbeerorder.domain.BeerOrder;

public interface BeerOrderManager {
	BeerOrder newBeerOrder(BeerOrder beerOrder);
}
