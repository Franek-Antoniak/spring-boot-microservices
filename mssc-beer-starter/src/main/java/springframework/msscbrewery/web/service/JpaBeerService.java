package springframework.msscbrewery.web.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import springframework.msscbrewery.web.model.BeerDto;

import java.util.UUID;

@Service
@Slf4j
class JpaBeerService implements BeerService {
	@Override
	public BeerDto getBeerById(UUID beerId) {
		return BeerDto.builder()
				.id(UUID.randomUUID())
				.beerName("Galaxy Cat")
				.beerStyle("Pale Ale")
				.build();
	}

	@Override
	public BeerDto saveNewBeer(BeerDto beerDto) {
		return BeerDto.builder()
				.id(UUID.randomUUID())
				.build();
	}

	@Override
	public void updateBeer(UUID beerId, BeerDto beerDto) {
		//todo impl - would add a real impl to update beer
	}

	@Override
	public void deleteById(UUID beerId) {
		log.debug("Deleting a beer...");
	}
}
