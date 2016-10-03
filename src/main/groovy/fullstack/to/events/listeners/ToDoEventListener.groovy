package fullstack.to.events.listeners

import fullstack.to.events.ToDoItemCompletedEvent
import fullstack.to.events.ToDoItemCreatedEvent
import org.axonframework.eventhandling.annotation.EventHandler
import org.springframework.stereotype.Component

@Component
class ToDoEventListener {

    @EventHandler
    public static void on(ToDoItemCreatedEvent event) {
        println("ToDoItemCreatedEventListener: $event.description ($event.todoId)");
    }

    @EventHandler
    public static void on(ToDoItemCompletedEvent event) {
        println("ToDoItemCompletedEventListener: $event.todoId with the following subtasks $event.steps");
    }
}
