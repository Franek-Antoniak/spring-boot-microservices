package springframework.msscbrewery.web.service;

import org.springframework.stereotype.Service;
import springframework.msscbrewery.web.model.CustomerDto;

import java.util.UUID;

@Service
class JpaCustomerService implements CustomerService {
	@Override
	public CustomerDto getCustomerById(UUID customerId) {
		return CustomerDto.builder()
				.id(UUID.randomUUID())
				.name("John Doe")
				.build();
	}
}
