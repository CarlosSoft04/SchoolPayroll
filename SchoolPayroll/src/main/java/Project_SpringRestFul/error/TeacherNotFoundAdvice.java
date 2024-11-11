package Project_SpringRestFul.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Apos a criacao da minha classe de erro(TeacherNotFoundException), eu crio esta classe advice para ter uma exccecao personalizada
 * e essa classe com a anotacao (@RestControllerAdvice) torna isso mais robusto e facilita a comunicacao de erro com o cliente
 *
 * O TeacherNotFoundException identifica o problema específico no código,
 * enquanto o ExceptionAdvice gera uma resposta apropriada (com código de status e mensagem) para o cliente.
 */

//Captura excecoes especifcias ou genericas lancadas por qualquer controlador na aplicacao
@RestControllerAdvice
public class TeacherNotFoundAdvice {

    @ExceptionHandler(TeacherNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String teacherNotFoundHandler(TeacherNotFoundException ex){
        return ex.getMessage();

    }
}
