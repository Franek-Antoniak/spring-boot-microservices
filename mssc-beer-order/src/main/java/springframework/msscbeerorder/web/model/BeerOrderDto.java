package springframework.msscbeerorder.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class BeerOrderDto extends BaseItem {

	private UUID customerId;
	private String customerRef;
	private List<BeerOrderLineDto> beerOrderLines;
	private OrderStatusEnum orderStatus;
	private String orderStatusCallbackUrl;
}
