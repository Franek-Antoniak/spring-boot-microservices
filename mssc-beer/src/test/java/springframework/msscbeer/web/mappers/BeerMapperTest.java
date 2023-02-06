package springframework.msscbeer.web.mappers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import springframework.msscbeer.domain.Beer;
import springframework.msscbeer.web.model.BeerDto;
import springframework.msscbeer.web.model.BeerStyleEnum;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class BeerMapperTest {
	@Autowired
	@Qualifier("delegate")
	BeerMapper beerMapper;

	BeerDto beerDto;
	Beer beer;

	@BeforeEach
	void setUp() {
		beerDto = BeerDto.builder()
		                 .id(UUID.randomUUID())
		                 .beerName("Beer1")
		                 .beerStyle(BeerStyleEnum.valueOf("PALE_ALE"))
				.upc("123123123123L")
				.price(BigDecimal.valueOf(12.99))
				.quantityOnHand(200)
				.createdDate(OffsetDateTime.now())
				.lastModifiedDate(OffsetDateTime.now())
				.version(1)
				.build();

		beer = Beer.builder()
				.id(beerDto.getId())
				.beerName("Beer1")
				.beerStyle(BeerStyleEnum.valueOf("PALE_ALE"))
				.upc("123123123123L")
				.price(BigDecimal.valueOf(12.99))
				.quantityToBrew(200)
				.createdDate(beerDto.getCreatedDate())
				.lastModifiedDate(beerDto.getLastModifiedDate())
				.version(1)
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
	}

	@Test
	void updateBeerFromDto() {
		beerDto.setQuantityOnHand(beerDto.getQuantityOnHand() + 100);
		beerDto.setLastModifiedDate(beerDto.getLastModifiedDate()
				.plusDays(1));
		Beer expected = beerMapper.updateBeerFromDto(beerDto, beer);

		assertEquals(expected.getId(), beer.getId());
		assertEquals(expected.getBeerName(), beerDto.getBeerName());
		assertEquals(expected.getBeerStyle(), beerDto.getBeerStyle());
		assertEquals(expected.getUpc(), beerDto.getUpc());
		assertEquals(expected.getPrice(), beerDto.getPrice());
		assertEquals(expected.getCreatedDate(), beer.getCreatedDate());
		assertEquals(expected.getLastModifiedDate(), beerDto.getLastModifiedDate());
		assertEquals(expected.getVersion(), beerDto.getVersion());
	}

}