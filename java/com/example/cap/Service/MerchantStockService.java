package com.example.cap.Service;

import com.example.cap.Model.MerchantStock;
import com.example.cap.Model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class MerchantStockService {
    private final ProdectServi prodectServi;
    ArrayList<MerchantStock>merchantStocks=new ArrayList<>();

    public ArrayList<MerchantStock>getMerchantStock(){
        return merchantStocks;
    }

    public void addMerchantStock(MerchantStock merchantStock){
        merchantStocks.add(merchantStock);
    }

    public boolean updateMerchantStock(MerchantStock merchantStock,String  id){
        for (int i = 0; i <merchantStocks.size(); i++) {
            if(merchantStocks.get(i).getId().equalsIgnoreCase(id)){
                merchantStocks.set(i,merchantStock);
                return true;
            }
        }
        return false;
    }
    public boolean deletMerchantStock(String id){
        for (int i = 0; i <merchantStocks.size(); i++) {
            if(merchantStocks.get(i).getId().equalsIgnoreCase(id)){
                merchantStocks.remove(id);
                return true;
            }

        }
        return false;
    }
    /// //////////////////

    public String addStocks(String mid, String pid, int amount) {
        MerchantStock stock = findstock(mid, pid);
        if (stock != null) {
            stock.setStock(stock.getStock() + amount);
            return "Stock updated! New stock: " + stock.getStock();
        } else {

            return "New stock created with amount: " + amount;
        }
    }


    /// //////////
    public MerchantStock findstock(String merchantId, String productId) {
        for (MerchantStock m : merchantStocks) {
            if (m.getMerchantid().equalsIgnoreCase(merchantId) &&
                    m.getProductid().equalsIgnoreCase(productId)) {
                return m;
            }
        }
        return null;
    }



}
