package fullstack.to.events.listeners

import fullstack.to.events.ToDoItemCreatedEvent
import org.axonframework.eventhandling.annotation.EventHandler
import org.springframework.stereotype.Component

@Component
class ToDoItemCreatedEventListener {

    @EventHandler
    public static void handle(ToDoItemCreatedEvent event) {
        println("ToDoItemCreatedEventListener: $event.description ($event.todoId)");
    }

}
