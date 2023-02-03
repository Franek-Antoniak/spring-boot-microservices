package springframework.msscbeerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import springframework.msscbeerservice.web.model.BeerDto;
import springframework.msscbeerservice.web.model.BeerStyleEnum;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Currency;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeerController.class)
@ExtendWith(MockitoExtension.class)
class BeerControllerTest {
	@Autowired MockMvc mockMvc;

	@Autowired ObjectMapper objectMapper;

	BeerDto validBeer;

	@BeforeEach
	void setUp() {
		validBeer = BeerDto.builder()
				.id(UUID.randomUUID())
				.beerName("My Beer")
				.beerStyle(BeerStyleEnum.valueOf("PALE_ALE"))
				.upc(123123123123L)
				.version(1)
				.quantityOnHand(200)
				.price(BigDecimal.valueOf(12.99))
				.currency(Currency.getInstance("USD"))
				.createdDate(OffsetDateTime.now())
				.lastModifiedDate(OffsetDateTime.now())
				.build();
	}

	@Test
	void getBeerById() throws Exception {
		// given

		// when

		// then
		mockMvc.perform(get("/api/v1/beer/" + validBeer.getId()
						.toString()).accept(APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void saveNewBeer() throws Exception {
		// given

		// when

		// then
		mockMvc.perform(post("/api/v1/beer/").contentType(APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(validBeer)))
				.andExpect(status().isCreated());
	}

	@Test
	void updateBeerById() throws Exception {
		// given

		// when

		// then
		mockMvc.perform(put("/api/v1/beer/" + validBeer.getId()
						.toString()).contentType(APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(validBeer)))
				.andExpect(status().isNoContent());
	}
}