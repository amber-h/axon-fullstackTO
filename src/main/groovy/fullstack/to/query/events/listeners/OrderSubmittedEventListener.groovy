package fullstack.to.query.events.listeners

import fullstack.to.command.events.OrderSubmittedEvent
import fullstack.to.command.models.OrderStatus
import fullstack.to.query.OrderRepository
import fullstack.to.query.models.Order
import org.axonframework.eventhandling.annotation.EventHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class OrderSubmittedEventListener {

    private static OrderRepository repository;

    @Autowired
    public OrderOpenedEventListener(OrderRepository repository) {
        this.repository = repository
    }

    @EventHandler
    public static void on(OrderSubmittedEvent event) {
        println("OrderSubmittedEventListener: $event.orderId");

        Order order = repository.findById(event.orderId)

        order.status = event.status
        repository.save(order)
    }
}
