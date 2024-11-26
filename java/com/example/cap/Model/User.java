package com.example.cap.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class User {
    @NotEmpty(message = "id cannot be Empty!..")
    @Size(min = 2, max = 10, message = "the size of id 2 to 10")
    private String id;
    @NotEmpty(message = "name cannot be Empty!")
    @Size(min = 5, max = 10, message = "the size of name 5 to 10")
    private String username;

    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).{7,}$", message = "Password must be at least 7 characters long and include letters and numbers.")
    private String password;
    @NotEmpty(message = "email cannot be empty!!")
    @Email(message ="must be valid email" )
    private String email;
    @NotEmpty(message = "role cannot be empty!!")
    @Pattern(regexp = "^(Admin|Customer)",message = "you have to chose one of those ( “Admin”,”Customer”)")
    private String role;
    @NotNull(message = "the balance cannot BE null")
    @Positive(message = "balance must be positive ")
    private Integer  balance;


    //////
    private ArrayList<String>wishlist=new ArrayList<>();
    private ArrayList<String> purchaseHistory = new ArrayList<>(); // سجل المشتريات


}
