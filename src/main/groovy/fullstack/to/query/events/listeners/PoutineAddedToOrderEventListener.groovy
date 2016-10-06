package fullstack.to.query.events.listeners

import fullstack.to.command.events.PoutineAddedToOrderEvent
import fullstack.to.query.OrderRepository
import fullstack.to.query.models.Order
import org.axonframework.eventhandling.annotation.EventHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class PoutineAddedToOrderEventListener {

    private static OrderRepository repository;

    @Autowired
    public OrderOpenedEventListener(OrderRepository repository) {
        this.repository = repository
    }

    @EventHandler
    public static void on(PoutineAddedToOrderEvent event) {
        Order order = repository.findById(event.orderId)

        order.lineItems = event.lineItemsWithAddition
        order.totalPrice = event.newTotalOrderPrice

        repository.save(order)
    }

}
