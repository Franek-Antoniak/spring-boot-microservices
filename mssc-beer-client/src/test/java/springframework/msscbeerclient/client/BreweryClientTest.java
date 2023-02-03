package springframework.msscbeerclient.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import springframework.msscbeerclient.model.BeerDto;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BreweryClientTest {
	@Autowired
	BreweryClient breweryClient;

	@Test
	void getBeerById() {
		BeerDto beerDto = breweryClient.getBeerById(UUID.randomUUID());

		assertNotNull(beerDto);
	}
}