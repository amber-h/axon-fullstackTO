package fullstack.to.aggregates

import fullstack.to.events.ToDoItemCompletedEvent
import fullstack.to.commands.CreateToDoItemCommand
import fullstack.to.events.ToDoItemCreatedEvent
import org.axonframework.commandhandling.annotation.CommandHandler
import org.axonframework.eventhandling.annotation.EventHandler
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot
import org.axonframework.eventsourcing.annotation.AggregateIdentifier
import org.axonframework.eventsourcing.annotation.EventSourcingHandler

public class ToDoItem extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private String id

    private boolean completed

    //Axon requires a no arg constructor to create uninitialized aggregate
    ToDoItem() {
    }

    @CommandHandler
    public ToDoItem(CreateToDoItemCommand command) {
        println("********************************")
        println("********************************")
        println("********************************")
        apply(new ToDoItemCreatedEvent(command.getTodoId(), command.getDescription()))
    }

    @EventSourcingHandler
    public void on(ToDoItemCreatedEvent event) {
        println("I am here")
        this.id = event.getTodoId()
        this.completed = false
    }

    @EventSourcingHandler
    public void on(ToDoItemCompletedEvent event) {
        println("I am here also!!!")
        this.completed = true
    }

    public void markCompleted() {
        apply(new ToDoItemCompletedEvent(id))
    }
}
