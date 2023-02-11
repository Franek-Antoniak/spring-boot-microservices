package springframework.msscbeerorder.state.machine;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import springframework.msscbeerorder.domain.BeerOrderEventEnum;
import springframework.msscbeerorder.domain.BeerOrderStatusEnum;
import springframework.msscbeerorder.repositories.BeerOrderRepository;

import java.util.Optional;
import java.util.UUID;

import static springframework.msscbeerorder.services.BeerOrderManagerImpl.ORDER_ID_HEADER;

@Slf4j
@RequiredArgsConstructor
@Component
public class BeerOrderStateChangeInterceptor extends StateMachineInterceptorAdapter<BeerOrderStatusEnum,
		BeerOrderEventEnum> {
	private final BeerOrderRepository beerOrderRepository;

	@Transactional
	@Override
	public void preStateChange(State<BeerOrderStatusEnum, BeerOrderEventEnum> state,
			Message<BeerOrderEventEnum> message, Transition<BeerOrderStatusEnum, BeerOrderEventEnum> transition,
			StateMachine<BeerOrderStatusEnum, BeerOrderEventEnum> stateMachine) {
		Optional<UUID> beerOrderIdOptional = Optional.ofNullable(message)
		                                             .map(mess -> (String) mess.getHeaders()
		                                                                       .getOrDefault(ORDER_ID_HEADER, " "))
		                                             .map(UUID::fromString);

		beerOrderIdOptional.ifPresent(beerOrderId -> {
			log.debug("Saving state for order id: " + beerOrderId + " Status: " + state.getId());
			beerOrderRepository.findById(beerOrderId)
			                   .ifPresent(beerOrder -> {
				                   beerOrder.setOrderStatus(state.getId());
				                   beerOrderRepository.saveAndFlush(beerOrder);
			                   });
		});
	}
}
