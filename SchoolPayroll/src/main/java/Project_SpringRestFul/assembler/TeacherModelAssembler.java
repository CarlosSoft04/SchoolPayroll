package Project_SpringRestFul.assembler;

import Project_SpringRestFul.controller.TeacherController;
import Project_SpringRestFul.model.Teacher;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
 public class TeacherModelAssembler implements RepresentationModelAssembler<Teacher, EntityModel<Teacher>> {

    @Override
    public EntityModel<Teacher> toModel(Teacher teacher){


        return EntityModel.of(teacher,
                linkTo(methodOn(TeacherController.class).one(teacher.getId())).withSelfRel(),
                linkTo(methodOn(TeacherController.class).all()).withRel("teachers"));

    }
}
