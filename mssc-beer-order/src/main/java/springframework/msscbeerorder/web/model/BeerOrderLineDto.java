package springframework.msscbeerorder.web.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@SuperBuilder
public class BeerOrderLineDto extends BaseItem {
	private String upc;
	private String beerName;
	private BeerStyleEnum beerStyle;
	private UUID beerId;
	@Builder.Default
	private Integer orderQuantity = 0;
	private BigDecimal price;
}
