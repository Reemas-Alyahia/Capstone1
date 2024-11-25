package com.example.cap.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class MerchantStock {

    @NotEmpty(message = "id cannot be Empty!..")
    @Size(min = 2, max = 10, message = "the size of id 2 to 10")
    private String id;
    @NotEmpty(message = "productid cannot be Empty!..")
    private String productid;
    @NotEmpty(message = "merchantid cannot be Empty!..")
    private String merchantid;
   @NotNull(message = "stock cannot be Empty!..")
   @Min(value = 10,message = "stock have to be more than 10 at start")
    private Integer stock;

}
