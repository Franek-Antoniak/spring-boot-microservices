package springframework.msscbeerorder.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
public class OrderStatusUpdate extends BaseItem {
	private UUID orderId;
	private String customerRef;
	private String orderStatus;
}
