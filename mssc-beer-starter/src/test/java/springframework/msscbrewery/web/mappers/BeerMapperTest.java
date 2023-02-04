package springframework.msscbrewery.web.mappers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import springframework.msscbrewery.web.domain.Beer;
import springframework.msscbrewery.web.model.BeerDto;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BeerMapperTest {
	BeerMapper beerMapper = BeerMapper.INSTANCE;
	BeerDto beerDto;
	Beer beer;

	@BeforeEach
	void setUp() {
		beerDto = BeerDto.builder()
				.id(UUID.randomUUID())
				.beerName("BeerName")
				.beerStyle("Ale")
				.upc(123456789012L)
				.build();

		beer = Beer.builder()
				.id(beerDto.getId())
				.beerName("BeerName")
				.beerStyle("Ale")
				.upc(123456789012L)
				.build();
	}

	@Test
	void beerToBeerDto() {
		Beer beerExpected = beerMapper.beerDtoToBeer(beerDto);

		assertEquals(beerExpected, beer);
	}

	@Test
	void beerDtoToBeer() {
		BeerDto beerDtoExpected = beerMapper.beerToBeerDto(beer);

		assertEquals(beerDtoExpected, beerDto);
	}
}