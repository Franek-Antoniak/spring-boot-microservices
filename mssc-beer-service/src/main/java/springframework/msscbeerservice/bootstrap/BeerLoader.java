package springframework.msscbeerservice.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import springframework.msscbeerservice.domain.Beer;
import springframework.msscbeerservice.repository.BeerRepository;
import springframework.msscbeerservice.web.model.BeerStyleEnum;

import java.math.BigDecimal;
import java.util.Currency;

@Component
@RequiredArgsConstructor
public class BeerLoader implements CommandLineRunner {
	private final BeerRepository beerRepository;


	@Override
	public void run(String... args) {
		loadBeerObjects();
	}

	private void loadBeerObjects() {
		if (beerRepository.count() == 0) {
			beerRepository.save(Beer.builder()
					.beerName("Mango Bobs")
					.beerStyle(BeerStyleEnum.IPA)
					.quantityToBrew(200)
					.upc(337010000001L)
					.price(new BigDecimal("12.95"))
					.currency(Currency.getInstance("USD"))
					.build());

			beerRepository.save(Beer.builder()
					.beerName("Galaxy Cat")
					.beerStyle(BeerStyleEnum.PALE_ALE)
					.quantityToBrew(200)
					.upc(337010000002L)
					.price(new BigDecimal("11.95"))
					.currency(Currency.getInstance("USD"))
					.build());
		}
	}
}