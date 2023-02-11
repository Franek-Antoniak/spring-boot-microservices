package springframework.msscbeerorder.services;

import springframework.msscbeerorder.domain.BeerOrder;

import java.util.UUID;

public interface BeerOrderManager {
	BeerOrder newBeerOrder(BeerOrder beerOrder);

	void processValidationResult(UUID beerOrderId, Boolean isValid);
}
