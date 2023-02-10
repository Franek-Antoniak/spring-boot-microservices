package springframework.spring.state.machine.services;

import org.springframework.statemachine.StateMachine;
import springframework.spring.state.machine.domain.Payment;
import springframework.spring.state.machine.domain.PaymentEvent;
import springframework.spring.state.machine.domain.PaymentState;

public interface PaymentService {
	Payment newPayment(Payment payment);

	StateMachine<PaymentState, PaymentEvent> preAuth(Long paymentId);

	StateMachine<PaymentState, PaymentEvent> authorizePayment(Long paymentId);

	StateMachine<PaymentState, PaymentEvent> declineAuth(Long paymentId);
}
