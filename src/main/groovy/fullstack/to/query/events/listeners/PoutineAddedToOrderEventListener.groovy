package fullstack.to.query.events.listeners

import fullstack.to.command.events.PoutineAddedToOrderEvent
import org.axonframework.eventhandling.annotation.EventHandler
import org.springframework.stereotype.Component

@Component
class PoutineAddedToOrderEventListener {

    @EventHandler
    public static void on(PoutineAddedToOrderEvent event) {
        println("PoutineAddedToOrderEventListener: $event.orderId")
    }

}
