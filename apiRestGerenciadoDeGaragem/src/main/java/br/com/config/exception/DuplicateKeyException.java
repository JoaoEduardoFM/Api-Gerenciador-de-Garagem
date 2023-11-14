package br.com.config.exception;

public class DuplicateKeyException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public DuplicateKeyException(String msg){
        super(msg);
    }

    public DuplicateKeyException(String msg, Throwable cause){
        super(msg,cause);
    }

}
