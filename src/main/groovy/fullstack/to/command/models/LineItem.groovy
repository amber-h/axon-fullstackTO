package fullstack.to.command.models

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class LineItem {

    String orderId
    String productId
    String description
    Double price
    Integer quantity

    LineItem() {}

    public LineItem(String orderId, String productId, String description, Double price, Integer quantity) {
        this.orderId = orderId
        this.productId = productId
        this.description = description
        this.price = price
        this.quantity = quantity
    }

}
