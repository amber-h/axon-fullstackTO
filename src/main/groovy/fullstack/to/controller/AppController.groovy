package fullstack.to.controller

import fullstack.to.commands.MarkCompletedCommand
import fullstack.to.commands.CreateToDoItemCommand
import org.axonframework.commandhandling.gateway.CommandGateway
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AppController {

    private static final Logger LOG = LoggerFactory.getLogger(this)

    @Autowired
    CommandGateway commandGateway

    @RequestMapping("/")
    public String helloWorld() {
        final String itemId = UUID.randomUUID().toString();

        LOG.info("Sending CreateToDoItemCommand for item:" + itemId)
        commandGateway.send(new CreateToDoItemCommand(itemId, "Order some poutine"));

        LOG.info("Sending MarkCompletedCommand for item:" + itemId)
        commandGateway.send(new MarkCompletedCommand(itemId));
        return "created to do"
    }
}
