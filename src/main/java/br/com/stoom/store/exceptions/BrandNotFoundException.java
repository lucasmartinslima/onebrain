package br.com.stoom.store.exceptions;


public class BrandNotFoundException extends RuntimeException{

    public BrandNotFoundException() {super("Brand not found!");}

    public BrandNotFoundException(String message){super(message);}

}
