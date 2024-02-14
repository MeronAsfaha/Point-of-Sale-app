package business.domain;

import java.util.Objects;

public class Product {
    private String name;
    final ProductType type;
    private Long id;
    private Double price;

    public Product(String name, ProductType type, Long id, Double price) {
        this.name = name;
        this.type = type;
        this.id = id;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public ProductType getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return Objects.equals(name, product.name) && type == product.type && Objects.equals(id, product.id) && Objects.equals(price, product.price);
    }

}
