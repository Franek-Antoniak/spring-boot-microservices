package springframework.msscbeer.domain;

import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
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
	@Type(type = "org.hibernate.type.UUIDCharType")
	@GenericGenerator(
			name = "UUID",
			strategy = "org.hibernate.id.UUIDGenerator"
	)
	@Column(
			length = 36,
			columnDefinition = "varchar(36)",
			updatable = false,
			nullable = false
	)
	private UUID id;
	private Integer version;

	@CreationTimestamp
	@Column(updatable = false)
	private OffsetDateTime createdDate;
	@UpdateTimestamp
	private OffsetDateTime lastModifiedDate;
	private String beerName;
	@Enumerated(EnumType.STRING)
	private BeerStyleEnum beerStyle;
	@NaturalId
	@Column(unique = true)
	private String upc;
	private BigDecimal price;
	private Integer quantityToBrew;
	private Integer minOnHand;
}
