package com.example.cap.Service;
import com.example.cap.Model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CategoryService {
    ArrayList<Category>categories=new ArrayList<>();
    public  ArrayList<Category>getCategories(){
        return categories;
    }
public void addCatgory(Category category){
    categories.add(category);
}
public boolean updateCatg(Category category,String id){
    for (int i = 0; i < categories.size(); i++) {
        if(categories.get(i).getId().equalsIgnoreCase(id)){
            categories.set(i,category);
            return true;
        }
    }
    return false;
}

public boolean deletCatg(String id){
    for (int i = 0; i < categories.size(); i++) {
        if(categories.get(i).getId().equalsIgnoreCase(id)){
            categories.remove(id);
            return true;
        }

    }
    return false;
}
/// /////////2Extra
public ArrayList searchCatg(String catgortname){
    ArrayList<Category>newC=new ArrayList<>();
    for (int i = 0; i < categories.size(); i++) {
        if(categories.get(i).getName().equalsIgnoreCase(catgortname)){
        }

    }
    if(newC.isEmpty()){
        return null;
    }
    return newC;
}
}
