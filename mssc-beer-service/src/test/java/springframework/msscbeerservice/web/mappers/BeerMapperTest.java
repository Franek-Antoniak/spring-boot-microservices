package springframework.msscbeerservice.web.mappers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import springframework.msscbeerservice.domain.Beer;
import springframework.msscbeerservice.web.model.BeerDto;
import springframework.msscbeerservice.web.model.BeerStyleEnum;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Currency;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BeerMapperTest {
	BeerMapper beerMapper = BeerMapper.INSTANCE;

	BeerDto beerDto;
	Beer beer;

	@BeforeEach
	void setUp() {
		beerDto = BeerDto.builder()
				.id(UUID.randomUUID())
				.beerName("Beer1")
				.beerStyle(BeerStyleEnum.valueOf("PALE_ALE"))
				.upc(123123123123L)
				.price(BigDecimal.valueOf(12.99))
				.quantityOnHand(200)
				.createdDate(OffsetDateTime.now())
				.lastModifiedDate(OffsetDateTime.now())
				.version(1)
				.currency(Currency.getInstance("USD"))
				.build();

		beer = Beer.builder()
				.id(beerDto.getId())
				.beerName("Beer1")
				.beerStyle(BeerStyleEnum.valueOf("PALE_ALE"))
				.upc(123123123123L)
				.price(BigDecimal.valueOf(12.99))
				.quantityToBrew(200)
				.createdDate(beerDto.getCreatedDate())
				.lastModifiedDate(beerDto.getLastModifiedDate())
				.version(1)
				.currency(Currency.getInstance("USD"))
				.build();
	}

	@Test
	void beerToBeerDto() {
		BeerDto expected = beerMapper.beerToBeerDto(beer);

		assertEquals(expected.getId(), beerDto.getId());
		assertEquals(expected.getBeerName(), beerDto.getBeerName());
		assertEquals(expected.getBeerStyle(), beerDto.getBeerStyle());
		assertEquals(expected.getUpc(), beerDto.getUpc());
		assertEquals(expected.getPrice(), beerDto.getPrice());
		assertEquals(expected.getCreatedDate(), beerDto.getCreatedDate());
		assertEquals(expected.getLastModifiedDate(), beerDto.getLastModifiedDate());
		assertEquals(expected.getVersion(), beerDto.getVersion());
		assertEquals(expected.getCurrency(), beerDto.getCurrency());
	}

	@Test
	void beerDtoToBeer() {
		Beer expected = beerMapper.beerDtoToBeer(beerDto);

		assertEquals(expected.getId(), beer.getId());
		assertEquals(expected.getBeerName(), beer.getBeerName());
		assertEquals(expected.getBeerStyle(), beer.getBeerStyle());
		assertEquals(expected.getUpc(), beer.getUpc());
		assertEquals(expected.getPrice(), beer.getPrice());
		assertEquals(expected.getCreatedDate(), beer.getCreatedDate());
		assertEquals(expected.getLastModifiedDate(), beer.getLastModifiedDate());
		assertEquals(expected.getVersion(), beer.getVersion());
		assertEquals(expected.getCurrency(), beer.getCurrency());
	}
}