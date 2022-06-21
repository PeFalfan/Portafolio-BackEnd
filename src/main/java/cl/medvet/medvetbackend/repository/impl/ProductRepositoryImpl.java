package cl.medvet.medvetbackend.repository.impl;

import cl.medvet.medvetbackend.models.PaymentMethodModel;
import cl.medvet.medvetbackend.models.ProductModel;
import cl.medvet.medvetbackend.repository.IProductRepository;
import cl.medvet.medvetbackend.util.DataBaseConnection;
import org.springframework.stereotype.Repository;

import java.sql.*;
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
        int res = 0;
        String query = "INSERT INTO producto  VALUES(0, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = getConnection()
                .prepareStatement(query)) {
            stmt.setString(1,product.getName_product());
            stmt.setString(2,product.getDesc_product());
            stmt.setString(3,product.getImage_product());
            stmt.setInt(4,product.getStock_product());
            stmt.setDouble(5,product.getPrice_product());
            stmt.setInt(6, product.getType_product());

            res = stmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return res;
    }

    public int editProduct(ProductModel product){

        int res = 0;

        String query = "UPDATE producto SET nombre_prod = ?, descripcion_prod = ?, image_prod = ?, stock_prod = ?, " +
                "precio_prod = ?, TIPO_PRODUCTO_id_tipo_prod = ? WHERE id_productos = ?";

        try (PreparedStatement stmt = getConnection()
                .prepareStatement(query)) {
            stmt.setString(1, product.getName_product());
            stmt.setString(2, product.getDesc_product());
            stmt.setString(3, product.getImage_product());
            stmt.setInt(4, product.getStock_product());
            stmt.setInt(5, product.getPrice_product());
            stmt.setInt(6, product.getType_product());
            stmt.setInt(7, product.getId_product());

            res = stmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }

        return res;
    }

    public List<PaymentMethodModel> getMethods(){
        List<PaymentMethodModel> methods = new ArrayList<>();

        String query = "SELECT * FROM medio_pago";

        try (PreparedStatement stmt = getConnection()
                .prepareStatement(query)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                methods.add(mapPaymentMethod(rs));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return methods;
    }

    public PaymentMethodModel mapPaymentMethod(ResultSet rs) throws SQLException{
        PaymentMethodModel payment = new PaymentMethodModel();

        payment.setId(rs.getInt("id_medio_pago"));
        payment.setName(rs.getString("nombre_medio_pago"));

        return payment;
    }
}
