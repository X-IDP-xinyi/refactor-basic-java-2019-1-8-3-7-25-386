package practice2;

import java.math.BigDecimal;

public class Product {
    private final BigDecimal price;
    private final BigDecimal availableDiscounts;
    private final long code;

    public Product(long code, double price, double discount) {
        this.code = code;
        this.price = BigDecimal.valueOf(price);
        this.availableDiscounts = BigDecimal.valueOf(discount);
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getDiscountRate() {
        return availableDiscounts;
    }

    public long getCode() {
        return code;
    }
}
