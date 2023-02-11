package springframework.msscbeerorder.state.machine.actions;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;
import springframework.msscbeerorder.domain.BeerOrder;
import springframework.msscbeerorder.domain.BeerOrderEventEnum;
import springframework.msscbeerorder.domain.BeerOrderStatusEnum;
import springframework.msscbeerorder.repositories.BeerOrderRepository;
import springframework.msscbeerorder.web.mappers.BeerOrderMapper;

import java.util.UUID;

import static springframework.msscbeerorder.config.JmsConfig.ALLOCATE_ORDER_QUEUE;
import static springframework.msscbeerorder.services.BeerOrderManagerImpl.ORDER_ID_HEADER;

@Slf4j
@RequiredArgsConstructor
@Component
public class AllocateOrderAction implements Action<BeerOrderStatusEnum, BeerOrderEventEnum> {
	private final JmsTemplate jmsTemplate;
	private final BeerOrderRepository beerOrderRepository;
	private final BeerOrderMapper beerOrderMapper;

	@Override
	public void execute(StateContext<BeerOrderStatusEnum, BeerOrderEventEnum> context) {
		UUID beerOrderId = context.getMessage()
		                          .getHeaders()
		                          .get(ORDER_ID_HEADER, UUID.class);
		BeerOrder beerOrder = beerOrderRepository.findOneById(beerOrderId);

		jmsTemplate.convertAndSend(ALLOCATE_ORDER_QUEUE, beerOrderMapper.beerOrderToDto(beerOrder));

		log.debug("Sent Allocation Request for order id: " + beerOrderId);
	}
}
