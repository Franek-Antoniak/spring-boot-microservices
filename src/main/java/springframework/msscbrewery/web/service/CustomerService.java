package springframework.msscbrewery.web.service;

import springframework.msscbrewery.web.model.CustomerDto;

import java.util.UUID;

public interface CustomerService {
	CustomerDto getCustomerById(UUID customerId);

	CustomerDto saveNewCustomer(CustomerDto customerDto);

	void deleteById(UUID customerId);

	void updateCustomer(UUID customerId, CustomerDto customerDto);
}
