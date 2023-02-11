package springframework.msscbeer.services;

import io.micrometer.core.instrument.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import springframework.brewery.model.BeerDto;
import springframework.brewery.model.BeerPagedList;
import springframework.msscbeer.domain.Beer;
import springframework.msscbeer.domain.BeerStyleEnum;
import springframework.msscbeer.repository.BeerRepository;
import springframework.msscbeer.web.controller.NotFoundException;
import springframework.msscbeer.web.mappers.BeerMapperDecorator;

import java.util.Objects;
import java.util.UUID;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
class BaseBeerService implements BeerService {
	private final BeerRepository beerRepository;
	private final BeerMapperDecorator beerMapper;

	@Override
	@Cacheable(
			cacheNames = "beerCache",
			key = "#beerId",
			condition = "#showInventoryOnHand == false"
	)
	public BeerDto getBeerById(UUID beerId, Boolean showInventoryOnHand) {
		Function<Beer, BeerDto> beerMapperFunction =
				showInventoryOnHand ? beerMapper::beerToBeerDtoCheckInventory : beerMapper::beerToBeerDto;
		return beerRepository.findById(beerId)
		                     .map(beerMapperFunction)
		                     .orElseThrow(NotFoundException::new);
	}

	@Override
	public BeerDto saveNewBeer(BeerDto beerDto) {
		return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beerDto)));
	}

	@Override
	@Transactional
	public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
		beerDto.setId(beerId);
		return beerMapper.beerToBeerDto(beerRepository.findById(beerId)
		                                              .map(beer -> beerMapper.updateBeerFromDto(beerDto, beer))
		                                              .orElseThrow(NotFoundException::new));
	}


	@Cacheable(
			cacheNames = "beerListCache",
			condition = "#showInventoryOnHand == false"
	)
	@Override
	public BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest of,
			Boolean showInventoryOnHand) {
		Page<Beer> beerPage;
		if (!StringUtils.isEmpty(beerName) && Objects.nonNull(beerStyle)) {
			beerPage = beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyle, of);
		} else if (StringUtils.isEmpty(beerName) && Objects.isNull(beerStyle)) {
			beerPage = beerRepository.findAll(of);
		} else if (StringUtils.isEmpty(beerName)) {
			beerPage = beerRepository.findAllByBeerStyle(beerStyle, of);
		} else {
			beerPage = beerRepository.findAllByBeerName(beerName, of);
		}
		Function<Beer, BeerDto> beerMapperFunction =
				showInventoryOnHand ? beerMapper::beerToBeerDtoCheckInventory : beerMapper::beerToBeerDto;
		return new BeerPagedList(beerPage.getContent()
		                                 .stream()
		                                 .map(beerMapperFunction)
		                                 .toList(), PageRequest.of(beerPage.getPageable()
		                                                                   .getPageNumber(), beerPage.getPageable()
		                                                                                             .getPageSize()),
		                         beerPage.getTotalElements());
	}

	@Override
	@Cacheable(
			cacheNames = "beerUpcCache",
			key = "#upc",
			condition = "#showInventoryOnHand == false"
	)
	public BeerDto getBeerByUpc(String upc, Boolean showInventoryOnHand) {
		Function<Beer, BeerDto> beerMapperFunction =
				showInventoryOnHand ? beerMapper::beerToBeerDtoCheckInventory : beerMapper::beerToBeerDto;
		return beerRepository.findByUpc(upc)
		                     .map(beerMapperFunction)
		                     .orElseThrow(NotFoundException::new);
	}
}
