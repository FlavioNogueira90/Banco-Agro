package br.com.bancoagro.srvaberturaconta.exceptions;

public class AberturaContaNotFoundException extends RuntimeException{

    public AberturaContaNotFoundException(){
    }
    public AberturaContaNotFoundException(String message){
        super(message);
    }
}
