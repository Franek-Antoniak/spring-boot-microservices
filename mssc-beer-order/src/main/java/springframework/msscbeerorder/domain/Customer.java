package springframework.msscbeerorder.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@Entity
@AllArgsConstructor
@SuperBuilder
public class Customer extends BaseEntity {

	private String customerName;
	@Column(
			length = 36,
			columnDefinition = "varchar"
	)
	private UUID apiKey;
	@OneToMany(mappedBy = "customer")
	private Set<BeerOrder> beerOrders;
}
