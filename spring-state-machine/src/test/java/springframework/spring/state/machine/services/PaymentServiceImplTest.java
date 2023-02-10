package springframework.spring.state.machine.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import springframework.spring.state.machine.domain.Payment;
import springframework.spring.state.machine.repository.PaymentRepository;

import java.math.BigDecimal;

@SpringBootTest
class PaymentServiceImplTest {
	@Autowired
	PaymentService paymentService;

	Payment payment;

	@Autowired
	PaymentRepository paymentRepository;

	@BeforeEach
	void setUp() {
		payment = Payment.builder()
		                 .amount(new BigDecimal("12.99"))
		                 .build();
	}

	@Test
	void preAuth() {
		Payment savedPayment = paymentService.newPayment(payment);

		System.out.println("Should be NEW");
		System.out.println(savedPayment);

		paymentService.preAuth(savedPayment.getId());

		Payment preAuthedPayment = paymentRepository.findById(savedPayment.getId())
		                                            .orElseThrow();

		System.out.println("Should be PRE_AUTH or PRE_AUTH_ERROR");
		System.out.println(preAuthedPayment);
	}
}