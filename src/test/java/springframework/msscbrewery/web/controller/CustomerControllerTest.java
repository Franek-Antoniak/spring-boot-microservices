package springframework.msscbrewery.web.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import springframework.msscbrewery.web.model.CustomerDto;
import springframework.msscbrewery.web.service.CustomerService;

import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

	@MockBean CustomerService customerService;

	@Autowired MockMvc mockMvc;

	@Autowired ObjectMapper objectMapper;
	CustomerDto validCustomer;

	@BeforeEach
	public void setUp() {
		validCustomer = CustomerDto.builder()
				.id(UUID.randomUUID())
				.name("Customer1")
				.build();
	}

	@Test
	void getCustomer() throws Exception {
		// given

		// when
		when(customerService.getCustomerById(any(UUID.class))).thenReturn(validCustomer);

		// then
		mockMvc.perform(get("/api/v1/customer/" + validCustomer.getId()
						.toString()).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id", is(validCustomer.getId()
						.toString())))
				.andExpect(jsonPath("$.name", is(validCustomer.getName())));

		then(customerService).should()
				.getCustomerById(any(UUID.class));
	}

	@Test
	void handlePost() throws Exception {
		// given

		// when
		when(customerService.saveNewCustomer(any())).thenReturn(validCustomer);

		// then
		mockMvc.perform(post("/api/v1/customer/").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(validCustomer)))
				.andExpect(status().isCreated())
				.andExpect(header().string("Location", "/api/v1/customer/" + validCustomer.getId()
						.toString()));

		then(customerService).should()
				.saveNewCustomer(any());
	}

	@Test
	void handleUpdate() throws Exception {
		// given

		// when

		// then
		mockMvc.perform(put("/api/v1/customer/" + validCustomer.getId()
						.toString()).contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(validCustomer)))
				.andExpect(status().isNoContent());

		then(customerService).should()
				.updateCustomer(any(), any());
	}

	@Test
	void deleteCustomer() throws Exception {
		// given

		// when

		// then
		mockMvc.perform(delete("/api/v1/customer/" + validCustomer.getId()
						.toString()))
				.andExpect(status().isNoContent());

		then(customerService).should()
				.deleteById(any());
	}
}