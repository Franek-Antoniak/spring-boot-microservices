package springframework.msscbeerclient.web.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import springframework.msscbeerclient.web.model.BeerDto;
import springframework.msscbeerclient.web.model.CustomerDto;

import java.net.URI;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BreweryClient {
	public final String BEER_PATH_V1 = "/api/v1/beer/";
	private final String CUSTOMER_PATH_V1 = "/api/v1/customer/";
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

	public CustomerDto getCustomerById(UUID uuid) {
		return restTemplate.getForObject(apihost + CUSTOMER_PATH_V1 + uuid.toString(), CustomerDto.class);
	}

	public URI saveNewCustomer(CustomerDto customerDto) {
		return restTemplate.postForLocation(apihost + CUSTOMER_PATH_V1, customerDto);
	}

	public void updateCustomer(UUID uuid, CustomerDto customerDto) {
		restTemplate.put(apihost + CUSTOMER_PATH_V1 + "/" + uuid.toString(), customerDto);
	}

	public void deleteCustomer(UUID uuid) {
		restTemplate.delete(apihost + CUSTOMER_PATH_V1 + "/" + uuid.toString());
	}
}
