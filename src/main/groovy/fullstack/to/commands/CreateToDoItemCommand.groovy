package fullstack.to.commands
import groovy.transform.Immutable
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier

@Immutable
public class CreateToDoItemCommand {

    @TargetAggregateIdentifier
    String todoId
    String description
}
