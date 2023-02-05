package springframework.msscbeerservice.web.mappers;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import springframework.msscbeerservice.domain.Beer;
import springframework.msscbeerservice.web.model.BeerDto;

@Mapper
@DecoratedWith(BeerMapperDecorator.class)
public abstract class BeerMapper {

	@Mapping(
			target = "quantityOnHand",
			ignore = true
	)
	public abstract BeerDto beerToBeerDto(Beer beer);


	@Mapping(
			target = "minOnHand",
			ignore = true
	)
	@Mapping(
			target = "quantityToBrew",
			source = "quantityOnHand"
	)
	public abstract Beer beerDtoToBeer(BeerDto beerDto);

	@Mapping(
			target = "minOnHand",
			ignore = true
	)
	@Mapping(
			target = "quantityToBrew",
			ignore = true
	)
	public abstract Beer updateBeerFromDto(BeerDto beerDto,
			@MappingTarget Beer beer);
}
