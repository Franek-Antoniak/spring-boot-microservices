package springframework.msscbeerorder.web.mappers;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import springframework.brewery.model.BeerDto;
import springframework.brewery.model.BeerOrderLineDto;
import springframework.msscbeerorder.domain.BeerOrderLine;
import springframework.msscbeerorder.services.beer.BeerService;

@Setter(onMethod = @__(@Autowired))
public abstract class BeerOrderLineMapperDecorator implements BeerOrderLineMapper {
	private BeerService beerService;
	private BeerOrderLineMapper beerOrderLineMapper;

	@Override
	public BeerOrderLineDto beerOrderLineToDto(BeerOrderLine line) {
		BeerOrderLineDto dto = beerOrderLineMapper.beerOrderLineToDto(line);
		BeerDto beerDto = beerService.getBeerByUpc(line.getUpc());
		dto.setBeerName(beerDto.getBeerName());
		dto.setBeerStyle(beerDto.getBeerStyle());
		dto.setPrice(beerDto.getPrice());
		dto.setBeerId(beerDto.getId());
		return dto;
	}
}
