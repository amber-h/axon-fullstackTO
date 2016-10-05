package fullstack.to.controller

import fullstack.to.commands.AddPoutineToOrderCommand
import fullstack.to.commands.OpenOrderCommand
import fullstack.to.commands.SubmitOrderCommand
import fullstack.to.models.LineItem
import org.axonframework.commandhandling.gateway.CommandGateway
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class CommandController {

    private static final Logger LOG = LoggerFactory.getLogger(this)

    @Autowired
    CommandGateway commandGateway

    @RequestMapping("/orders")
    public String openOrder() {
        final String orderId = UUID.randomUUID().toString();

        LOG.info("Sending OpenOrderCommand for order with id:" + orderId)
        commandGateway.send(new OpenOrderCommand(orderId));

        return "opened an order with id: " + orderId
    }

    @RequestMapping(value = "/orders/{orderId}/addPoutine", method = RequestMethod.POST)
    public void addItem(@RequestBody LineItem request, @PathVariable String orderId) {
        commandGateway.send(new AddPoutineToOrderCommand(
                orderId,
                request.productId,
                request.description,
                request.price,
                request.quantity
        ));
    }

    @RequestMapping(value = "/orders/{orderId}/submit", method = RequestMethod.POST)
    public void submit(@PathVariable String orderId) {
        commandGateway.send(new SubmitOrderCommand(orderId));
    }
}
