package springframework.msscbeerorder.web.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import springframework.msscbeerorder.domain.BeerOrder;
import springframework.msscbeerorder.web.model.BeerOrderDto;


@Mapper(
		uses = {
				DateMapper.class,
				BeerOrderLineMapper.class
		}
)
public interface BeerOrderMapper {

	@Mapping(
			target = "customerId",
			ignore = true
	)
	BeerOrderDto beerOrderToDto(BeerOrder beerOrder);

	@Mappings(
			{
					@Mapping(
							target = "customer",
							ignore = true
					),
					@Mapping(
							target = "id",
							ignore = true
					)
			}
	)
	BeerOrder dtoToBeerOrder(BeerOrderDto dto);
}
