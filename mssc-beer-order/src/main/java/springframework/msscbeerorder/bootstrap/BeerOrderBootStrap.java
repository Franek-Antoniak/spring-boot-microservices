package springframework.msscbeerorder.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import springframework.msscbeerorder.domain.Customer;
import springframework.msscbeerorder.repositories.CustomerRepository;

import java.util.UUID;


@RequiredArgsConstructor
@Component
public class BeerOrderBootStrap implements CommandLineRunner {
	public static final String TASTING_ROOM = "Tasting Room";
	public static final String BEER_1_UPC = "0631234200036";
	public static final String BEER_2_UPC = "0631234300019";
	public static final String BEER_3_UPC = "0083783375213";

	private final CustomerRepository customerRepository;

	@Override
	public void run(String... args) {
		loadCustomerData();
	}

	private void loadCustomerData() {
		if (customerRepository.count() == 0) {
			customerRepository.save(Customer.builder()
					.customerName(TASTING_ROOM)
					.apiKey(UUID.randomUUID())
					.build());
		}
	}
}
