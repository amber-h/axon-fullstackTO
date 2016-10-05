package fullstack.to.command.commands

import groovy.transform.Immutable
import lombok.AllArgsConstructor
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier

@Immutable
@AllArgsConstructor
public class AddPoutineToOrderCommand {

    @TargetAggregateIdentifier
    String orderId
    String productId
    String description
    BigDecimal price
    Integer quantity



}
