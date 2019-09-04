package io.catapp.shoppinglist.web;

import io.catapp.shoppinglist.domain.ShoppingList;
import io.catapp.shoppinglist.services.MapValidationErrorService;
import io.catapp.shoppinglist.services.ShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/catsShoppingList")
public class ShoppingListController {

    @Autowired
    private ShoppingListService shoppingListService;

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
}
