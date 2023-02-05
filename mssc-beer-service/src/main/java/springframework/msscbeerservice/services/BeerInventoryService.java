package springframework.msscbeerservice.services;

import java.util.UUID;

public interface BeerInventoryService {
	Integer getOnHandInventory(UUID beerId);
}
