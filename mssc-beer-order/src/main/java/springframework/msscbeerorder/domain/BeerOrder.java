package springframework.msscbeerorder.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;


@Getter
@Setter
@Entity
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
public class BeerOrder extends BaseEntity {
	private String customerRef;
	@ManyToOne
	private Customer customer;
	@OneToMany(
			mappedBy = "beerOrder",
			cascade = CascadeType.ALL
	)
	@Fetch(FetchMode.JOIN)
	private Set<BeerOrderLine> beerOrderLines;
	@Builder.Default
	private BeerOrderStatusEnum orderStatus = BeerOrderStatusEnum.NEW;
	private String orderStatusCallbackUrl;
}
