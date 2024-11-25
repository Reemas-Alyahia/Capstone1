package com.example.cap.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Category {
    @NotEmpty(message = "id cannot be Empty!..")
    @Size(min = 2, max = 10, message = "the size of id 2 to 10")
    private String id;
    @NotEmpty(message = "name cannot be Empty!")
    @Size(min = 3, max = 10, message = "the size of name 3 to 10")
    private String name;
}
