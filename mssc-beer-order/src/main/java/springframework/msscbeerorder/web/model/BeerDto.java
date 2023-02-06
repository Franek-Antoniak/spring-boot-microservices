package springframework.msscbeerorder.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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


	private UUID id;


	private Integer version;


	@JsonFormat(
			pattern = "yyyy-MM-dd'T'HH:mm:ssZ",
			shape = JsonFormat.Shape.STRING
	)
	private OffsetDateTime createdDate;


	@JsonFormat(
			pattern = "yyyy-MM-dd'T'HH:mm:ssZ",
			shape = JsonFormat.Shape.STRING
	)
	private OffsetDateTime lastModifiedDate;


	private String beerName;


	private BeerStyleEnum beerStyle;


	private String upc;

	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private BigDecimal price;

	//	@NotNull
	//	private Currency currency;

	private Integer quantityOnHand;
}
