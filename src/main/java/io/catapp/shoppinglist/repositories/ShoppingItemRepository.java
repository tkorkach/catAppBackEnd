package io.catapp.shoppinglist.repositories;

import io.catapp.shoppinglist.domain.ShoppingItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingItemRepository extends CrudRepository<ShoppingItem, Long> {

    @Override
    Iterable<ShoppingItem> findAll();

    List<ShoppingItem> findByListId(Long listId);
}
