package springframework.msscbeerorder.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import springframework.msscbeerorder.domain.BeerOrderLine;

import java.util.UUID;

public interface BeerOrderLineRepository extends PagingAndSortingRepository<BeerOrderLine, UUID> {
}
