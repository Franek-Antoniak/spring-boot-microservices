package springframework.msscbeerservice.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springframework.msscbeerservice.services.BeerService;
import springframework.msscbeerservice.web.model.BeerDto;
import springframework.msscbeerservice.web.model.BeerPagedList;
import springframework.msscbeerservice.web.model.BeerStyleEnum;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/beer")
public class BeerController {
	private static final String DEFAULT_PAGE_NUMBER = "0";
	private static final String DEFAULT_PAGE_SIZE = "25";
	private final BeerService beerService;

	@GetMapping
	public ResponseEntity<BeerPagedList> listBeers(
			@RequestParam(
					required = false,
					defaultValue = DEFAULT_PAGE_NUMBER
			)
			@PositiveOrZero Integer pageNumber,
			@RequestParam(
					required = false,
					defaultValue = DEFAULT_PAGE_SIZE
			)
			@Positive Integer pageSize,
			@RequestParam(required = false) String beerName,
			@RequestParam(required = false) BeerStyleEnum beerStyle) {
		BeerPagedList beerList = beerService.listBeers(beerName, beerStyle, PageRequest.of(pageNumber, pageSize));
		return new ResponseEntity<>(beerList, HttpStatus.OK);
	}

	@GetMapping("/{beerId}")
	public ResponseEntity<BeerDto> getBeerById(
			@PathVariable("beerId") UUID beerId) {
		return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<BeerDto> saveNewBeer(@Valid
	@RequestBody
	BeerDto beerDto) {
		return new ResponseEntity<>(beerService.saveNewBeer(beerDto), HttpStatus.CREATED);
	}


	@PutMapping("/{beerId}")
	public ResponseEntity<BeerDto> updateBeerById(
			@PathVariable("beerId") UUID beerId, @Valid
	@RequestBody
	BeerDto beerDto) {
		return new ResponseEntity<>(beerService.updateBeer(beerId, beerDto), HttpStatus.NO_CONTENT);
	}
}
