package io.catapp.shoppinglist.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ShoppingListIdException extends RuntimeException {
    public ShoppingListIdException(String message) {super(message);}
}
