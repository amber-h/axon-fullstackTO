package fullstack.to.commands
import groovy.transform.Immutable
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier

@Immutable
public class MarkCompletedCommand {

    @TargetAggregateIdentifier
    String todoId
}