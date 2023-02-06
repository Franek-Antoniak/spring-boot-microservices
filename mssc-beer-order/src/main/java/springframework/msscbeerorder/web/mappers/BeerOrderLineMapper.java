package springframework.msscbeerorder.web.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import springframework.msscbeerorder.domain.BeerOrderLine;
import springframework.msscbeerorder.web.model.BeerOrderLineDto;

@Mapper(uses = {DateMapper.class})
public interface BeerOrderLineMapper {
	@Mapping(
			target = "beerName",
			ignore = true
	)
	BeerOrderLineDto beerOrderLineToDto(BeerOrderLine line);

	@Mapping(
			target = "quantityAllocated",
			ignore = true
	)
	@Mapping(
			target = "beerOrder",
			ignore = true
	)
	BeerOrderLine dtoToBeerOrderLine(BeerOrderLineDto dto);
}
