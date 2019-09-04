package io.catapp.shoppinglist.exceptions;

public class ShoppingListExceptionResponse {
    private String shoppingListId;

    public ShoppingListExceptionResponse (String shoppingListId){
        this.shoppingListId = shoppingListId;
    };

    public String getShoppingListId() {
        return shoppingListId;
    }

    public void setShoppingListId(String shoppingListId) {
        this.shoppingListId = shoppingListId;
    }
}
