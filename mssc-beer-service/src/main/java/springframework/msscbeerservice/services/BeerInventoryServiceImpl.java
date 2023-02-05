package springframework.msscbeerservice.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import springframework.msscbeerservice.web.model.BeerInventoryDto;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class BeerInventoryServiceImpl implements BeerInventoryService {
	private final String INVENTORY_PATH_V1 = "/api/v1/beer/{beerId}/inventory";
	private final RestTemplate restTemplate;
	private final String beerInventoryServiceHost;

	@Override
	public Integer getOnHandInventory(UUID beerId) {
		log.debug("Calling Inventory Service");

		ResponseEntity<List<BeerInventoryDto>> responseEntity = restTemplate.exchange(
				beerInventoryServiceHost + INVENTORY_PATH_V1, HttpMethod.GET, null,
				new ParameterizedTypeReference<>() {}, beerId);

		return Objects.requireNonNull(responseEntity.getBody())
		              .stream()
		              .mapToInt(BeerInventoryDto::getQuantityOnHand)
		              .sum();
	}
}
