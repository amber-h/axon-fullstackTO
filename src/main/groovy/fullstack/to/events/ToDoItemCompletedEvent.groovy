package fullstack.to.events
import groovy.transform.Immutable

@Immutable
public class ToDoItemCompletedEvent {

    String todoId
}