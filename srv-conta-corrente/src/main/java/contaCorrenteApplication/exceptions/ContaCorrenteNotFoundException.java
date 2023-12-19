package contaCorrenteApplication.exceptions;

public class ContaCorrenteNotFoundException extends RuntimeException{

    public ContaCorrenteNotFoundException(){
    }
    public ContaCorrenteNotFoundException(String message){
        super(message);
    }
}
