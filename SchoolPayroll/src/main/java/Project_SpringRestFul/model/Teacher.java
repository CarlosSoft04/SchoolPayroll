package Project_SpringRestFul.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;

/**
 * Essa classe representa as tabelas do banco de dados, ou seja, ela mapeia as tabelas do nosso banco de dados para objetos java.
 * Foi por onde eu comecei o sistema, pois com ela, ja posso partir para a construcap de outras camadas como (repository, controller).
 * Aloquei ela dentro do pacote 'model' por principios clean code.
 *
 * */

//Anotacao para dizer que essa classe irá armazenar dados.Classe gerenciado pelo JPA.
@Entity
public class Teacher {

    //Atributo Id marcado como unico(@Id) e gerado automaticamente(@Generated)
    private @Id @GeneratedValue Long id;
    private String nome;
    private String sobrenome;
    private String disciplina;

    //Construtor padrao e um construtor com atributos
    public Teacher() {
    }
    public Teacher(String nome, String sobrenome, String disciplina) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.disciplina = disciplina;
    }

    //Metodos getters e setters, equals, hashAndCode e toString
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

    public String getNome() {
        return this.nome;  // Retorna o nome sem alterações, como você deseja
    }

    public void setNome(String nome) {
        String[] parts = nome.split(" ", 2);  // Limita a divisão para 2 partes
        this.nome = parts[0];  // Primeiro nome
        this.sobrenome = parts.length > 1 ? parts[1] : "";  // Sobrenome, se houver
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
        return Objects.equals(id, teacher.id) &&
                Objects.equals(nome, teacher.nome) &&
                Objects.equals(sobrenome, teacher.sobrenome) &&
                Objects.equals(disciplina, teacher.disciplina);
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
