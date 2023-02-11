package springframework.msscbeerorder.services;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springframework.msscbeerorder.domain.BeerOrder;
import springframework.msscbeerorder.domain.BeerOrderEventEnum;
import springframework.msscbeerorder.domain.BeerOrderStatusEnum;
import springframework.msscbeerorder.repositories.BeerOrderRepository;

@Service
@RequiredArgsConstructor
public class BeerOrderManagerImpl implements BeerOrderManager {
	private final StateMachineFactory<BeerOrderStatusEnum, BeerOrderEventEnum> stateMachineFactory;
	private final BeerOrderRepository beerOrderRepository;

	@Transactional
	@Override
	public BeerOrder newBeerOrder(BeerOrder beerOrder) {
		beerOrder.setId(null);
		beerOrder.setOrderStatus(BeerOrderStatusEnum.NEW);

		BeerOrder savedBeerOrder = beerOrderRepository.save(beerOrder);

		sendBeerOrderEvent(savedBeerOrder, BeerOrderEventEnum.VALIDATE_ORDER);
		return savedBeerOrder;
	}

	private void sendBeerOrderEvent(BeerOrder beerOrder, BeerOrderEventEnum eventEnum) {
		StateMachine<BeerOrderStatusEnum, BeerOrderEventEnum> sm = build(beerOrder);
		Message<BeerOrderEventEnum> msg = MessageBuilder.withPayload(eventEnum)
		                                                .build();
		sm.sendEvent(msg);
	}

	private StateMachine<BeerOrderStatusEnum, BeerOrderEventEnum> build(BeerOrder beerOrder) {
		StateMachine<BeerOrderStatusEnum, BeerOrderEventEnum> sm = stateMachineFactory.getStateMachine(
				beerOrder.getId());
		sm.stop();

		sm.getStateMachineAccessor()
		  .doWithAllRegions(sma -> {
			  sma.resetStateMachine(new DefaultStateMachineContext<>(beerOrder.getOrderStatus(), null, null, null));
		  });

		sm.start();
		return sm;
	}
}
