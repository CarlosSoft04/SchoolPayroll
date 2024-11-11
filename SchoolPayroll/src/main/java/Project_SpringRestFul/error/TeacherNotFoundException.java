package Project_SpringRestFul.error;

/**
 * Depois de criar a minha classe Controladora para a entidade 'Teacher' eu crio essa classe para tartar o erro de excecao caso o professor nao for achado
 * Eu crio separadamente esta classe para obter uma resposta clara e consistene sobre o erro, retornando o mesmo para o cliente.
 * Alem disso, essa tecnica, centraliza a logica de erro em um so local, facilitando manutencao e correcao de codigo
 */
 public class TeacherNotFoundException extends RuntimeException{

         public TeacherNotFoundException(Long id){
        super("Professor nao encontrado. id: " + id );
    }
}
