package fullstack.to.models

import fullstack.to.commands.CreateToDoItemCommand
import fullstack.to.commands.MarkCompletedCommand
import fullstack.to.events.ToDoItemCompletedEvent
import fullstack.to.events.ToDoItemCreatedEvent
import org.axonframework.commandhandling.annotation.CommandHandler
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot
import org.axonframework.eventsourcing.annotation.AggregateIdentifier
import org.axonframework.eventsourcing.annotation.EventSourcingHandler

public class ToDoItem extends AbstractAnnotatedAggregateRoot<String> {

    @AggregateIdentifier
    private String id

    private boolean completed

    //Axon requires a no arg constructor to create uninitialized aggregate
    ToDoItem() {
    }

    @CommandHandler
    public ToDoItem(CreateToDoItemCommand command) {
        apply(new ToDoItemCreatedEvent(command.getTodoId(), command.getDescription()))
    }

    @CommandHandler
    public void on(MarkCompletedCommand command) {
        apply(new ToDoItemCompletedEvent(command.todoId))
    }

    @EventSourcingHandler
    public void on(ToDoItemCreatedEvent event) {
        this.id = event.getTodoId()
        this.completed = false
    }

    @EventSourcingHandler
    public void on(ToDoItemCompletedEvent event) {
        this.completed = true
    }

    public void markCompleted() {
        apply(new ToDoItemCompletedEvent(id))
    }
}
