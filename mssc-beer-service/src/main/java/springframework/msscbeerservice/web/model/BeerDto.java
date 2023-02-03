package springframework.msscbeerservice.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Currency;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeerDto {
	@Null
	private UUID id;

	@Null
	private Integer version;

	@Null
	private OffsetDateTime createdDate;

	@Null
	private OffsetDateTime lastModifiedDate;

	@NotBlank
	private String beerName;

	@NotNull
	private BeerStyleEnum beerStyle;

	@Positive
	@NotNull
	private Long upc;

	@Positive
	@NotNull
	private BigDecimal price;
	@NotNull
	@org.hibernate.validator.constraints.Currency({"USD"})
	private Currency currency;

	@NotNull
	@PositiveOrZero
	private Integer quantityOnHand;
}
