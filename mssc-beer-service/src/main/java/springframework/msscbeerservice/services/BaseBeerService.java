package springframework.msscbeerservice.services;

import io.micrometer.core.instrument.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import springframework.msscbeerservice.domain.Beer;
import springframework.msscbeerservice.repository.BeerRepository;
import springframework.msscbeerservice.web.controller.NotFoundException;
import springframework.msscbeerservice.web.mappers.BeerMapper;
import springframework.msscbeerservice.web.model.BeerDto;
import springframework.msscbeerservice.web.model.BeerPagedList;
import springframework.msscbeerservice.web.model.BeerStyleEnum;

import java.util.Objects;
import java.util.UUID;

@Component
@RequiredArgsConstructor
class BaseBeerService implements BeerService {
	private final BeerRepository beerRepository;
	private final BeerMapper beerMapper;

	@Override
	public BeerDto getBeerById(UUID beerId) {
		return beerRepository.findById(beerId)
				.map(beerMapper::beerToBeerDto)
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


	@Override
	public BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest of) {
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

		return new BeerPagedList(beerPage.getContent()
		                                 .stream()
		                                 .map(beerMapper::beerToBeerDto)
		                                 .toList(), PageRequest.of(beerPage.getPageable()
		                                                                   .getPageNumber(), beerPage.getPageable()
		                                                                                             .getPageSize()),
		                         beerPage.getTotalElements());
	}
}
