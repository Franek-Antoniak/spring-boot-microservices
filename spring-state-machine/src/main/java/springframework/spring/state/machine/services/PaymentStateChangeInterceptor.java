package springframework.spring.state.machine.services;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;
import springframework.spring.state.machine.domain.Payment;
import springframework.spring.state.machine.domain.PaymentEvent;
import springframework.spring.state.machine.domain.PaymentState;
import springframework.spring.state.machine.repository.PaymentRepository;

import java.util.Optional;

import static springframework.spring.state.machine.services.PaymentServiceImpl.PAYMENT_ID_HEADER;

@Component
@RequiredArgsConstructor
public class PaymentStateChangeInterceptor extends StateMachineInterceptorAdapter<PaymentState, PaymentEvent> {
	private final PaymentRepository paymentRepository;

	@Override
	public void preStateChange(State<PaymentState, PaymentEvent> state, Message<PaymentEvent> message,
			Transition<PaymentState, PaymentEvent> transition, StateMachine<PaymentState, PaymentEvent> stateMachine) {
		Optional<Long> paymentId = Optional.ofNullable(message)
		                                   .map(msg -> (Long) msg.getHeaders()
		                                                         .getOrDefault(PAYMENT_ID_HEADER, -1));
		paymentId.ifPresent(id -> {
			Payment payment = paymentRepository.findById(id)
			                                   .orElseThrow();
			payment.setState(state.getId());
			paymentRepository.save(payment);
		});
	}
}
