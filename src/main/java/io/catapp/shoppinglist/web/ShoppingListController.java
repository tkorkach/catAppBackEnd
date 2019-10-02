package io.catapp.shoppinglist.web;

import io.catapp.shoppinglist.domain.ShoppingItem;
import io.catapp.shoppinglist.domain.ShoppingList;
import io.catapp.shoppinglist.services.MapValidationErrorService;
import io.catapp.shoppinglist.services.ShoppingItemService;
import io.catapp.shoppinglist.services.ShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/catsShoppingList")
@CrossOrigin
public class ShoppingListController {

    @Autowired
    private ShoppingListService shoppingListService;

    @Autowired
    private ShoppingItemService shoppingItemService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;


    @PostMapping("")
    public ResponseEntity<?> createNewList(@RequestBody @Valid ShoppingList shoppingList, BindingResult result) {

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null) return errorMap;

        ShoppingList shoppingList1 = shoppingListService.saveOrUpdateShoppingList(shoppingList);
        return new ResponseEntity<ShoppingList>(shoppingList, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getShoppingListById(@PathVariable Long id){
        ShoppingList shoppingList = shoppingListService.findById(id);
        return new ResponseEntity<ShoppingList>(shoppingList, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllShoppingLists(){
        Iterable<ShoppingList> projects = shoppingListService.findAll();
        return new ResponseEntity<Iterable<ShoppingList>>(projects, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable Long id){
        shoppingListService.deleteById(id);

        return new ResponseEntity<String>("Shopping list with id " + id + " was deleted", HttpStatus.OK );
    }

    @PostMapping("{id}/item")
    public ResponseEntity<?> createNewItem(@RequestBody ShoppingItem shoppingItem,@PathVariable Long id, BindingResult result) {

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null) return errorMap;

        ShoppingItem shoppingItem1 = shoppingItemService.saveOrUpdateShoppingItem(shoppingItem, id);
        return new ResponseEntity<ShoppingItem>(shoppingItem, HttpStatus.CREATED);
    }

    @GetMapping("{id}/allItems")
    public ResponseEntity<?> getAllItems(@PathVariable Long id){
        Iterable<ShoppingItem> items = shoppingItemService.findAllByListId(id);
        return new ResponseEntity<Iterable<ShoppingItem>>(items, HttpStatus.OK);
    }

    @DeleteMapping("/item/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable Long id){
        shoppingItemService.deleteById(id);

        return new ResponseEntity<String>("Shopping item with id " + id + " was deleted", HttpStatus.OK );
    }
}
