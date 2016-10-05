package fullstack.to.command.models

import fullstack.to.command.commands.AddPoutineToOrderCommand
import fullstack.to.command.commands.OpenOrderCommand
import fullstack.to.command.events.OrderOpenedEvent
import fullstack.to.command.events.PoutineAddedToOrderEvent
import org.axonframework.test.FixtureConfiguration
import org.axonframework.test.Fixtures
import spock.lang.Specification

public class OrderTest extends Specification {
    private FixtureConfiguration fixture;

    void setup() {
        fixture = Fixtures.newGivenWhenThenFixture(Order.class)
    }

    def "opens an order"() {
        setup:
        fixture.given()
                .when(new OpenOrderCommand("orderId"))
                .expectEvents(new OrderOpenedEvent("orderId", OrderStatus.OPEN))
    }

    def "add poutine to order with existing poutine"() {
        setup:
        fixture.given(
                    new OrderOpenedEvent("orderId", OrderStatus.OPEN),
                    new PoutineAddedToOrderEvent("orderId", BigDecimal.ZERO, "product1", "Tasty poutine", 12.00, 1)
                )
                .when(new AddPoutineToOrderCommand("orderId", "productId", "A good poutine", 10.00, 5))
                .expectEvents(new PoutineAddedToOrderEvent("orderId", 12.00, "productId", "A good poutine", 10.00, 5))
    }

}