package springframework.brewery.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeerDto implements Serializable {
	@Serial
	private static final long serialVersionUID = -2287110524734738390L;

	@Null
	private UUID id;

	@Null
	private Integer version;

	@Null
	@JsonFormat(
			pattern = "yyyy-MM-dd'T'HH:mm:ssZ",
			shape = JsonFormat.Shape.STRING
	)
	private OffsetDateTime createdDate;

	@Null
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", shape = JsonFormat.Shape.STRING)
	private OffsetDateTime lastModifiedDate;

	@NotBlank
	private String beerName;

	@NotNull
	private String beerStyle;

	@NotNull
	private String upc;

	@Positive
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private BigDecimal price;

	@NotNull
	@PositiveOrZero
	private Integer quantityOnHand;
}
