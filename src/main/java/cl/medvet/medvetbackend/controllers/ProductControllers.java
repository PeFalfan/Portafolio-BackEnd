package cl.medvet.medvetbackend.controllers;

import cl.medvet.medvetbackend.models.ProductModel;
import cl.medvet.medvetbackend.models.ResponseModel;
import cl.medvet.medvetbackend.services.impl.ProductServiceImpl;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
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

    @PostMapping(value = "/saveProduct")
    public @ResponseBody ResponseModel saveProduct(@RequestBody ProductModel product){
        ResponseModel response = new ResponseModel();

        try{
            response = productService.addProduct(product);
        }catch (Exception e){
            e.printStackTrace();
        }

        return response;
    }

    @GetMapping(value = "/getPaymentMethods")
    public @ResponseBody ResponseModel getPaymentMethod(){
        ResponseModel response = new ResponseModel();

        try {
            response = productService.getPaymentMethods();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    @PutMapping(value = "/updateProduct")
    public @ResponseBody ResponseModel updateProduct(@RequestBody ProductModel product){
        ResponseModel response = new ResponseModel();

        try {
            response = productService.updateProduct(product);
        } catch (Exception e){
            e.printStackTrace();
        }

        return response;
    }

}
