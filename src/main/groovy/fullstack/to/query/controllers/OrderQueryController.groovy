package fullstack.to.query.controllers

import fullstack.to.query.OrderRepository
import fullstack.to.query.models.Order
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderQueryController {

    @Autowired
    private OrderRepository repository

    @RequestMapping(value = "/orders/{orderId}", method = RequestMethod.GET)
    public Order getOrder(@PathVariable String orderId) {
        return repository.findById(orderId)
    }
}
