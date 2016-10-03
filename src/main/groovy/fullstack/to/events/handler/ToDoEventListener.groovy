package fullstack.to.events.handler

import fullstack.to.events.ToDoItemCompletedEvent
import fullstack.to.events.ToDoItemCreatedEvent
import org.axonframework.eventhandling.annotation.EventHandler
import org.springframework.stereotype.Component

@Component
class ToDoEventListener {

    @EventHandler
    public static void on(ToDoItemCreatedEvent event) {
        System.out.println("We've got something to do: $event.description ($event.todoId)");
    }

    @EventHandler
    public static void on(ToDoItemCompletedEvent event) {
        System.out.println("We've completed a task: $event.todoId with the following subtasks $event.steps");
    }
}
