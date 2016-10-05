package fullstack.to.events

import groovy.transform.Immutable
import lombok.Getter

@Immutable
@Getter
class PoutineAddedToOrderEvent {

    String orderId
    Double currentOrderPrice
    String productId
    String description
    Double itemPrice
    int quantity

}
