package fullstack.to.command.events

import groovy.transform.Immutable
import lombok.Getter

@Immutable
@Getter
class OrderSubmittedEvent {

    String orderId
}
