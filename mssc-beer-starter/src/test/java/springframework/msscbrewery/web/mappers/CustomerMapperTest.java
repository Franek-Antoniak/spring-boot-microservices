package springframework.msscbrewery.web.mappers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import springframework.msscbrewery.web.domain.Customer;
import springframework.msscbrewery.web.model.CustomerDto;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerMapperTest {
	CustomerMapper customerMapper = CustomerMapper.INSTANCE;
	Customer customer;
	CustomerDto customerDto;

	@BeforeEach
	void setUp() {
		customer = Customer.builder()
				.id(UUID.randomUUID())
				.name("John")
				.build();
		customerDto = CustomerDto.builder()
				.id(customer.getId())
				.name("John")
				.build();
	}

	@Test
	void customerToCustomerDto() {
		CustomerDto expectedDto = customerMapper.customerToCustomerDto(customer);

		assertEquals(expectedDto, customerDto);
	}

	@Test
	void customerDtoToCustomer() {
		Customer expectedCustomer = customerMapper.customerDtoToCustomer(customerDto);

		assertEquals(expectedCustomer, customer);
	}
}