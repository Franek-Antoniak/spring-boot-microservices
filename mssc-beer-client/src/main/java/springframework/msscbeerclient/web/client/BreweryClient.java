package springframework.msscbeerclient.web.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import springframework.msscbeerclient.web.model.BeerDto;

import java.net.URI;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BreweryClient {
	public final String BEER_PATH_V1 = "/api/v1/beer/";
	private final RestTemplate restTemplate;
	private final String apihost;

	public BeerDto getBeerById(UUID uuid) {
		return restTemplate.getForObject(apihost + BEER_PATH_V1 + uuid.toString(), BeerDto.class);
	}

	public URI saveNewBeer(BeerDto beerDto) {
		return restTemplate.postForLocation(apihost + BEER_PATH_V1, beerDto);
	}

	public void updateBeer(UUID uuid, BeerDto beerDto) {
		restTemplate.put(apihost + BEER_PATH_V1 + "/" + uuid.toString(), beerDto);
	}

	public void deleteBeer(UUID uuid) {
		restTemplate.delete(apihost + BEER_PATH_V1 + "/" + uuid.toString());
	}
}
