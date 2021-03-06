package cl.medvet.medvetbackend.services.impl;

import cl.medvet.medvetbackend.models.ProductModel;
import cl.medvet.medvetbackend.models.ResponseModel;
import cl.medvet.medvetbackend.repository.impl.ProductRepositoryImpl;
import cl.medvet.medvetbackend.services.IProductService;

public class ProductServiceImpl implements IProductService {

    private ProductRepositoryImpl productRepo = new ProductRepositoryImpl();

    @Override
    public ResponseModel getProducts() {

        ResponseModel response = new ResponseModel();

        try {

            response.setData(productRepo.getProducts());
            response.setMessageResponse("Productos cargados correctamente.");
            response.setError(null);

        } catch (Exception e) {

            response.setData(null);
            response.setMessageResponse("Error al consultar productos.");
            response.setError(e.getMessage());

        }
        return response;
    }

    @Override
    public ResponseModel addProduct(ProductModel product) {
        ResponseModel response = new ResponseModel();

        try{
            response.setData(productRepo.addProduct(product));
            response.setMessageResponse("Producto agregado correctamente");
            response.setError(null);
        } catch (Exception e){
            e.printStackTrace();
            response.setError(e.getMessage());
            response.setData(null);
            response.setMessageResponse("Error al agregar producto");
        }

        return response;
    }

    public ResponseModel getPaymentMethods(){
        ResponseModel response = new ResponseModel();

        try {
            response.setData(productRepo.getMethods());
            response.setMessageResponse("Metodos consultados correctamente");
            response.setError(null);
        } catch (Exception e){
            response.setData(null);
            response.setMessageResponse("Error al consultar Metodos de pago");
            response.setError(e.getMessage());
        }

        return response;
    }

    public  ResponseModel updateProduct(ProductModel prod){
        ResponseModel response = new ResponseModel();

        try {
            response.setData(productRepo.editProduct(prod));
            response.setMessageResponse("Producto actualizado correctamente");
            response.setError(null);
        } catch (Exception e){
            response.setData(null);
            response.setMessageResponse("Error al intentar actualizar producto");
            response.setError(e.getMessage());
            e.printStackTrace();
        }

        return response;
    }
}
