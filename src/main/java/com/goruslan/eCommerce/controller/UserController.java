package com.goruslan.eCommerce.controller;

import com.goruslan.eCommerce.entity.Role;
import com.goruslan.eCommerce.entity.Transaction;
import com.goruslan.eCommerce.entity.User;
import com.goruslan.eCommerce.service.ProductService;
import com.goruslan.eCommerce.service.TransactionService;
import com.goruslan.eCommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.LocalDateTime;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private TransactionService transactionService;

    /**
     * @param user
     * @return ResponseEntity
     * Takes a user as a parameter and
     * returns CONFLICT status if the username is already taken.
     * Saves the new user otherwise and return the CREATED status.
     */
    // ResponseEntity capsulation contains Http header, Http status and response body.
    @PostMapping("/api/user/registration")
    public ResponseEntity<?> register(@RequestBody User user){
        if(userService.findByUsername(user.getUsername()) != null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        //default role.
        user.setRole(Role.USER);
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    /**
     * @param principal
     * @return ResponseEntity
     */
    @GetMapping("/api/user/login")
    public ResponseEntity<?> getUser(Principal principal){
        //principal = httpServletRequest.getUserPrincipal.
        if(principal == null || principal.getName() == null){
            //logout will also use here so we should return ok http status.
            return ResponseEntity.ok(principal);
        }
        return new ResponseEntity<>(userService.findByUsername(principal.getName()), HttpStatus.OK);
    }

    @PostMapping("/api/user/purchase")
    public ResponseEntity<?> purchaseProduct(@RequestBody Transaction transaction) {
        transaction.setPurchaseDate(LocalDateTime.now());
        return new ResponseEntity<>(transactionService.saveTransaction(transaction), HttpStatus.CREATED);
    }

    @GetMapping("/api/user/products")
    public ResponseEntity<?> getAllProducts(){
        return new ResponseEntity<>(productService.findAllProducts(), HttpStatus.OK);
    }


}
