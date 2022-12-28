package dtos;

public class SalesDto {
    int id;
    String  des_prod;
    int quantity;
    float uni_price;
    float total;

    public SalesDto() {
    }

    public SalesDto(int id, String des_prod, int quantity, float uni_price, float total) {
        this.id = id;
        this.des_prod = des_prod;
        this.quantity = quantity;
        this.uni_price = uni_price;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDes_prod() {
        return des_prod;
    }

    public void setDes_prod(String des_prod) {
        this.des_prod = des_prod;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getUni_price() {
        return uni_price;
    }

    public void setUni_price(float uni_price) {
        this.uni_price = uni_price;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
    
}
