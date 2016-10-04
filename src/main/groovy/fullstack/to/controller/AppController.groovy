package fullstack.to.controller

import fullstack.to.commands.CreateToDoItemCommand
import fullstack.to.commands.MarkCompletedCommand
import org.axonframework.commandhandling.gateway.CommandGateway
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
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

        return "created to do with id: " + itemId
    }

    @RequestMapping(value = "/complete/{itemId}", method = RequestMethod.POST)
    public String markCompleted(@PathVariable String itemId) {
        LOG.info("Sending MarkCompletedCommand for item:" + itemId)
        commandGateway.send(new MarkCompletedCommand(itemId));
        return "mark todo completed"
    }
}
