package springframework.msscbeerinventory.web.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import springframework.msscbeerinventory.domain.BeerInventory;
import springframework.msscbeerinventory.web.model.BeerInventoryDto;


@Mapper(uses = {DateMapper.class})
public interface BeerInventoryMapper {

	@Mapping(
			target = "upc",
			ignore = true
	)
	BeerInventory beerInventoryDtoToBeerInventory(BeerInventoryDto beerInventoryDTO);

	BeerInventoryDto beerInventoryToBeerInventoryDto(BeerInventory beerInventory);
}
