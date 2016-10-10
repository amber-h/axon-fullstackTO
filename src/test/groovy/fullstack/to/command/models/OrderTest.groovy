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
        expect:
        fixture.given()
                .when(new OpenOrderCommand("orderId"))
                .expectEvents(new OrderOpenedEvent("orderId", OrderStatus.OPEN))
    }

    def "add poutine to order with existing poutine"() {

        LineItem originalLineItem = new LineItem("orderId", "829348", "Tasty Poutine", 12.00, 1)
        LineItem newLineItem = new LineItem("orderId", "123456", "A good poutine", 10.00, 2)

        expect:
        fixture.given(
                    new OrderOpenedEvent("orderId", OrderStatus.OPEN),
                    new PoutineAddedToOrderEvent("orderId", 12.00, [originalLineItem], originalLineItem)
                )
                .when(new AddPoutineToOrderCommand("orderId", "123456", "A good poutine", 10.00, 2))
                .expectEvents(new PoutineAddedToOrderEvent("orderId", 32.00, [originalLineItem, newLineItem], newLineItem))
    }

}