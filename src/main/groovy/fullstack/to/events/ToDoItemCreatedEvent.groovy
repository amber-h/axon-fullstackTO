package fullstack.to.events
import groovy.transform.Immutable

@Immutable
public class ToDoItemCreatedEvent {

    String todoId
    String description
}