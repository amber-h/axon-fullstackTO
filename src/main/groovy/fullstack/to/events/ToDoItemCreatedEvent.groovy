package fullstack.to.events
import groovy.transform.Immutable
import lombok.Getter

@Immutable
@Getter
public class ToDoItemCreatedEvent {

    String todoId
    String description
}