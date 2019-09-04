package io.catapp.shoppinglist.services;

import io.catapp.shoppinglist.domain.ShoppingList;
import io.catapp.shoppinglist.exceptions.ShoppingListIdException;
import io.catapp.shoppinglist.repositories.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingListService {

    @Autowired
    private ShoppingListRepository shoppingListRepository;

    public ShoppingList saveOrUpdateShoppingList(ShoppingList project) {
        return shoppingListRepository.save(project);
    }

    public ShoppingList findById(Long id){
        ShoppingList shoppingList =  shoppingListRepository.findById(id).get();

        if (shoppingList == null){
            throw new ShoppingListIdException("Shopping list ID " + id + " does not exists");
        }

        return shoppingList;
    }

    public Iterable<ShoppingList> findAll(){
        return  shoppingListRepository.findAll();
    }

    public void deleteById(Long id){
        ShoppingList project = shoppingListRepository.findById(id).get();

        if (project == null){
            throw new ShoppingListIdException("cannot delete shopping list with ID " + id + ", this shopping list does not exists");
        }

        shoppingListRepository.delete(project);
    }

}
