package com.example.cap.Controller;

import com.example.cap.ApiResponce.ApiResponce;
import com.example.cap.Model.Category;
import com.example.cap.Model.User;
import com.example.cap.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/catgory")
@RequiredArgsConstructor
public class CategoryContrlloer {
    private final CategoryService categoryService;

    @GetMapping("/get")
    public ResponseEntity getCategory(){
        return ResponseEntity.status(200).body(categoryService.getCategories());
    }

    @PostMapping("/add")
    public ResponseEntity addCategory(@RequestBody @Valid Category category, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
      categoryService.addCatgory(category);
        return ResponseEntity.status(200).body(new ApiResponce("Done Adding"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory(@RequestBody @Valid Category category,String id,Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        boolean isUpdate=categoryService.updateCatg(category,id);
        if(isUpdate){
            return ResponseEntity.status(200).body(new ApiResponce("Done Updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponce("Cannot Found the id"));

    }
    @DeleteMapping("/delet/{id}")
    public ResponseEntity deletCategory(String id){
        boolean isDelet= categoryService.deletCatg(id);
        return ResponseEntity.status(200).body(new ApiResponce("Done Deleted"));

    }

    /// ////////
    @GetMapping("/search/{cangname}")
public ResponseEntity search(@PathVariable String cangname){
    ArrayList newC=categoryService.searchCatg(cangname);
    if(newC==null){
        return ResponseEntity.status(400).body(new ApiResponce("There no Catgory like this name"));

    }
    return ResponseEntity.status(200).body(newC);
}




}
