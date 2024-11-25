package com.example.cap.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Product {
    @NotEmpty(message = "id cannot be Empty!..")
    @Size(min = 2, max = 10, message = "the size of id 2 to 10")
    private String id;
    @NotEmpty(message = "name cannot be Empty!")
    @Size(min = 3, max = 10, message = "the size of name 3 to 10")
    private String name;
    @NotNull(message = "price cannot be Empty!..")
    @Positive(message = "must be positive number")
    private Integer price;
    @NotEmpty(message = "the  category ID cannot be Empty!..")
    @Size(min = 2, max = 10, message = "the size of category ID  2 to 10")
    private String categoryID;
/// ////////
@Min(1)
@Max(5)
private Integer rating;
private boolean isHasDiscount=false;


}