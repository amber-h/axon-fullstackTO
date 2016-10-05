package fullstack.to.query

import fullstack.to.query.models.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {

    public Order findById(String id)
}

