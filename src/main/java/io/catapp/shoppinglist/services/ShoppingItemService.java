package io.catapp.shoppinglist.services;

import io.catapp.shoppinglist.domain.ShoppingItem;
import io.catapp.shoppinglist.repositories.ShoppingItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingItemService {

    @Autowired
    private ShoppingItemRepository shoppingItemRepository;

    public ShoppingItem saveOrUpdateShoppingItem(ShoppingItem item, long shoppingListId) {
        item.setListId(shoppingListId);
        return shoppingItemRepository.save(item);
    }

    public ShoppingItem findById(Long id){
        return shoppingItemRepository.findById(id).get();
    }

    public Iterable<ShoppingItem> findAllByListId(Long listId){
        return  shoppingItemRepository.findByListId(listId);
    }

    public void deleteById(Long id){
        ShoppingItem item = shoppingItemRepository.findById(id).get();
        shoppingItemRepository.delete(item);
    }

}
