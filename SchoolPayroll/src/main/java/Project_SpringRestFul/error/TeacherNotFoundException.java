package Project_SpringRestFul.error;

 public class TeacherNotFoundException extends RuntimeException{

         public TeacherNotFoundException(Long id){
        super("Professor nao encontrado. id: " + id );
    }
}
