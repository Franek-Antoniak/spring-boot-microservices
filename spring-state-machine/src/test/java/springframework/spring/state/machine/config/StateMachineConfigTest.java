package springframework.spring.state.machine.config;

import org.junit.jupiter.api.RepeatedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import springframework.spring.state.machine.domain.PaymentEvent;
import springframework.spring.state.machine.domain.PaymentState;

@SpringBootTest
class StateMachineConfigTest {
	@Autowired
	StateMachineFactory<PaymentState, PaymentEvent> factory;

	@RepeatedTest(10)
	void testNewStateMachine() {
		StateMachine<PaymentState, PaymentEvent> sm = factory.getStateMachine();
		sm.start();

		System.out.println(sm.getState());

		sm.sendEvent(PaymentEvent.PRE_AUTHORIZE);

		System.out.println(sm.getState());

		sm.sendEvent(PaymentEvent.PRE_AUTH_APPROVED);

		System.out.println(sm.getState());

		sm.sendEvent(PaymentEvent.AUTHORIZE);

		System.out.println(sm.getState());

		sm.sendEvent(PaymentEvent.AUTH_APPROVED);

		System.out.println(sm.getState());
	}
}