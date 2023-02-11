package springframework.brewery.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Null;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class OrderStatusUpdate {
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
	@JsonFormat(
			pattern = "yyyy-MM-dd'T'HH:mm:ssZ",
			shape = JsonFormat.Shape.STRING
	)
	private OffsetDateTime lastModifiedDate;

	private UUID orderId;
	private String customerRef;
	private String orderStatus;
}
