package fullstack.to.query.events.listeners

import fullstack.to.command.events.OrderSubmittedEvent
import org.axonframework.eventhandling.annotation.EventHandler
import org.springframework.stereotype.Component

@Component
class OrderSubmittedEventListener {

    @EventHandler
    public static void on(OrderSubmittedEvent event) {
        println("OrderSubmittedEventListener: $event.orderId");
    }
}
