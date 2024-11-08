package Project_SpringRestFul.assembler;

import Project_SpringRestFul.controller.PedidoController;
import Project_SpringRestFul.model.Pedido;
import Project_SpringRestFul.model.Status;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class PedidoModelAssembler implements RepresentationModelAssembler<Pedido, EntityModel<Pedido>> {

    @Override
    public EntityModel<Pedido> toModel(Pedido pedido){

        EntityModel<Pedido> pedidoModel = EntityModel.of(pedido,
                linkTo(methodOn(PedidoController.class).one(pedido.getId())).withSelfRel(),
                linkTo(methodOn(PedidoController.class).all()).withRel("pedidos"));

        if(pedido.getStatus() == Status.IN_PROGRESS){
            pedidoModel.add(linkTo(methodOn(PedidoController.class).cancel(pedido.getId())).withRel("cancel"));
            pedidoModel.add(linkTo(methodOn(PedidoController.class).complete(pedido.getId())).withRel("complete"));

        }
        return pedidoModel;
    }
}
