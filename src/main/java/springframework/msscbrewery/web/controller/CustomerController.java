package springframework.msscbrewery.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springframework.msscbrewery.web.model.CustomerDto;
import springframework.msscbrewery.web.service.CustomerService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
	private final CustomerService customerService;

	@GetMapping("/{customerId}")
	public CustomerDto getCustomer(@PathVariable("customerId") UUID customerId) {
		return customerService.getCustomerById(customerId);
	}
}
