package io.catapp.shoppinglist.repositories;

import io.catapp.shoppinglist.domain.ShoppingList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingListRepository extends CrudRepository<ShoppingList, Long> {

    //@Override
    //ShoppingList findById(Long id);

    @Override
    Iterable<ShoppingList> findAll();
}
