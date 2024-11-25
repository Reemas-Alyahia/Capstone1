package com.example.cap.Service;

import com.example.cap.Model.MerchantStock;
import com.example.cap.Model.Product;
import com.example.cap.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService {
    private final ProdectServi prodectServi;
    private final MerchantStockService merchantStockService;


    ArrayList<User>users=new ArrayList<>();
    public ArrayList<User>getUser(){
        return users;
    }

    public void addUser(User user){
        users.add(user);
    }

    public boolean updateUser(User user,String  id){
        for (int i = 0; i <users.size(); i++) {
            if(users.get(i).getId().equalsIgnoreCase(id)){
                users.set(i,user);
                return true;
            }
        }
        return false;
    }
    public boolean deletUser(String id){
        for (int i = 0; i <users.size(); i++) {
            if(users.get(i).getId().equalsIgnoreCase(id)){
                users.remove(id);
                return true;
            }

        }
        return false;
    }
/// //////////////
///
 public User findUser (String id){
            for(User user:users){
                   if(user.getId().equalsIgnoreCase(id)){
                      return user ;
                   }
            }
             return null;
 }

    public String buy(String userId, String productId, String merchantId) {
        User user = null;
        for (User u : users) {
            if (u.getId().equalsIgnoreCase(userId)) {
                user = u;
                break;
            }
        }
        if (user == null) {
            return "User is not found";
        }
        Product product = prodectServi.findPrudect(productId);
        if (product == null) {
            return "Cannot find the product";
        }
        MerchantStock stock = merchantStockService.findstock(merchantId, productId);

        if (stock == null || stock.getStock() <= 0) {
            return "No stock available";
        }

        if (user.getBalance() < product.getPrice()) {
            return "Insufficient balance";
        }
        user.setBalance(user.getBalance() - product.getPrice());
        stock.setStock(stock.getStock() - 1);
        return "Purchase successful ,, Remaining balance: " + user.getBalance() + ", Remaining stock: " + stock.getStock();
    }


/// /////4
    public String resetPasswords(String email,String pass){
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getEmail().equalsIgnoreCase(email)){
                users.get(i).setPassword(pass);
                return "Your password has been reset successfully.";
            }

        }
        return "User with the provided email not found.";
    }
    public String addProductToWishlist(String userId, String productId) {
        User user = findUser(userId);
        if (user == null) {
            return "User with ID: " + userId + " not found.";
        }

        Product product = prodectServi.findPrudect(productId);
        if (product == null) {
            return "Product with ID: " + productId + " not found.";
        }

        if (user.getWishlist() == null) {
            user.setWishlist(new ArrayList<>());
        }

        if (user.getWishlist().contains(productId)) {
            return "Product is already in the wishlist.";
        }

        user.getWishlist().add(productId);
        return "Product added to wishlist successfully.";
    }


    public ArrayList<String> getWishlist(String userId) {
        User user = findUser(userId);
        if (user == null) {
            return null;
        }

        if (user.getWishlist() == null) {
            user.setWishlist(new ArrayList<>());
        }

        return user.getWishlist();
    }



    /// ///////extra
public boolean changeUsername(String id,String oldname,String newname){
    for (User user : users) {
        if (user.getId().equalsIgnoreCase(id)) {
            if (user.getUsername().equals(oldname)) {
                user.setUsername(newname);
                return true;
            }
        }
    }
    return false;
}

    /// //////////
    /// 3

    public boolean changePasswords(String id,String oldpass,String newPass){
        for (User user : users) {
            if (user.getId().equalsIgnoreCase(id)) {
                if (user.getPassword().equals(oldpass)) {
                    user.setPassword(newPass);
                    return true;
                }
            }
        }
        return false;
    }













}
