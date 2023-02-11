package springframework.msscbeer.web.mappers;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import springframework.brewery.model.BeerDto;
import springframework.msscbeer.domain.Beer;

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
			ignore = true
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
