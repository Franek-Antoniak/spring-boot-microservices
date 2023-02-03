package springframework.msscbrewery.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springframework.msscbrewery.web.model.CustomerDto;
import springframework.msscbrewery.web.service.CustomerService;

import javax.validation.Valid;
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

	@PostMapping
	public ResponseEntity<Void> handlePost(@Valid @RequestBody CustomerDto customerDto) {
		CustomerDto savedDto = customerService.saveNewCustomer(customerDto);

		HttpHeaders headers = new HttpHeaders();
		//todo add hostname to url
		headers.add("Location", "/api/v1/customer/" + savedDto.getId()
				.toString());

		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	@PutMapping({"/{customerId}"})
	public ResponseEntity<Void> handleUpdate(@PathVariable("customerId") UUID customerId,
			@Valid @RequestBody CustomerDto customerDto) {
		customerService.updateCustomer(customerId, customerDto);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping({"/{customerId}"})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCustomer(@PathVariable("customerId") UUID customerId) {
		customerService.deleteById(customerId);
	}
}
