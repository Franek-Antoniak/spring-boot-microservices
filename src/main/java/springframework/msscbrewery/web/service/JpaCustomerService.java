package springframework.msscbrewery.web.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import springframework.msscbrewery.web.model.CustomerDto;

import java.util.UUID;

@Service
@Slf4j
class JpaCustomerService implements CustomerService {
	@Override
	public CustomerDto getCustomerById(UUID customerId) {
		return CustomerDto.builder()
				.id(UUID.randomUUID())
				.name("John Doe")
				.build();
	}

	@Override
	public CustomerDto saveNewCustomer(CustomerDto customerDto) {
		return CustomerDto.builder()
				.id(UUID.randomUUID())
				.build();
	}

	@Override
	public void deleteById(UUID customerId) {
		log.debug("Deleting a customer...");
	}

	@Override
	public void updateCustomer(UUID customerId, CustomerDto customerDto) {
		// todo impl - would add a real impl to update customer
	}
}
