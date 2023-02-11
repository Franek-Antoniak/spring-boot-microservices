package springframework.msscbeerorder.services.listeners;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import springframework.brewery.model.events.ValidateOrderResult;
import springframework.msscbeerorder.services.BeerOrderManager;

import java.util.UUID;

import static springframework.msscbeerorder.config.JmsConfig.VALIDATE_ORDER_RESPONSE_QUEUE;

@Slf4j
@RequiredArgsConstructor
@Component
public class ValidationResultListener {
	private final BeerOrderManager beerOrderManager;

	@JmsListener(destination = VALIDATE_ORDER_RESPONSE_QUEUE)
	public void listen(ValidateOrderResult result) {
		final UUID beerOrderId = result.getOrderId();

		log.debug("Validation result for order id: " + beerOrderId);

		beerOrderManager.processValidationResult(beerOrderId, result.getIsValid());
	}
}
