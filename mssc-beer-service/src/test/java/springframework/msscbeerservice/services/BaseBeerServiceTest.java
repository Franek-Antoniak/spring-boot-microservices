package springframework.msscbeerservice.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import springframework.msscbeerservice.repository.BeerRepository;

@ExtendWith(MockitoExtension.class)
		// todo unit tests, yet classes are without any business logic
class BaseBeerServiceTest {
	@Mock
	private BeerRepository beerRepository;

	@InjectMocks
	private BaseBeerService baseBeerService;

	@BeforeEach
	void setUp() {
		// todo
	}

	@Test
	void getBeerById() {
		// given

		// when


		// then

	}

	@Test
	void saveNewBeer() {
		// given

		// when

		// then
	}

	@Test
	void updateBeer() {
		// given

		// when

		// then
	}
}