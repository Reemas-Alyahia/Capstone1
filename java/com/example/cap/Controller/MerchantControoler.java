package com.example.cap.Controller;

import com.example.cap.ApiResponce.ApiResponce;
import com.example.cap.Model.Merchant;
import com.example.cap.Model.User;
import com.example.cap.Service.MerchantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/merh")
@RequiredArgsConstructor
public class MerchantControoler {
    private final MerchantService merchantService;
    @GetMapping("/get")
    public ResponseEntity getMerchant(){
        return ResponseEntity.status(200).body(merchantService.getMerchants());
    }

    @PostMapping("/add")
    public ResponseEntity addMerchant(@RequestBody @Valid Merchant merchant, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        merchantService.addMerchant(merchant);
        return ResponseEntity.status(200).body(new ApiResponce("Done Adding"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchant(@RequestBody @Valid Merchant merchant,String id,Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        boolean isUpdate=merchantService.updateMerchant(merchant,id);
        if(isUpdate){
            return ResponseEntity.status(200).body(new ApiResponce("Done Updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponce("Cannot Found the id"));

    }
    @DeleteMapping("/delet/{id}")
    public ResponseEntity deletMerchant(String id){
        boolean isDelet= merchantService.deletMerchant(id);
        return ResponseEntity.status(200).body(new ApiResponce("Done Deleted"));

    }

    /// ///////6
    @PutMapping("/giv/{iduser}/{mid}/{amount}")
public ResponseEntity givto(@PathVariable String iduser,@PathVariable String mid,@PathVariable int amount){
        String result=merchantService.givtTo(iduser,mid,amount);
        return ResponseEntity.status(200).body(result);
}
/// //////////////
    @PostMapping("/add-offer/{productId}/{discount}")
    public ResponseEntity addOffer(@PathVariable String productId, @PathVariable double discount) {
        String message = merchantService.addOffer(productId, discount);
        return ResponseEntity.status(200).body(new ApiResponce(message));
    }
    @GetMapping("/oferDet/{catgoryId}")
    public ResponseEntity ggetOfferDetails(@PathVariable String catgoryId){
        String message = merchantService.getOfferDetails(catgoryId);
        return ResponseEntity.status(200).body(new ApiResponce(message));
    }

}




