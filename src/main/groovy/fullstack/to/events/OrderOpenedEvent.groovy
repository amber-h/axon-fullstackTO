package fullstack.to.events

import fullstack.to.models.OrderStatus
import groovy.transform.Immutable
import lombok.Getter

@Immutable
@Getter
class OrderOpenedEvent {

    String id
    OrderStatus status

}
