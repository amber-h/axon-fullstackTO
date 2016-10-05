package fullstack.to.command.commands

import groovy.transform.Immutable
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier

@Immutable
class SubmitOrderCommand {

    @TargetAggregateIdentifier
    String orderId
}
