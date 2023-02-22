package practice3;

import java.math.BigDecimal;
public class OrderLineItem {
    private final BigDecimal price;

    public OrderLineItem(double price) {
        this.price = BigDecimal.valueOf(price);
    }

    public BigDecimal getPrice() {
        return price;
    }
}
