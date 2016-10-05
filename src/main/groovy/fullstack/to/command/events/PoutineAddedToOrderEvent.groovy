package fullstack.to.command.events

import fullstack.to.command.models.LineItem
import groovy.transform.Immutable
import lombok.Getter

@Immutable
@Getter
class PoutineAddedToOrderEvent {

    String orderId
    Double newTotalOrderPrice
    List<LineItem> lineItemsWithAddition
    LineItem lineItemAddition

}
