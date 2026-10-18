package ca.arshjudge.telecom.repository;
import ca.arshjudge.telecom.domain.ServiceOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, Long> {
    List<ServiceOrder> findByCustomerId(Long customerId);
}
