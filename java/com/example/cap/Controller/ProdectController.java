package com.example.cap.Controller;

import com.example.cap.ApiResponce.ApiResponce;
import com.example.cap.Model.Product;
import com.example.cap.Model.User;
import com.example.cap.Service.ProdectServi;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/prodect")
@RequiredArgsConstructor
public class ProdectController {
    private final ProdectServi prodectServi;

    @GetMapping("/get")
    public ResponseEntity getProdect() {
        return ResponseEntity.status(200).body(prodectServi.getProduct());
    }
   @PostMapping("/add")
    public ResponseEntity addProdect(@RequestBody @Valid Product product, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
       prodectServi.addProduct(product);
        return ResponseEntity.status(200).body(new ApiResponce("Done Adding"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updatProduct(@RequestBody @Valid Product product, String id, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        boolean isUpdate=prodectServi.updateProduct(product,id);
        if(isUpdate){
            return ResponseEntity.status(200).body(new ApiResponce("Done Updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponce("Cannot Found the id"));

    }
    @DeleteMapping("/delet/{id}")
    public ResponseEntity deletProduct(String id){
        boolean isDelet= prodectServi.deletProduct(id);
        return ResponseEntity.status(200).body(new ApiResponce("Done Deleted"));

    }
    /// /////////////////////////////
    /// 1
    @PostMapping("/rate/{id}/{rate}")
    public ResponseEntity rateProduct(@PathVariable String id, @PathVariable Integer rate ) {
        if (rate == null || rate < 1 || rate > 5) {
            return ResponseEntity.status(400).body(new ApiResponce("Invalid rating value. Rating must be between 1 and 5."));
        }
        String message = prodectServi.addRating(id, rate);
        return ResponseEntity.status(200).body(new ApiResponce(message));}

    @GetMapping("/rating/{id}")
    public ResponseEntity<ApiResponce> getProductRating(@PathVariable String id) {
        Integer rating = prodectServi.getRating(id);
        if (rating != null) {
            return ResponseEntity.status(200).body(new ApiResponce("Product rating: " + rating));
        } else {
            return ResponseEntity.status(404).body(new ApiResponce("Product with ID: " + id + " not found."));
        }
    }



}

