package br.com.stoom.store.exceptions;

public class CategoryAlreadyExistsException extends RuntimeException{
    public CategoryAlreadyExistsException() {super("Category already exists.");}

    public CategoryAlreadyExistsException(String message){super(message);}
}
