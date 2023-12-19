package cadastroClienteApplication.exceptions;

public class MySQLIntegrityConstraintViolationException extends RuntimeException{

    public MySQLIntegrityConstraintViolationException(){
    }
    public MySQLIntegrityConstraintViolationException(String message){
        super(message);
    }
}

