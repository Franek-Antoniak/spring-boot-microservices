package springframework.msscbeerclient.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import springframework.msscbeerclient.web.client.BreweryClient;
import springframework.msscbeerclient.web.model.BeerDto;
import springframework.msscbeerclient.web.model.CustomerDto;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BreweryClientTest {
	@Autowired BreweryClient breweryClient;

	@Test
	void getBeerById() {
		BeerDto beerDto = breweryClient.getBeerById(UUID.randomUUID());

		assertNotNull(beerDto);
	}

	@Test
	void saveNewBeer() {
		BeerDto beerDto = BeerDto.builder().beerName("New Beer").build();

		URI uri = breweryClient.saveNewBeer(beerDto);

		assertNotNull(uri);
	}

	@Test
	void updateBeer() {
		BeerDto beerDto = BeerDto.builder().beerName("New Beer").build();

		breweryClient.updateBeer(UUID.randomUUID(), beerDto);
	}

	@Test
	void deleteBeer() {
		breweryClient.deleteBeer(UUID.randomUUID());
	}

	@Test
	void getCustomerById() {
		CustomerDto customerDto = breweryClient.getCustomerById(UUID.randomUUID());

		assertNotNull(customerDto);
	}

	@Test
	void saveNewCustomer() {
		CustomerDto customerDto = CustomerDto.builder().name("New Customer").build();

		URI uri = breweryClient.saveNewCustomer(customerDto);

		assertNotNull(uri);
	}

	@Test
	void updateCustomer() {
		CustomerDto customerDto = CustomerDto.builder().name("New Customer").build();

		breweryClient.updateCustomer(UUID.randomUUID(), customerDto);
	}

	@Test
	void deleteCustomer() {
		breweryClient.deleteCustomer(UUID.randomUUID());
	}
}