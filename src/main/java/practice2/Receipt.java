package practice2;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class Receipt {

    public Receipt() {
        tax = BigDecimal.valueOf(0.1);
        tax = tax.setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal tax;

    public double CalculateGrandTotal(List<Product> products, List<OrderItem> items) {
        BigDecimal subTotal = calculateSubtotal(products, items);

        for (Product product : products) {
            OrderItem curItem = findOrderItemByProduct(items, product);
            BigDecimal reducedPrice = getDiscountRate(product, curItem);
            subTotal = subTotal.subtract(reducedPrice);
        }
        BigDecimal taxTotal = subTotal.multiply(tax);
        BigDecimal grandTotal = subTotal.add(taxTotal);

        return BigDecimal2double(grandTotal);
    }

    private static BigDecimal getDiscountRate(Product product, OrderItem curItem) {
        return product.getPrice()
                .multiply(product.getDiscountRate())
                .multiply(new BigDecimal(curItem.getCount()));
    }

    private static double BigDecimal2double(BigDecimal grandTotal) {
        return grandTotal.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }


    private OrderItem findOrderItemByProduct(List<OrderItem> items, Product product) {
        OrderItem curItem = null;
        for (OrderItem item : items) {
            if (item.getCode() == product.getCode()) {
                curItem = item;
                break;
            }
        }
        return curItem;
    }

    private BigDecimal calculateSubtotal(List<Product> products, List<OrderItem> items) {
        BigDecimal subTotal = new BigDecimal(0);
        for (Product product : products) {
            OrderItem item = findOrderItemByProduct(items, product);
            BigDecimal itemTotal = product.getPrice().multiply(new BigDecimal(item.getCount()));
            subTotal = subTotal.add(itemTotal);
        }
        return subTotal;
    }
}
