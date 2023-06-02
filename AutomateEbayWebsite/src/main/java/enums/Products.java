package enums;

public enum Products {
    MacBook("0345391802", "MacBook");

    // The price will always fluctuate. The product id and product title will be more or less constant.
    private String id;
    private String productTitle;

    Products(String id, String productTitle){
        this.id = id;
        this.productTitle = productTitle;
    }

    public String getProductId(){
        return id;
    }

    public String getProductTitle(){
        return productTitle;
    }
}
