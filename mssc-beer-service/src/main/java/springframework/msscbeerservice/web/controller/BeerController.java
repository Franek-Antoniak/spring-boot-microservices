package springframework.msscbeerservice.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springframework.msscbeerservice.web.model.BeerDto;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/beer")
public class BeerController {

	@GetMapping("/{beerId}")
	public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID beerId) {
		// TODO impl
		return new ResponseEntity<>(BeerDto.builder()
				.build(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Void> saveNewBeer(@Valid @RequestBody BeerDto beerDto) {
		// TODO impl
		return new ResponseEntity<>(HttpStatus.CREATED);
	}


	@PutMapping("/{beerId}")
	public ResponseEntity<Void> updateBeerById(@PathVariable("beerId") UUID beerId,
			@Valid @RequestBody BeerDto beerDto) {
		// TODO impl
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
