package com.example.cap.Controller;

import com.example.cap.ApiResponce.ApiResponce;
import com.example.cap.Model.User;
import com.example.cap.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/get")
    public ResponseEntity getUser(){
        return ResponseEntity.status(200).body(userService.getUser());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponce("Done Adding"));
    }
    @PutMapping("/update/{id}")
     public ResponseEntity updateUser(@RequestBody @Valid User user,String id,Errors errors){
         if(errors.hasErrors()){
             return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
         }
         boolean isUpdate=userService.updateUser(user,id);
         if(isUpdate){
             return ResponseEntity.status(200).body(new ApiResponce("Done Updated"));
         }
    return ResponseEntity.status(400).body(new ApiResponce("Cannot Found the id"));

     }
     @DeleteMapping("/delet/{id}")
   public ResponseEntity deletUser(String id){
        boolean isDelet= userService.deletUser(id);
       return ResponseEntity.status(200).body(new ApiResponce("Done Deleted"));

   }

   @PutMapping("/buy/{userId}/{productId}/{merchantId}")
    public ResponseEntity buyProduct(@PathVariable String userId, @PathVariable String productId, @PathVariable String merchantId) {
        String result = userService.buy(userId, productId, merchantId);
        return ResponseEntity.status(200).body(result);
    }

@PutMapping("/reset/{email}/{pass}")
    public ResponseEntity resetPasswords(@PathVariable String email,@PathVariable String pass){
        String result=userService.resetPasswords(email,pass);
        return ResponseEntity.status(200).body(result);
    }

    @PostMapping("/wishlist/{userId}/{productId}")
    public ResponseEntity<ApiResponce> addProductToWishlist(@PathVariable String userId, @PathVariable String productId) {
        String message = userService.addProductToWishlist(userId, productId);
        return ResponseEntity.status(200).body(new ApiResponce(message));
    }
    @GetMapping("/wlist/{userId}")
    public ResponseEntity getWishlist(@PathVariable String userId) {
        ArrayList wishlist = userService.getWishlist(userId);
        if (wishlist == null || wishlist.isEmpty()) {
            return ResponseEntity.status(404).body(new ApiResponce("Wishlist is empty or user not found."));
        }
        return ResponseEntity.status(200).body(wishlist);
    }
/// ///////
    //////1Extra
@PutMapping("/ct/{id}/{oldname}/{newname}")
public ResponseEntity changUseName(@PathVariable String id,@PathVariable String oldname,String newname){
    boolean isChanged=userService.changeUsername(id, oldname, newname);
    if(isChanged){
        return ResponseEntity.status(200).body(new ApiResponce("Done from changing Username"));
    }
    return ResponseEntity.status(400).body(new ApiResponce("Cannot Found the id"));
}
/// /////////
    @PutMapping("/chang/{id}/{oldpass}/{newpass}")
    public ResponseEntity changPasswords(@PathVariable String id,@PathVariable String oldpass,String newpass){
        boolean isChanged=userService.changePasswords(id,oldpass,newpass);
        if(isChanged){
            return ResponseEntity.status(200).body(new ApiResponce("Done from changing password"));
        }
        return ResponseEntity.status(400).body(new ApiResponce("Cannot Found the id"));
    }


}
