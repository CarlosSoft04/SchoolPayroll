package Project_SpringRestFul.controller;

import Project_SpringRestFul.error.TeacherNotFoundException;
import Project_SpringRestFul.model.Teacher;
import Project_SpringRestFul.repository.TeacherRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeacherController {
    private final TeacherRepository repository;

    public TeacherController(TeacherRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/teachers")
    List<Teacher> all(){
        return repository.findAll();
    }

    @GetMapping("/teachers/{id}")
    Teacher one(@PathVariable Long id){
        return repository.findById(id).orElseThrow(() -> new TeacherNotFoundException(id));
    }

    @PostMapping("/teachers")
    Teacher newTeacher(@RequestBody Teacher newTeacher){
        return repository.save(newTeacher);
    }

    @PutMapping("/teachers/{id}")
    Teacher replaceTeacher(@RequestBody Teacher newTeacher, @PathVariable Long id) {
        return repository.findById(id)
                .map(teacher -> {

                    teacher.setName(newTeacher.getName());
                    teacher.setRole(newTeacher.getRole());

                    return repository.save(teacher);
                })

                .orElseGet(() -> {
                    return repository.save(newTeacher);
                });
    }

    @DeleteMapping("/teachers/{id}")
    void deleteTeacher(@PathVariable Long id){
        repository.deleteById(id);
    }

}




