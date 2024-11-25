package com.example.cap.Controller;

import com.example.cap.ApiResponce.ApiResponce;
import com.example.cap.Model.MerchantStock;
import com.example.cap.Model.User;
import com.example.cap.Service.MerchantStockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/mStock")
@RequiredArgsConstructor
public class MerchantStockControler {
    private final MerchantStockService merchantStockService;


    @GetMapping("/get")
    public ResponseEntity getMerchantStock(){
        return ResponseEntity.status(200).body(merchantStockService.getMerchantStock());
    }

    @PostMapping("/add")
    public ResponseEntity addMerchantStock(@RequestBody @Valid MerchantStock merchantStock, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        merchantStockService.addMerchantStock(merchantStock);
        return ResponseEntity.status(200).body(new ApiResponce("Done Adding"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchantStock(@RequestBody @Valid MerchantStock merchantStock,String id,Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        boolean isUpdate= merchantStockService.updateMerchantStock(merchantStock,id);
        if(isUpdate){
            return ResponseEntity.status(200).body(new ApiResponce("Done Updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponce("Cannot Found the id"));

    }
    @DeleteMapping("/delet/{id}")
    public ResponseEntity deletMerchantStock(String id){
        boolean isDelet= merchantStockService.deletMerchantStock(id);
        return ResponseEntity.status(200).body(new ApiResponce("Done Deleted"));

    }
/// /
@PutMapping("/addStocks/{mid}/{pid}/{amount}")
    public ResponseEntity addStocks(@PathVariable String mid,@PathVariable String pid,@PathVariable int amount){
    String result=merchantStockService.addStocks(mid,pid,amount);
    return ResponseEntity.status(200).body(result);
    }





}
