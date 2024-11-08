package Project_SpringRestFul.error;

public class PedidoNotFoundException extends  RuntimeException {
    public PedidoNotFoundException(Long id){
        super("Pedido nao encontrado: " + id);

    }
}
