package springframework.msscbrewery.web.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import springframework.msscbrewery.web.domain.Customer;
import springframework.msscbrewery.web.model.CustomerDto;

@Mapper
public abstract class CustomerMapper {
	public static final CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

	abstract CustomerDto customerToCustomerDto(Customer customer);

	abstract Customer customerDtoToCustomer(CustomerDto customerDto);
}
