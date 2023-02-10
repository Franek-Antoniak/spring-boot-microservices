package springframework.spring.state.machine.services;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import springframework.spring.state.machine.domain.Payment;
import springframework.spring.state.machine.domain.PaymentEvent;
import springframework.spring.state.machine.domain.PaymentState;
import springframework.spring.state.machine.repository.PaymentRepository;

@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
	public static final String PAYMENT_ID_HEADER = "payment_id";

	private final PaymentRepository paymentRepository;
	private final StateMachineFactory<PaymentState, PaymentEvent> stateMachineFactory;

	@Override
	public Payment newPayment(Payment payment) {
		payment.setState(PaymentState.NEW);
		return paymentRepository.save(payment);
	}

	@Override
	public StateMachine<PaymentState, PaymentEvent> preAuth(Long paymentId) {
		StateMachine<PaymentState, PaymentEvent> sm = build(paymentId);
		sendEvent(paymentId, sm, PaymentEvent.PRE_AUTHORIZE);
		return sm;
	}

	@Override
	public StateMachine<PaymentState, PaymentEvent> authorizePayment(Long paymentId) {
		StateMachine<PaymentState, PaymentEvent> sm = build(paymentId);
		sendEvent(paymentId, sm, PaymentEvent.AUTH_APPROVED);
		return sm;
	}

	@Override
	public StateMachine<PaymentState, PaymentEvent> declineAuth(Long paymentId) {
		StateMachine<PaymentState, PaymentEvent> sm = build(paymentId);
		sendEvent(paymentId, sm, PaymentEvent.AUTH_DECLINED);
		return sm;
	}

	private void sendEvent(Long paymentId, StateMachine<PaymentState, PaymentEvent> sm, PaymentEvent event) {
		Message<PaymentEvent> msg = MessageBuilder.withPayload(event)
		                                          .setHeader(PAYMENT_ID_HEADER, paymentId)
		                                          .build();
		sm.sendEvent(msg);
	}

	private StateMachine<PaymentState, PaymentEvent> build(Long paymentId) {
		Payment payment = paymentRepository.findById(paymentId)
		                                   .orElseThrow();
		StateMachine<PaymentState, PaymentEvent> sm = stateMachineFactory.getStateMachine(
				Long.toString(payment.getId()));
		sm.stop();
		sm.getStateMachineAccessor()
		  .doWithAllRegions(sma -> {
			  sma.resetStateMachine(new DefaultStateMachineContext<>(payment.getState(), null, null, null));
		  });
		sm.start();
		return sm;
	}
}
