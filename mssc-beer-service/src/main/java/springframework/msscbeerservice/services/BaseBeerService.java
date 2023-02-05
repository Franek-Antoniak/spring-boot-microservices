package springframework.msscbeerservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import springframework.msscbeerservice.repository.BeerRepository;
import springframework.msscbeerservice.web.controller.NotFoundException;
import springframework.msscbeerservice.web.mappers.BeerMapper;
import springframework.msscbeerservice.web.model.BeerDto;

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
}
