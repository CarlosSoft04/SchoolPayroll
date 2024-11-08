package Project_SpringRestFul.controller;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import Project_SpringRestFul.assembler.PedidoModelAssembler;
import Project_SpringRestFul.error.PedidoNotFoundException;
import Project_SpringRestFul.model.Pedido;
import Project_SpringRestFul.model.Status;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Project_SpringRestFul.repository.PedidoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PedidoController {
    private final PedidoRepository repository;
    private final PedidoModelAssembler assembler;


    public PedidoController(PedidoRepository repository, PedidoModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }@GetMapping("/pedidos")
    public CollectionModel<EntityModel<Pedido>> all() {

        List<EntityModel<Pedido>> pedidos = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(pedidos, //
                linkTo(methodOn(PedidoController.class).all()).withSelfRel());
    }

    @GetMapping("/pedidos/{id}")
    public EntityModel<Pedido> one(@PathVariable Long id) {

        Pedido pedido = repository.findById(id) //
                .orElseThrow(() -> new PedidoNotFoundException(id));

        return assembler.toModel(pedido);
    }

    @DeleteMapping("/pedidos/{id}/cancel")
    public ResponseEntity<?> cancel(@PathVariable Long id) {
        Pedido pedido = repository.findById(id).orElseThrow(() -> new PedidoNotFoundException(id));

        if(pedido.getStatus() == Status.IN_PROGRESS){
            pedido.setStatus(Status.CANCELLED);
            return ResponseEntity.ok(assembler.toModel(repository.save(pedido)));

        }

        return  ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(Problem.create()
                        .withTitle("Metodo permitido").withDetail("Nao pode cancelar pedido no status de: " + pedido.getStatus()));
    }

    @PutMapping("/pedidos/{id}/complete")
    public ResponseEntity<?> complete(@PathVariable Long id){
        Pedido pedido = repository.findById(id).orElseThrow(() -> new PedidoNotFoundException(id));

        if(pedido.getStatus() == Status.IN_PROGRESS){
            pedido.setStatus(Status.COMPLETED);
            return  ResponseEntity.ok(assembler.toModel(repository.save(pedido)));
        }

        return  ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).header(HttpHeaders.CONTENT_TYPE,MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(Problem.create().withTitle("Metodo nao permitido").withDetail("Nao pode completar o metodo com essse status" + pedido.getStatus()));

    }

}

