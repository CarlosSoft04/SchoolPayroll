package Project_SpringRestFul.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Teacher {

    private @Id
    @GeneratedValue Long id;
    private String nome;
    private String sobrenome;
    private String disciplina;

    public Teacher() {
    }

    public Teacher(String nome,String sobrenome, String disciplina) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.disciplina = disciplina;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.nome + " " + this.sobrenome;
    }

    public void setName(String nome) {
    String[] parts = nome.split(" ");
    this.nome = parts[0];
    this.sobrenome = parts[1];
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teacher teacher)) return false;
        return Objects.equals(id, teacher.id) && Objects.equals(nome, teacher.nome) && Objects.equals(sobrenome, teacher.sobrenome) && Objects.equals(disciplina, teacher.disciplina);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, sobrenome, disciplina);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", disciplina='" + disciplina + '\'' +
                '}';
    }
}
