package fullstack.to.events.listeners

import fullstack.to.events.OrderOpenedEvent
import org.axonframework.eventhandling.annotation.EventHandler
import org.springframework.stereotype.Component

@Component
class OrderOpenedEventListener {

    @EventHandler
    public static void on(OrderOpenedEvent event) {
        println("OrderOpenedEventListener: $event.id");
    }

}
