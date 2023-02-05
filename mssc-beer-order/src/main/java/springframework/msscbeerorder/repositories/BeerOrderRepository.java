package springframework.msscbeerorder.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import springframework.msscbeerorder.domain.BeerOrder;
import springframework.msscbeerorder.domain.Customer;
import springframework.msscbeerorder.domain.OrderStatusEnum;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface BeerOrderRepository extends JpaRepository<BeerOrder, UUID> {

    Page<BeerOrder> findAllByCustomer(Customer customer, Pageable pageable);

    List<BeerOrder> findAllByOrderStatus(OrderStatusEnum orderStatusEnum);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    BeerOrder findOneById(UUID id);

    Optional<BeerOrder> findByIdAndCustomer_Id(UUID orderId, UUID customerId);
}
