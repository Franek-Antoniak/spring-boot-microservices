package springframework.msscbeerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springframework.msscbeerservice.domain.Beer;

import java.util.UUID;

public interface BeerRepository extends JpaRepository<Beer, UUID> {

}
