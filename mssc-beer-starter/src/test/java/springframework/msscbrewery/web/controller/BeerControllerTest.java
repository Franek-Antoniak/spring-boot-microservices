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
import springframework.msscbrewery.web.model.BeerDto;
import springframework.msscbrewery.web.service.BeerService;

import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(BeerController.class)
public class BeerControllerTest {

	@MockBean
	BeerService beerService;

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;
	BeerDto validBeer;

	@BeforeEach
	public void setUp() {
		validBeer = BeerDto.builder()
				.id(UUID.randomUUID())
				.beerName("Beer1")
				.beerStyle("PALE_ALE")
				.upc(123456789012L)
				.build();
	}

	@Test
	public void getBeer() throws Exception {
		// given
		given(beerService.getBeerById(any(UUID.class))).willReturn(validBeer);

		// when

		// then
		mockMvc.perform(get("/api/v1/beer/" + UUID.randomUUID()).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id", is(validBeer.getId()
						.toString())))
				.andExpect(jsonPath("$.beerName", is(validBeer.getBeerName())));
	}

	@Test
	public void handlePost() throws Exception {
		//given
		BeerDto beerDto = validBeer;
		beerDto.setId(null);
		BeerDto savedDto = BeerDto.builder()
				.id(UUID.randomUUID())
				.beerName("New Beer")
				.build();
		String beerDtoJson = objectMapper.writeValueAsString(beerDto);

		given(beerService.saveNewBeer(any())).willReturn(savedDto);

		//when

		//then

		mockMvc.perform(post("/api/v1/beer/").contentType(MediaType.APPLICATION_JSON)
						.content(beerDtoJson))
				.andExpect(header().string("Location", "/api/v1/beer/" + savedDto.getId()
						.toString()))
				.andExpect(status().isCreated());

	}

	@Test
	public void handleUpdate() throws Exception {
		//given
		BeerDto beerDto = validBeer;
		beerDto.setId(null);
		String beerDtoJson = objectMapper.writeValueAsString(beerDto);

		//when
		mockMvc.perform(put("/api/v1/beer/" + UUID.randomUUID()).contentType(MediaType.APPLICATION_JSON)
						.content(beerDtoJson))
				.andExpect(status().isNoContent());

		//then
		then(beerService).should()
				.updateBeer(any(), any());
	}

	@Test
	public void deleteBeer() throws Exception {
		//given

		//when
		mockMvc.perform(delete("/api/v1/beer/" + UUID.randomUUID()))
				.andExpect(status().isNoContent());

		//then
		then(beerService).should()
				.deleteById(any());
	}
}
