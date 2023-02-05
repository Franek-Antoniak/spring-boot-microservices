package springframework.msscbeerorder.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BaseItem {
	@JsonProperty("id")
	private UUID id;

	@JsonProperty("version")
	private Integer version;

	@JsonFormat(
			pattern = "yyyy-MM-dd'T'HH:mm:ssZ",
			shape = JsonFormat.Shape.STRING
	)
	@JsonProperty("createdDate")
	private OffsetDateTime createdDate;

	@JsonFormat(
			pattern = "yyyy-MM-dd'T'HH:mm:ssZ",
			shape = JsonFormat.Shape.STRING
	)
	@JsonProperty("lastModifiedDate")
	private OffsetDateTime lastModifiedDate;
}

