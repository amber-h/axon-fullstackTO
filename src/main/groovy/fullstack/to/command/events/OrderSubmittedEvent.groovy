package fullstack.to.command.events

import fullstack.to.command.models.OrderStatus
import groovy.transform.Immutable
import lombok.Getter

@Immutable
@Getter
class OrderSubmittedEvent {

    String orderId
    OrderStatus status
}
