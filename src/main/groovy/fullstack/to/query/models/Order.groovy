package fullstack.to.query.models

import fullstack.to.command.models.LineItem
import fullstack.to.command.models.OrderStatus
import org.springframework.data.annotation.Id


class Order {

    @Id
    String id

    OrderStatus status
    List<LineItem> lineItems
    BigDecimal totalPrice
}
