package springframework.msscbeerorder.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SuperBuilder
public class BeerOrderLine extends BaseEntity {
	@ManyToOne
	private BeerOrder beerOrder;
	@Type(type = "org.hibernate.type.UUIDCharType")
	@Column(
			length = 36,
			columnDefinition = "varchar(36)"
	)
	private UUID beerId;
	@Builder.Default
	private Integer orderQuantity = 0;
	@Builder.Default
	private Integer quantityAllocated = 0;
	String upc;
}
