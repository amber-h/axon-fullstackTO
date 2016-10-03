package fullstack.to.commands.handler

import fullstack.to.aggregates.ToDoItem
import fullstack.to.commands.MarkCompletedCommand
import org.axonframework.commandhandling.annotation.CommandHandler
import org.axonframework.repository.Repository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ToDoCommandHandler {

    private Repository repository;

    @Autowired
    public ToDoCommandHandler(Repository repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void markCompleted(MarkCompletedCommand command) {
        ToDoItem todo = repository.load(command.todoId)
        todo.markCompleted()
    }
}
