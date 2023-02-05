package springframework.msscbeerservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import springframework.msscbeerservice.domain.Beer;
import springframework.msscbeerservice.web.model.BeerStyleEnum;

import java.util.UUID;

public interface BeerRepository extends JpaRepository<Beer, UUID> {

	Page<Beer> findAllByBeerNameAndBeerStyle(String beerName, BeerStyleEnum beerStyle, Pageable of);

	Page<Beer> findAllByBeerStyle(BeerStyleEnum beerStyle, Pageable of);

	Page<Beer> findAllByBeerName(String beerName, Pageable of);
}
