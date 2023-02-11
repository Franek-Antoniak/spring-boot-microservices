package springframework.msscbeer.services.order;

import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import springframework.brewery.model.events.ValidateOrderRequest;
import springframework.brewery.model.events.ValidateOrderResult;

import static springframework.msscbeer.config.JmsConfig.VALIDATE_ORDER_QUEUE;
import static springframework.msscbeer.config.JmsConfig.VALIDATE_ORDER_RESPONSE_QUEUE;

@RequiredArgsConstructor
@Component
public class BeerOrderValidationListener {
	private final BeerOrderValidator beerOrderValidator;
	private final JmsTemplate jmsTemplate;

	@JmsListener(destination = VALIDATE_ORDER_QUEUE)
	public void listen(ValidateOrderRequest validateOrderRequest) {
		Boolean isValid = beerOrderValidator.validateOrder(validateOrderRequest.getBeerOrder());

		jmsTemplate.convertAndSend(VALIDATE_ORDER_RESPONSE_QUEUE, ValidateOrderResult.builder()
		                                                                             .orderId(
				                                                                             validateOrderRequest.getBeerOrder()
				                                                                                                 .getId())
		                                                                             .isValid(isValid)
		                                                                             .build());
	}
}
