package Project_SpringRestFul.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "CLIENTE_PEDIDO")
public class Pedido {

    private @Id @GeneratedValue Long id;
    private String descicao;
    private Status status;

    public Pedido() {
    }

    public Pedido(String descicao, Status status) {
        this.descicao = descicao;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pedido pedido)) return false;
        return Objects.equals(id, pedido.id) && Objects.equals(descicao, pedido.descicao) && Objects.equals(status, pedido.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descicao, status);
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", descicao='" + descicao + '\'' +
                ", status=" + status +
                '}';
    }
}
