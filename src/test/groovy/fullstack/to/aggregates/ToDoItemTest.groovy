package fullstack.to.aggregates

import fullstack.to.commands.CreateToDoItemCommand
import fullstack.to.commands.MarkCompletedCommand
import fullstack.to.commands.handler.ToDoCommandHandler
import fullstack.to.events.ToDoItemCompletedEvent
import fullstack.to.events.ToDoItemCreatedEvent
import org.axonframework.test.FixtureConfiguration
import org.axonframework.test.Fixtures
import spock.lang.Specification

public class ToDoItemTest extends Specification {
    private FixtureConfiguration fixture;

    void setup() {
        fixture = Fixtures.newGivenWhenThenFixture(ToDoItem.class)
        fixture.registerAnnotatedCommandHandler(new ToDoCommandHandler(fixture.getRepository()))
    }

    def "test Create ToDo Item"() {
        setup:
        fixture.given()
                .when(new CreateToDoItemCommand("todo1", "need to implement the aggregate"))
                .expectEvents(new ToDoItemCreatedEvent("todo1", "need to implement the aggregate"))
    }

    def "test Mark ToDo Item As Completed"() {
        setup:
        fixture.given(new ToDoItemCreatedEvent("todo1", "need to implement the aggregate"))
                .when(new MarkCompletedCommand("todo1"))
                .expectEvents(new ToDoItemCompletedEvent("todo1"))
    }

}