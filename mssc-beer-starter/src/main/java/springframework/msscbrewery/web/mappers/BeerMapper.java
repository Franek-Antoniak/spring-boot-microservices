package springframework.msscbrewery.web.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import springframework.msscbrewery.web.domain.Beer;
import springframework.msscbrewery.web.model.BeerDto;

@Mapper(uses = {DateMapper.class})
public abstract class BeerMapper {
	public static final BeerMapper INSTANCE = Mappers.getMapper(BeerMapper.class);

	abstract BeerDto beerToBeerDto(Beer beer);

	abstract Beer beerDtoToBeer(BeerDto beerDto);
}
