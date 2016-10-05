package fullstack.to.events.listeners

import fullstack.to.events.ToDoItemCompletedEvent
import org.axonframework.eventhandling.annotation.EventHandler
import org.springframework.stereotype.Component

@Component
class ToDoItemCompletedEventListener {

    @EventHandler
    public static void on(ToDoItemCompletedEvent event) {
        println("ToDoItemCompletedEventListener: $event.todoId");
    }
}
