package fullstack.to.models

import fullstack.to.commands.AddPoutineToOrderCommand
import fullstack.to.commands.OpenOrderCommand
import fullstack.to.commands.SubmitOrderCommand
import fullstack.to.events.OrderOpenedEvent
import fullstack.to.events.OrderSubmittedEvent
import fullstack.to.events.PoutineAddedToOrderEvent
import org.axonframework.commandhandling.annotation.CommandHandler
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot
import org.axonframework.eventsourcing.annotation.AggregateIdentifier
import org.axonframework.eventsourcing.annotation.EventSourcingHandler

public class Order extends AbstractAnnotatedAggregateRoot<String> {

    @AggregateIdentifier
    private String id

    private OrderStatus status
    private List<LineItem> lineItems
    private BigDecimal totalPrice

    //Axon requires a no arg constructor to create uninitialized aggregate
    Order() {
    }

    @CommandHandler
    public Order(OpenOrderCommand command) {
        apply(new OrderOpenedEvent(command.getId(), OrderStatus.OPEN))
    }

    @CommandHandler
    public void on(AddPoutineToOrderCommand command) {
        apply(new PoutineAddedToOrderEvent(
                command.getOrderId(),
                this.totalPrice,
                command.getProductId(),
                command.getDescription(),
                command.getPrice(),
                command.getQuantity()
        ))
    }

    @CommandHandler
    public void on(SubmitOrderCommand command) {
        apply(new OrderSubmittedEvent(command.getOrderId()))
    }

    @EventSourcingHandler
    public void on(OrderOpenedEvent event) {
        this.id = event.getId()
        this.status = OrderStatus.OPEN
        this.lineItems = []
        this.totalPrice = BigDecimal.ZERO
    }

    @EventSourcingHandler
    public void on(PoutineAddedToOrderEvent event) {
        this.lineItems.add(new LineItem(
                event.orderId,
                event.productId,
                event.description,
                event.itemPrice,
                event.quantity
        ))
        this.totalPrice += event.getItemPrice().multiply(event.getQuantity());
    }

    @EventSourcingHandler
    public void on(OrderSubmittedEvent event) {
        this.status = OrderStatus.SUBMITTED
    }

}
