package springframework.msscbeerservice.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
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
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", shape = JsonFormat.Shape.STRING)
	private OffsetDateTime createdDate;

	@Null
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", shape = JsonFormat.Shape.STRING)
	private OffsetDateTime lastModifiedDate;

	@NotBlank
	private String beerName;

	@NotNull
	private BeerStyleEnum beerStyle;

	@NotNull
	private String upc;

	@Positive
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private BigDecimal price;

	//	@NotNull
	//	private Currency currency;

	@NotNull
	@PositiveOrZero
	private Integer quantityOnHand;
}
