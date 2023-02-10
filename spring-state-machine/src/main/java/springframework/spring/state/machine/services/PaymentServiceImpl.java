package springframework.spring.state.machine.services;

import org.springframework.statemachine.StateMachine;
import springframework.spring.state.machine.domain.Payment;
import springframework.spring.state.machine.domain.PaymentEvent;
import springframework.spring.state.machine.domain.PaymentState;

public class PaymentServiceImpl implements PaymentService {
	@Override
	public Payment newPayment(Payment payment) {
		return null;
	}

	@Override
	public StateMachine<PaymentState, PaymentEvent> preAuth(Long paymentId) {
		return null;
	}

	@Override
	public StateMachine<PaymentState, PaymentEvent> authorizePayment(Long paymentId) {
		return null;
	}

	@Override
	public StateMachine<PaymentState, PaymentEvent> declineAuth(Long paymentId) {
		return null;
	}
}
