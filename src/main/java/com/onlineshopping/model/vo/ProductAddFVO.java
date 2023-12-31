package com.onlineshopping.model.vo;

public class ProductAddFVO {
    private Integer productId;
    private String productIntro;
    private String productName;
    private double productPrice;

    @Override
    public String toString() {
        return "ProductAddFVO{" +
                "productId=" + productId +
                ", productIntro='" + productIntro + '\'' +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                '}';
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductIntro() {
        return productIntro;
    }

    public void setProductIntro(String productIntro) {
        this.productIntro = productIntro;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public ProductAddFVO() {
    }

    public ProductAddFVO(Integer productId, String productIntro, String productName, double productPrice) {
        this.productId = productId;
        this.productIntro = productIntro;
        this.productName = productName;
        this.productPrice = productPrice;
    }
}
