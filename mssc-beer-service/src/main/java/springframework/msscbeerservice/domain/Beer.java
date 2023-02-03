package springframework.msscbeerservice.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import springframework.msscbeerservice.web.model.BeerStyleEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Currency;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Beer {
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
			name = "UUID",
			strategy = "org.hibernate.id.UUIDGenerator"
	)
	@Column(
			length = 36,
			columnDefinition = "varchar",
			updatable = false,
			nullable = false
	)
	private UUID id;
	private Integer version;

	@CreationTimestamp
	@Column(updatable = false)
	private Timestamp createdDate;
	@UpdateTimestamp
	private Timestamp lastModifiedDate;
	private String beerName;
	private BeerStyleEnum beerStyle;
	@Column(unique = true)
	private Long upc;
	private BigDecimal price;
	private Currency currency;
	private Integer quantityToBrew;
}
