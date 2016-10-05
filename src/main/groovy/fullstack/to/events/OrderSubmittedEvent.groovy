package fullstack.to.events

import groovy.transform.Immutable
import lombok.Getter

@Immutable
@Getter
class OrderSubmittedEvent {

    String orderId
}
