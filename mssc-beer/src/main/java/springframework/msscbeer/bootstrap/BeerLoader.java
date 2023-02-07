package springframework.msscbeer.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import springframework.msscbeer.domain.Beer;
import springframework.msscbeer.repository.BeerRepository;
import springframework.msscbeer.web.model.BeerStyleEnum;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class BeerLoader implements CommandLineRunner {
	public static final String BEER_1_UPC = "0631234200036";
	public static final String BEER_2_UPC = "0631234300019";
	public static final String BEER_3_UPC = "0083783375213";
	private final BeerRepository beerRepository;


	@Override
	public void run(String... args) {
		loadBeerObjects();
	}

	private void loadBeerObjects() {
		if (beerRepository.count() == 0) {
			Beer beer1 = Beer.builder()
			                 .beerName("Mango Bobs")
			                 .beerStyle(BeerStyleEnum.IPA)
			                 .price(new BigDecimal("12.95"))
			                 .quantityToBrew(200)
			                 .minOnHand(12)
			                 .upc(BEER_1_UPC)
			                 .build();

			Beer beer2 = Beer.builder()
			                 .beerName("Galaxy Cat")
			                 .beerStyle(BeerStyleEnum.PALE_ALE)
			                 .price(new BigDecimal("12.95"))
			                 .quantityToBrew(200)
			                 .minOnHand(12)
			                 .upc(BEER_2_UPC)
			                 .build();

			Beer beer3 = Beer.builder()
			                 .beerName("Pinball Porter")
			                 .beerStyle(BeerStyleEnum.PORTER)
			                 .price(new BigDecimal("12.95"))
			                 .quantityToBrew(200)
			                 .minOnHand(12)
			                 .upc(BEER_3_UPC)
			                 .build();

			beerRepository.save(beer1);
			beerRepository.save(beer2);
			beerRepository.saveAndFlush(beer3);
		}
	}
}
