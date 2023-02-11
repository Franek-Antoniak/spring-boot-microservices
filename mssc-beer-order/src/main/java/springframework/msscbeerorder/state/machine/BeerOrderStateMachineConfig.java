package springframework.msscbeerorder.state.machine;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import springframework.msscbeerorder.domain.BeerOrderEventEnum;
import springframework.msscbeerorder.domain.BeerOrderStatusEnum;

import java.util.EnumSet;

@Configuration
@EnableStateMachineFactory
public class BeerOrderStateMachineConfig extends StateMachineConfigurerAdapter<BeerOrderStatusEnum,
		BeerOrderEventEnum> {
	@Override
	public void configure(
			StateMachineStateConfigurer<BeerOrderStatusEnum, BeerOrderEventEnum> states) throws Exception {
		states.withStates()
		      .initial(BeerOrderStatusEnum.NEW)
		      .states(EnumSet.allOf(BeerOrderStatusEnum.class))
		      .end(BeerOrderStatusEnum.DELIVERED)
		      .end(BeerOrderStatusEnum.DELIVERY_EXCEPTION)
		      .end(BeerOrderStatusEnum.VALIDATION_EXCEPTION)
		      .end(BeerOrderStatusEnum.ALLOCATION_EXCEPTION)
		      .end(BeerOrderStatusEnum.PICKED_UP);
	}

	@Override
	public void configure(
			StateMachineTransitionConfigurer<BeerOrderStatusEnum, BeerOrderEventEnum> transitions) throws Exception {
		transitions.withExternal()
		           .source(BeerOrderStatusEnum.NEW)
		           .target(BeerOrderStatusEnum.VALIDATION_PENDING)
		           .event(BeerOrderEventEnum.VALIDATE_ORDER)
		           .and()
		           .withExternal()
		           .source(BeerOrderStatusEnum.NEW)
		           .target(BeerOrderStatusEnum.VALIDATED)
		           .event(BeerOrderEventEnum.VALIDATION_PASSED)
		           .and()
		           .withExternal()
		           .source(BeerOrderStatusEnum.NEW)
		           .target(BeerOrderStatusEnum.VALIDATION_EXCEPTION)
		           .event(BeerOrderEventEnum.VALIDATION_FAILED)
		           .and();
	}

}
