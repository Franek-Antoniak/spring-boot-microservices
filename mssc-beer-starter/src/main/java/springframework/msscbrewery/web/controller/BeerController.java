package springframework.msscbrewery.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springframework.msscbrewery.web.model.BeerDto;
import springframework.msscbrewery.web.service.BeerService;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/beer")
@RequiredArgsConstructor
@Slf4j
public class BeerController {

	private final BeerService beerService;

	@GetMapping({"/{beerId}"})
	public ResponseEntity<BeerDto> getBeer(@PathVariable("beerId") UUID beerId) {
		return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Void> handlePost(@Valid @RequestBody BeerDto beerDto) {
		BeerDto savedDto = beerService.saveNewBeer(beerDto);

		HttpHeaders headers = new HttpHeaders();
		//todo add hostname to url
		headers.add("Location", "/api/v1/beer/" + savedDto.getId()
				.toString());

		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	@PutMapping({"/{beerId}"})
	public ResponseEntity<Void> handleUpdate(@PathVariable("beerId") UUID beerId,
			@Valid @RequestBody BeerDto beerDto) {
		beerService.updateBeer(beerId, beerDto);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping({"/{beerId}"})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBeer(@PathVariable("beerId") UUID beerId) {
		beerService.deleteById(beerId);
	}
}
