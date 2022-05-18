package cl.medvet.medvetbackend.services;

import cl.medvet.medvetbackend.models.ProductModel;
import cl.medvet.medvetbackend.models.ResponseModel;

import java.util.List;

public interface IProductService {

    public ResponseModel getProducts();

    public ResponseModel addProduct(ProductModel product);

}
