package springframework.msscbeerclient.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import springframework.msscbeerclient.model.BeerDto;

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
}
