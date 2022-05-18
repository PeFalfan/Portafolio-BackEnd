package cl.medvet.medvetbackend.controllers;

import cl.medvet.medvetbackend.models.ResponseModel;
import cl.medvet.medvetbackend.services.impl.ProductServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(value = "*")
@RestController
public class ProductControllers {

    ProductServiceImpl productService = new ProductServiceImpl();

    @GetMapping(value = "/getProducts")
    public @ResponseBody ResponseModel getProducts(){
        ResponseModel response = new ResponseModel();

        try {

            response = productService.getProducts();

        } catch (Exception e){
            e.printStackTrace();
        }

        return response;
    }

}
