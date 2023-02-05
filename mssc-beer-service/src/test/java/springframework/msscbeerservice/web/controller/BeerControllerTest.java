package springframework.msscbeerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import springframework.msscbeerservice.services.BeerService;
import springframework.msscbeerservice.web.model.BeerDto;
import springframework.msscbeerservice.web.model.BeerStyleEnum;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(BeerController.class)
@ExtendWith(MockitoExtension.class)
class BeerControllerTest {
	@Autowired
	MockMvc mockMvc;

	@MockBean
	BeerService beerService;

	@Autowired
	ObjectMapper objectMapper;
	BeerDto beerDto;

	@BeforeEach
	void setUp() {
		beerDto = BeerDto.builder()
				.beerName("My Beer")
				.beerStyle(BeerStyleEnum.valueOf("PALE_ALE"))
				.upc("123123123123L")
				.quantityOnHand(200)
				.price(BigDecimal.valueOf(12.99))
				.build();
	}

	@Test
	void getBeerById() throws Exception {
		// given

		// when
		when(beerService.getBeerById(any())).thenReturn(beerDto);

		// then
		mockMvc.perform(get("/api/v1/beer/" + UUID.randomUUID()).accept(APPLICATION_JSON))
				.andExpect(status().isOk());

		then(beerService).should()
				.getBeerById(any());
	}

	@Test
	void saveNewBeer() throws Exception {
		// given

		// when
		when(beerService.saveNewBeer(any())).thenReturn(beerDto);

		// then
		mockMvc.perform(post("/api/v1/beer/").contentType(APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(beerDto)))
				.andExpect(status().isCreated());

		then(beerService).should()
				.saveNewBeer(any());
	}

	@Test
	void updateBeerById() throws Exception {
		// given

		// when
		when(beerService.updateBeer(any(), any())).thenReturn(beerDto);

		// then
		mockMvc.perform(put("/api/v1/beer/" + UUID.randomUUID()).contentType(APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(beerDto)))
				.andExpect(status().isNoContent());

		then(beerService).should()
				.updateBeer(any(), any());
	}

	@Test
	void handlePostWithException_MethodArgumentNotValidException() throws Exception {
		// given
		beerDto.setId(UUID.randomUUID());

		// when

		// then
		mockMvc.perform(post("/api/v1/beer/").contentType(APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(beerDto)))
				.andExpect(status().isBadRequest());

		then(beerService).should(never())
				.saveNewBeer(any());
	}

	@Test
	void handlePostWithException_NotFoundException() throws Exception {
		// given

		// when
		when(beerService.saveNewBeer(any())).thenThrow(NotFoundException.class);

		// then
		mockMvc.perform(post("/api/v1/beer/").contentType(APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(beerDto)))
				.andExpect(status().isNotFound());

		then(beerService).should()
				.saveNewBeer(any());
	}

}