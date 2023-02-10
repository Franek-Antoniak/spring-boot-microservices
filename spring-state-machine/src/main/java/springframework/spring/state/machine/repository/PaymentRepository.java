package springframework.spring.state.machine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springframework.spring.state.machine.domain.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
