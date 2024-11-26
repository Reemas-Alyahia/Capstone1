package com.example.cap.Service;
import com.example.cap.Model.Merchant;
import com.example.cap.Model.Product;
import com.example.cap.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
@RequiredArgsConstructor
public class MerchantService {
private final UserService userService;
private final ProdectServi prodectServi;
    ArrayList<Merchant>merchants=new ArrayList<>();

    public ArrayList<Merchant>getMerchants(){
        return merchants;
    }

    public void addMerchant(Merchant merchant){
        merchants.add(merchant);
    }

    public boolean updateMerchant(Merchant merchant,String  id){
        for (int i = 0; i < merchants.size(); i++) {
if(merchants.get(i).getId().equalsIgnoreCase(id)){
    merchants.set(i,merchant);
    return true;
 }
        }
        return false;
    }

    public boolean deletMerchant(String id){
        for (int i = 0; i < merchants.size(); i++) {
            if(merchants.get(i).getId().equalsIgnoreCase(id)){
                merchants.remove(id);
                return true;
            }

        }
        return false;
    }
    /// ///////
    public Merchant findMerchant(String id){
           for(Merchant merchant:merchants){
                  if(merchant.getId().equalsIgnoreCase(id)){
                      return merchant;
                  }
            }
              return null;
         }
/// ////////1

   public String givtTo(String iduser,String mid,int amount){
      Merchant found=findMerchant(mid);
      if(found==null){
          return "Sorry but i checked and there no Merchant like this id ";
      }
       User foundUser = userService.findUser(iduser);
     if (foundUser == null) {
         return "Sorry, there is no user with this ID.";
    }
     foundUser.setBalance(foundUser.getBalance() + amount);


     return "Gift card : " + amount + " added successfully! for user: "+iduser+" and new balance is:  "+foundUser.getBalance();
 }


 /// //////////////2
 public String addOffer(String adminid ,String productId, double discountPercentage) {
    User found=userService.findUser(adminid);
    if(!found.getRole().equalsIgnoreCase("Admin")){
        return "Sorry, only Admin can change the balance.";

    }
     if (discountPercentage < 1 || discountPercentage > 100) {
         return "Discount percentage must be between 1% and 100%.";
     }

     Product foundP = prodectServi.findPrudect(productId);
     if (foundP == null) {
         return "Sorry, no product found with the provided ID.";
     }

     double discountedPrice = foundP.getPrice() - (foundP.getPrice() * discountPercentage / 100);
     if (discountedPrice <= 0) {
         return "Discount is too high, resulting in an invalid price.";
     }

     foundP.setPrice((int) discountedPrice);
     foundP.setHasDiscount(true);
     return "Discount applied successfully! New price for product ID " + productId + " is: " + discountedPrice;
 }

/// /
public String getOfferDetails(String categoryId) {
    ArrayList<Product> productsWithDiscount = prodectServi.getProductsByCategory(categoryId);

    if (productsWithDiscount.isEmpty()) {
        return "No products found for category: " + categoryId;
    }

    StringBuilder result = new StringBuilder("Offers for category: " + categoryId + " ");
    for (Product product : productsWithDiscount) {
        if (product.isHasDiscount()) {

            double price = product.getPrice().doubleValue();
            result.append(String.format("Product ID: %s, Name: %s, Price after discount: %.2f",
                    product.getId(), product.getName(), price));
        }
    }
    if (result.toString().equals("Offers for category: " + categoryId + " ")) {
        return "No discounted products found for category: " + categoryId;
    }

    return result.toString();
}
}
