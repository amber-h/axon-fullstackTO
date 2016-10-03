package fullstack.to.commands.handler

import fullstack.to.aggregates.ToDoItem
import fullstack.to.commands.CreateToDoItemCommand
import fullstack.to.commands.MarkCompletedCommand
import org.axonframework.commandhandling.annotation.CommandHandler
import org.axonframework.repository.Repository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CreateTodoItemCommandHandler {

    private Repository repository;

    @Autowired
    public CreateTodoItemCommandHandler(Repository repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(CreateToDoItemCommand command) {
        new ToDoItem(command)
    }
}
