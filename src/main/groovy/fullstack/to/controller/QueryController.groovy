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
class QueryController {

    @RequestMapping(value = "/orders/{orderId}", method = RequestMethod.GET)
    public void getOrder(@PathVariable String orderId) {
        println("get order by id")
    }
}
