package springframework.msscbeerorder.web.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import springframework.brewery.model.BeerOrderDto;
import springframework.msscbeerorder.domain.BeerOrder;


@Mapper(
		uses = {
				DateMapper.class,
				BeerOrderLineMapper.class
		}
)
public interface BeerOrderMapper {

	@Mapping(
			target = "customerId",
			source = "customer.id"
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
					),
					@Mapping(
							target = "orderStatus",
							ignore = true
					)
			}
	)
	BeerOrder dtoToBeerOrder(BeerOrderDto dto);
}
