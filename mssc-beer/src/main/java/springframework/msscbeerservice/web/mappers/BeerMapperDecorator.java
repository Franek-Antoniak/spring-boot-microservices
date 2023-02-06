package springframework.msscbeerservice.web.mappers;


import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import springframework.msscbeerservice.domain.Beer;
import springframework.msscbeerservice.services.inventory.BeerInventoryService;
import springframework.msscbeerservice.web.model.BeerDto;

@Setter(onMethod = @__(@Autowired))
public abstract class BeerMapperDecorator extends BeerMapper {
	private BeerMapper beerMapper;
	private BeerInventoryService beerInventoryService;

	public BeerDto beerToBeerDtoCheckInventory(Beer beer) {
		BeerDto beerDto = beerMapper.beerToBeerDto(beer);
		beerDto.setQuantityOnHand(beerInventoryService.getOnHandInventory(beer.getId()));
		return beerDto;
	}
}
