package practice3;

import java.math.BigDecimal;
import java.util.List;

public class Order {

    private List<OrderLineItem> orderLineItemList;
    private List<BigDecimal> discounts;
    private BigDecimal tax;

    public Order(List<OrderLineItem> orderLineItemList, List<BigDecimal> discounts) {
        this.orderLineItemList = orderLineItemList;
        this.discounts = discounts;
        this.tax = new BigDecimal("0.1");
    }

    public BigDecimal calculate() {
        BigDecimal subTotal = new BigDecimal(0);

        subTotal = getSubTotal(subTotal);
        subTotal = getDiscounts(subTotal);

        tax = getTax(subTotal);
        return subTotal.add(tax);
    }

    private BigDecimal getDiscounts(BigDecimal subTotal) {
        for (BigDecimal discount : discounts) {
            subTotal = subTotal.subtract(discount);
        }
        return subTotal;
    }

    private BigDecimal getSubTotal(BigDecimal subTotal) {
        for (OrderLineItem lineItem : orderLineItemList) {
            subTotal = subTotal.add(lineItem.getPrice());
        }
        return subTotal;
    }

    private BigDecimal getTax(BigDecimal subTotal) {
        return subTotal.multiply(this.tax);
    }
}
