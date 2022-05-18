package cl.medvet.medvetbackend.repository.impl;

import cl.medvet.medvetbackend.models.ProductModel;
import cl.medvet.medvetbackend.repository.IProductRepository;
import cl.medvet.medvetbackend.util.DataBaseConnection;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements IProductRepository {

    private Connection getConnection() throws SQLException {
        return DataBaseConnection.getConnection();
    }


    @Override
    public List<ProductModel> getProducts() {

        List<ProductModel> products = new ArrayList<>();

        try (Statement stmt = getConnection().createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM producto")){
            while (rs.next()){
                products.add(mapProduct(rs));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return products;
    }

    public ProductModel mapProduct(ResultSet rs) throws SQLException {
        ProductModel prod = new ProductModel();

        prod.setId_product(rs.getInt("id_productos"));
        prod.setName_product(rs.getString("nombre_prod"));
        prod.setDesc_product(rs.getString("descripcion_prod"));
        prod.setStock_product(rs.getInt("stock_prod"));
        prod.setPrice_product(rs.getInt("precio_prod"));
        prod.setImage_product(rs.getString("image_prod"));
        prod.setType_product(rs.getInt("TIPO_PRODUCTO_id_tipo_prod"));

        return prod;
    }

    @Override
    public int addProduct(ProductModel product) {
        return 0;
    }
}
