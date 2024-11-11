package Project_SpringRestFul.controller;

import Project_SpringRestFul.assembler.TeacherModelAssembler;
import Project_SpringRestFul.error.TeacherNotFoundException;
import Project_SpringRestFul.model.Teacher;
import Project_SpringRestFul.repository.TeacherRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A criacao desta classe foi o meu quinto passo. Essa é a classe que encapsula o repositorio em camadas WEB, gerencia entidades, e contem os metodos que
 * irao retonar os dados diretamente em formato JSON ou XML.
 */

//Essa anotacao diz ao Spring que eh classe controladora Rest, ou seja, ela quem manipula requiscoes Htpp, GET, POST, DELETE...
@RestController
public class TeacherController {
    //Instanciando um objeto para buscar os metodos da interface JpaRepository
    private final TeacherRepository repository;
    //Instancia do assembler para acessar a classe de conversao/transformacao de dados entre as camadas
    private final TeacherModelAssembler assembler;


    public TeacherController(TeacherRepository repository, TeacherModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    /**
     * Definindo um endpoint GET para a rota(/teachers).Quando um cliente fizer a solicitacao GET para esse endpoint, este metod sera chamado.
     * Este metodo retorna uma lista de todos os professores que estao cadastrados no sistema, cada um com seu respectivo link
     *
     * @return retorna uma colecao de modelos(CollectionModel) de professores, onde cada modelo de uma entidade(EntityModel) contem os dados de
     * professor e um link auto-referencial para o proprio recurso
     */
    @GetMapping("/teachers")
    public CollectionModel<EntityModel<Teacher>> all() {
        List<EntityModel<Teacher>> teachers = repository.findAll().stream().map(assembler::toModel).collect(Collectors.toList());

        return CollectionModel.of(teachers, linkTo(methodOn(TeacherController.class).all()).withSelfRel());
    }

    /**
     * Definindo um endpoint para a rota de um professor especifcado pelo seu id.Quando um cliente fizer uma solicitacao GET para esse endipoint, passando um id como parametro especifico,
     * o metodo ira buscar aquele professor com o id especificado.
     * Caso o professor nao seja encontrado, ele lanca um excecao TeacherNotFoundException, que sera tratada em uma classe especifca* @param id
     * @return
     */
    @GetMapping("/teachers/{id}")
    public EntityModel<Teacher> one(@PathVariable Long id){
        Teacher teacher = repository.findById(id).orElseThrow(() -> new TeacherNotFoundException(id));
        return assembler.toModel(teacher);

    }

    /**
     * Definindo um endpoint para a rota(/teachers).Esse metodo serve para criar um novo professor.Esse metodo eh chamado quando um cliente
     * envia uma solicitacao para criar um novo professor e entao, o sistema recebe os dados passados no corpo da requisicao e persiste
     * essa informacao no banco de dados
     */
    @PostMapping("/teachers")
    ResponseEntity<?> newTeacher(@RequestBody Teacher newTeacher) {
        //Salva o professor no banco de dados e adiciona link hateoas
        EntityModel<Teacher> entityModel = assembler.toModel(repository.save(newTeacher));
        //Retorna um status 201 e o link do novo professor na resposta
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    /**
     * Definindo um endpoint para a rota(/teachers/id)Esse metodo serve para atualizar/mudar o valor de estado de um atributo de um professor especifico pelo ID
     * Metodo chamado quando um cliente envia uma solicitacao para atualizar um professor e entao, o sistema recebe a requsicao, atualiza com os dados passsados e
     * persiste no banco de dados.
     * Caso o id nao for encontrado,o novo professor é salvo como um novo registro
     * @param newTeacher
     * @param id
     * @return
     */
    @PutMapping("/teachers/{id}")
    ResponseEntity<?> replaceTeacher(@RequestBody Teacher newTeacher, @PathVariable Long id) {
        Teacher atualizarTeacher = repository.findById(id).map(teacher -> {
            teacher.setNome(newTeacher.getNome());
            teacher.setDisciplina(newTeacher.getDisciplina());
            teacher.setSobrenome(newTeacher.getSobrenome()); // Verifique se o sobrenome está sendo atualizado
            return repository.save(teacher);
        }).orElseThrow(() -> new TeacherNotFoundException(id));
        EntityModel<Teacher> entityModel = assembler.toModel(atualizarTeacher);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF)
                        .toUri()).body(entityModel);
    }

    /**
     * Esta é a rota que ira deletar um professor quando o cliente enviar a solicitacao passando como parametro um id especifico.
     * @return O metodo no content, eh um metodo estatico e cria uma instancia de ResponseEntity com o status Http 204 No Content, que indica qeu a operacoa foi realizada com sucesso
     * mas que nao possui dados para enviar de volta ao cliente
     */
    @DeleteMapping("/teachers/{id}")
    ResponseEntity<?> deleteTeacher(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    }






