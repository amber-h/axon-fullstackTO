package fullstack.to.query.controllers

import org.springframework.web.bind.annotation.*

@RestController
class OrderQueryController {

    @RequestMapping(value = "/orders/{orderId}", method = RequestMethod.GET)
    public void getOrder(@PathVariable String orderId) {
        println("get order by id")
    }
}
