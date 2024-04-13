package Exceptions;

public class ErrorApiKeyUrlException extends RuntimeException{
    private String mensagem;
    public ErrorApiKeyUrlException(String mensagem){
     this.mensagem = mensagem;
    }

    @Override
    public String getMessage() {
        return this.mensagem;
    }
}
