package fullstack.to.events.listeners

import fullstack.to.events.PoutineAddedToOrderEvent
import org.axonframework.eventhandling.annotation.EventHandler
import org.springframework.stereotype.Component

@Component
class PoutineAddedToOrderEventListener {

    @EventHandler
    public static void on(PoutineAddedToOrderEvent event) {
        println("PoutineAddedToOrderEventListener: $event.orderId");
    }

}
