package cl.medvet.medvetbackend.models;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.io.Serializable;

@EntityScan
public class ProductModel implements Serializable {

    private static final long serialVersionUID = -291598534117011650L;
    private int id_product;
    private String name_product;
    private String desc_product;
    private int stock_product;
    private String image_product;
    private int price_product;

    private int type_product;

    public ProductModel() {
    }

    public int getType_product() {
        return type_product;
    }

    public void setType_product(int type_product) {
        this.type_product = type_product;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
    }

    public String getDesc_product() {
        return desc_product;
    }

    public void setDesc_product(String desc_product) {
        this.desc_product = desc_product;
    }

    public int getStock_product() {
        return stock_product;
    }

    public void setStock_product(int stock_product) {
        this.stock_product = stock_product;
    }

    public String getImage_product() {
        return image_product;
    }

    public void setImage_product(String image_product) {
        this.image_product = image_product;
    }

    public int getPrice_product() {
        return price_product;
    }

    public void setPrice_product(int price_product) {
        this.price_product = price_product;
    }
}
