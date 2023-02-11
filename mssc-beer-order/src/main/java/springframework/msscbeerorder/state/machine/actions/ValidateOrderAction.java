package springframework.msscbeerorder.state.machine.actions;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;
import springframework.brewery.model.events.ValidateOrderRequest;
import springframework.msscbeerorder.domain.BeerOrder;
import springframework.msscbeerorder.domain.BeerOrderStatusEnum;
import springframework.msscbeerorder.repositories.BeerOrderRepository;
import springframework.msscbeerorder.web.mappers.BeerOrderMapper;

import java.util.UUID;

import static springframework.msscbeerorder.config.JmsConfig.VALIDATE_ORDER_QUEUE;
import static springframework.msscbeerorder.services.BeerOrderManagerImpl.ORDER_ID_HEADER;

@Slf4j
@Component
@RequiredArgsConstructor
public class ValidateOrderAction implements Action<BeerOrderStatusEnum, BeerOrderStatusEnum> {
	private final BeerOrderRepository beerOrderRepository;
	private final BeerOrderMapper beerOrderMapper;
	private final JmsTemplate jmsTemplate;

	@Override
	public void execute(StateContext<BeerOrderStatusEnum, BeerOrderStatusEnum> context) {
		UUID beerOrderId = context.getMessage()
		                          .getHeaders()
		                          .get(ORDER_ID_HEADER, UUID.class);
		BeerOrder beerOrder = beerOrderRepository.findOneById(beerOrderId);

		jmsTemplate.convertAndSend(VALIDATE_ORDER_QUEUE, ValidateOrderRequest.builder()
		                                                                     .beerOrder(beerOrderMapper.beerOrderToDto(
				                                                                     beerOrder))
		                                                                     .build());

		log.debug("Sent Validation request to queue for order id " + beerOrderId);
	}

}
