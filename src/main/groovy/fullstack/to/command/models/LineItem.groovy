package fullstack.to.command.models

import groovy.transform.EqualsAndHashCode
import groovy.transform.Immutable

@Immutable
@EqualsAndHashCode
class LineItem {

    String orderId
    String productId
    String description
    Double price
    Integer quantity

}
