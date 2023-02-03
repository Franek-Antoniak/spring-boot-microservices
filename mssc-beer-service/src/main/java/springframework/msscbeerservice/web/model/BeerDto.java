package springframework.msscbeerservice.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Currency;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeerDto {
	private UUID id;
	private Integer version;

	private OffsetDateTime createdDate;
	private OffsetDateTime lastModifiedDate;

	private String beerName;

	private BeerStyleEnum beerStyle;

	private Long upc;

	private BigDecimal price;
	private Currency currency;
	private Integer quantityOnHand;
}
