package springframework.spring.state.machine.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.guard.Guard;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import springframework.spring.state.machine.domain.PaymentEvent;
import springframework.spring.state.machine.domain.PaymentState;
import springframework.spring.state.machine.services.PaymentServiceImpl;

import java.util.EnumSet;
import java.util.Random;

@Slf4j
@EnableStateMachineFactory
@Configuration
public class StateMachineConfig extends StateMachineConfigurerAdapter<PaymentState, PaymentEvent> {

	@Override
	public void configure(StateMachineConfigurationConfigurer<PaymentState, PaymentEvent> config) throws Exception {
		StateMachineListenerAdapter<PaymentState, PaymentEvent> adapter = new StateMachineListenerAdapter<>() {
			@Override
			public void stateChanged(State<PaymentState, PaymentEvent> from, State<PaymentState, PaymentEvent> to) {
				log.info("State changed from {} to {}", from, to);
			}
		};
		config.withConfiguration()
		      .listener(adapter);
	}

	@Override
	public void configure(StateMachineStateConfigurer<PaymentState, PaymentEvent> states) throws Exception {
		states.withStates()
		      .initial(PaymentState.NEW)
		      .states(EnumSet.allOf(PaymentState.class))
		      .end(PaymentState.AUTH)
		      .end(PaymentState.PRE_AUTH_ERROR)
		      .end(PaymentState.AUTH_ERROR);
	}

	@Override
	public void configure(StateMachineTransitionConfigurer<PaymentState, PaymentEvent> transitions) throws Exception {
		transitions.withExternal()
		           .source(PaymentState.NEW)
		           .target(PaymentState.NEW)
		           .event(PaymentEvent.PRE_AUTHORIZE)
		           .action(preAuthAction())
		           .guard(paymentIdGuard())
		           .and()
		           .withExternal()
		           .source(PaymentState.NEW)
		           .target(PaymentState.PRE_AUTH)
		           .event(PaymentEvent.PRE_AUTH_APPROVED)
		           .and()
		           .withExternal()
		           .source(PaymentState.NEW)
		           .target(PaymentState.PRE_AUTH_ERROR)
		           .event(PaymentEvent.PRE_AUTH_DECLINED)
		           .and()
		           .withExternal()
		           .source(PaymentState.PRE_AUTH)
		           .target(PaymentState.PRE_AUTH)
		           .event(PaymentEvent.AUTHORIZE)
		           .action(authAction())
		           .and()
		           .withExternal()
		           .source(PaymentState.PRE_AUTH)
		           .target(PaymentState.AUTH)
		           .event(PaymentEvent.AUTH_APPROVED)
		           .and()
		           .withExternal()
		           .source(PaymentState.PRE_AUTH)
		           .target(PaymentState.AUTH_ERROR)
		           .event(PaymentEvent.AUTH_DECLINED);
	}

	public Action<PaymentState, PaymentEvent> preAuthAction() {
		return context -> {
			log.debug("PreAuth was called");
			if (new Random().nextInt(10) < 8) {
				log.debug("Approved");
				context.getStateMachine()
				       .sendEvent(MessageBuilder.withPayload(PaymentEvent.PRE_AUTH_APPROVED)
				                                .setHeader(PaymentServiceImpl.PAYMENT_ID_HEADER,
				                                           context.getMessageHeader(
						                                           PaymentServiceImpl.PAYMENT_ID_HEADER))
				                                .build());
			} else {
				log.debug("Declined! No Credit!");
				context.getStateMachine()
				       .sendEvent(MessageBuilder.withPayload(PaymentEvent.PRE_AUTH_DECLINED)
				                                .setHeader(PaymentServiceImpl.PAYMENT_ID_HEADER,
				                                           context.getMessageHeader(
						                                           PaymentServiceImpl.PAYMENT_ID_HEADER))
				                                .build());
			}
		};
	}

	public Guard<PaymentState, PaymentEvent> paymentIdGuard() {
		return context -> context.getMessageHeader(PaymentServiceImpl.PAYMENT_ID_HEADER) != null;
	}

	public Action<PaymentState, PaymentEvent> authAction() {
		return context -> {
			log.debug("Auth was called");
			if (new Random().nextInt(10) < 8) {
				log.debug("Auth Approved");
				context.getStateMachine()
				       .sendEvent(MessageBuilder.withPayload(PaymentEvent.AUTH_APPROVED)
				                                .setHeader(PaymentServiceImpl.PAYMENT_ID_HEADER,
				                                           context.getMessageHeader(
						                                           PaymentServiceImpl.PAYMENT_ID_HEADER))
				                                .build());
			} else {
				log.debug("Auth Declined! No Credit!");
				context.getStateMachine()
				       .sendEvent(MessageBuilder.withPayload(PaymentEvent.AUTH_DECLINED)
				                                .setHeader(PaymentServiceImpl.PAYMENT_ID_HEADER,
				                                           context.getMessageHeader(
						                                           PaymentServiceImpl.PAYMENT_ID_HEADER))
				                                .build());
			}
		};
	}
}
