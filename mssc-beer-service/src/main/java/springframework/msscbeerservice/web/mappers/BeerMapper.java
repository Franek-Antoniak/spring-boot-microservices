package springframework.msscbeerservice.web.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import springframework.msscbeerservice.domain.Beer;
import springframework.msscbeerservice.web.model.BeerDto;

@Mapper
public abstract class BeerMapper {
	public static final BeerMapper INSTANCE = Mappers.getMapper(BeerMapper.class);

	@Mapping(
			target = "quantityOnHand",
			source = "quantityToBrew"
	)
	public abstract BeerDto beerToBeerDto(Beer beer);

	@Mapping(
			target = "quantityToBrew",
			source = "quantityOnHand"
	)
	public abstract Beer beerDtoToBeer(BeerDto beerDto);

	@Mapping(
			target = "quantityToBrew",
			source = "quantityOnHand"
	)
	public abstract Beer updateBeerFromDto(BeerDto beerDto, @MappingTarget Beer beer);
}
