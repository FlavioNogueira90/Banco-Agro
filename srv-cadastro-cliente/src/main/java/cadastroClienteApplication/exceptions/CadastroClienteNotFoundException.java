package cadastroClienteApplication.exceptions;

public class CadastroClienteNotFoundException extends RuntimeException{

    public CadastroClienteNotFoundException(){
    }
    public CadastroClienteNotFoundException(String message){
        super(message);
    }
}
