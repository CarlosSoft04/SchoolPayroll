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

@RestController
public class TeacherController {
    private final TeacherRepository repository;
    private final TeacherModelAssembler assembler;


    public TeacherController(TeacherRepository repository, TeacherModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/teachers")
    public CollectionModel<EntityModel<Teacher>> all() {
        List<EntityModel<Teacher>> teachers = repository.findAll().stream().map(assembler::toModel).collect(Collectors.toList());

        return CollectionModel.of(teachers, linkTo(methodOn(TeacherController.class).all()).withSelfRel());
    }

    @GetMapping("/teachers/{id}")
    public EntityModel<Teacher> one(@PathVariable Long id){
        Teacher teacher = repository.findById(id).orElseThrow(() -> new TeacherNotFoundException(id));
        return assembler.toModel(teacher);

    }

    @PostMapping("/teachers")
    ResponseEntity<?> newTeacher(@RequestBody Teacher newTeacher) {
        EntityModel<Teacher> entityModel = assembler.toModel(repository.save(newTeacher));

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @PutMapping("/teachers/{id}")
    ResponseEntity<?> replaceTeacher(@RequestBody Teacher newTeacher, @PathVariable Long id) {
        Teacher atualizarTeacher = repository.findById(id).map(teacher -> {
            teacher.setName(newTeacher.getName());
            teacher.setDisciplina(newTeacher.getDisciplina());
            return repository.save(teacher);
        }).orElseGet(() -> {
            return repository.save(newTeacher);
        });

        EntityModel<Teacher> entityModel = assembler.toModel(atualizarTeacher);

        return  ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF)
                        .toUri()).body(entityModel);




        }
    @DeleteMapping("/teachers/{id}")
    ResponseEntity<?> deleteTeacher(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    }






