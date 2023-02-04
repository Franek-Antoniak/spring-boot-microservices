package springframework.msscbeerservice.web.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import springframework.msscbeerservice.domain.Beer;
import springframework.msscbeerservice.web.model.BeerDto;

@Mapper
public abstract class BeerMapper {
	public static final BeerMapper INSTANCE = Mappers.getMapper(BeerMapper.class);

	@Mapping(
			target = "quantityOnHand",
			ignore = true
	)
	abstract BeerDto beerToBeerDto(Beer beer);

	@Mapping(
			target = "quantityToBrew",
			ignore = true
	)
	abstract Beer beerDtoToBeer(BeerDto beerDto);
}
