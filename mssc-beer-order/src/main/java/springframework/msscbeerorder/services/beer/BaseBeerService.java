package springframework.msscbeerorder.services.beer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import springframework.msscbeerorder.web.model.BeerDto;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class BaseBeerService implements BeerService {
	private final String BEER_PATH_V1 = "/api/v1/beer/";
	private final String BEER_UPC_PATH_V1 = "/api/v1/beer/upc/";
	private final RestTemplate restTemplate;
	private final String beerServiceHost;

	@Override
	public BeerDto getBeerByUpc(String upc) {
		return restTemplate.getForObject(beerServiceHost + BEER_UPC_PATH_V1 + upc, BeerDto.class);
	}

	@Override
	public BeerDto getBeerById(UUID beerId) {
		return restTemplate.getForObject(beerServiceHost + BEER_PATH_V1 + beerId, BeerDto.class);
	}
}
