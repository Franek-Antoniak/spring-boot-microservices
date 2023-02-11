package springframework.msscbeerorder.state.machine;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import springframework.msscbeerorder.domain.BeerOrderEventEnum;
import springframework.msscbeerorder.domain.BeerOrderStatusEnum;

import java.util.EnumSet;

import static springframework.msscbeerorder.domain.BeerOrderStatusEnum.*;

@Configuration
@EnableStateMachineFactory
@RequiredArgsConstructor
public class BeerOrderStateMachineConfig extends StateMachineConfigurerAdapter<BeerOrderStatusEnum,
		BeerOrderEventEnum> {
	private final Action<BeerOrderStatusEnum, BeerOrderEventEnum> validateOrderAction;

	@Override
	public void configure(
			StateMachineStateConfigurer<BeerOrderStatusEnum, BeerOrderEventEnum> states) throws Exception {
		states.withStates()
		      .initial(NEW)
		      .states(EnumSet.allOf(BeerOrderStatusEnum.class))
		      .end(DELIVERED)
		      .end(DELIVERY_EXCEPTION)
		      .end(VALIDATION_EXCEPTION)
		      .end(ALLOCATION_EXCEPTION)
		      .end(PICKED_UP);
	}

	@Override
	public void configure(
			StateMachineTransitionConfigurer<BeerOrderStatusEnum, BeerOrderEventEnum> transitions) throws Exception {
		transitions.withExternal()
		           .source(NEW)
		           .target(VALIDATION_PENDING)
		           .event(BeerOrderEventEnum.VALIDATE_ORDER)
		           .action(validateOrderAction)
		           .and()
		           .withExternal()
		           .source(NEW)
		           .target(VALIDATED)
		           .event(BeerOrderEventEnum.VALIDATION_PASSED)
		           .and()
		           .withExternal()
		           .source(NEW)
		           .target(VALIDATION_EXCEPTION)
		           .event(BeerOrderEventEnum.VALIDATION_FAILED)
		           .and();
	}

}
