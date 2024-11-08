package Project_SpringRestFul.repository;

import Project_SpringRestFul.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
