package fullstack.to.command.models

import fullstack.to.command.commands.AddPoutineToOrderCommand
import fullstack.to.command.commands.OpenOrderCommand
import fullstack.to.command.commands.SubmitOrderCommand
import fullstack.to.command.events.OrderOpenedEvent
import fullstack.to.command.events.OrderSubmittedEvent
import fullstack.to.command.events.PoutineAddedToOrderEvent
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

        BigDecimal newOrderTotal = totalPrice + command.getPrice().multiply(command.getQuantity());

        LineItem lineItemAddition = new LineItem(command.orderId, command.productId, command.description, command.price, command.quantity)
        List<LineItem> orderLineItemsWithAddition = lineItems.plus(lineItemAddition)

        apply(new PoutineAddedToOrderEvent(
                command.getOrderId(),
                newOrderTotal,
                orderLineItemsWithAddition,
                lineItemAddition
        ))
    }

    @CommandHandler
    public void on(SubmitOrderCommand command) {
        apply(new OrderSubmittedEvent(command.getOrderId(), OrderStatus.SUBMITTED))
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
        this.lineItems.add(event.lineItemAddition)
        this.totalPrice += event.newTotalOrderPrice
    }

    @EventSourcingHandler
    public void on(OrderSubmittedEvent event) {
        this.status = event.status
    }

}
