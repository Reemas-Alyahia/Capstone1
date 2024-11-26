package com.example.cap.Service;

import com.example.cap.Model.Merchant;
import com.example.cap.Model.Product;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProdectServi {
    ArrayList<Product>products=new ArrayList<>();

    public ArrayList<Product>getProduct(){
        return products;
    }

    public void addProduct(Product product){
        products.add(product);
    }

    public boolean updateProduct(Product product,String  id){
        for (int i = 0; i <products.size(); i++) {
            if(products.get(i).getId().equalsIgnoreCase(id)){
                products.set(i,product);
                return true;
            }
        }
        return false;
    }
    public boolean deletProduct(String id){
        for (int i = 0; i < products.size(); i++) {
            if(products.get(i).getId().equalsIgnoreCase(id)){
                products.remove(id);
                return true;
            }

        }
        return false;
    }

    public Product findPrudect(String id){
         for(Product product:products){
             if(product.getId().equalsIgnoreCase(id)){
                 return product;
             }
         }
         return null;
    }
/// //////////////////
/// 5

public String addRating(String id, int rating) {
    for (Product product : products) {
        if (product.getId().equalsIgnoreCase(id)) {
            product.setRating(rating); // تعيين التقييم الجديد
            return "Rating " + rating + " added successfully to product: " + id;
        }
    }
    return "Product with ID: " + id + " not found.";
}
/// //get
public Integer getRating(String id) {
    for (Product product : products) {
        if (product.getId().equalsIgnoreCase(id)) {
            return product.getRating();
        }
    }
    return null;
}

public ArrayList<Product> getProductsByCategory(String categoryid){
    ArrayList<Product>newcat=new ArrayList<>();
    for(Product product:products){
        if(product.getCategoryID().equalsIgnoreCase(categoryid)){
         newcat.add(product);
        }
    }
    return newcat;
}
}
