package cl.medvet.medvetbackend.repository;

import cl.medvet.medvetbackend.models.ProductModel;

import java.util.List;

public interface IProductRepository {

    public List<ProductModel> getProducts();

    public int addProduct(ProductModel product);

}
