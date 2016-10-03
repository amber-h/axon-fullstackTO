package fullstack.to
import com.mongodb.MongoClient
import fullstack.to.aggregates.ToDoItem
import org.axonframework.commandhandling.CommandBus
import org.axonframework.commandhandling.SimpleCommandBus
import org.axonframework.commandhandling.annotation.AggregateAnnotationCommandHandler
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.commandhandling.gateway.DefaultCommandGateway
import org.axonframework.contextsupport.spring.AnnotationDriven
import org.axonframework.eventhandling.EventBus
import org.axonframework.eventhandling.SimpleEventBus
import org.axonframework.eventsourcing.EventSourcingRepository
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot
import org.axonframework.eventstore.EventStore
import org.axonframework.eventstore.mongo.DefaultMongoTemplate
import org.axonframework.eventstore.mongo.MongoEventStore
import org.axonframework.eventstore.mongo.MongoTemplate
import org.axonframework.repository.Repository
import org.axonframework.serializer.json.JacksonSerializer
import org.axonframework.serializer.xml.XStreamSerializer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment

import javax.annotation.PostConstruct

@Configuration
@AnnotationDriven
public class AxonConfiguration {

    @Autowired
    Environment env

    @Bean
    CommandBus commandBus() {
        new SimpleCommandBus()
    }

    @Bean
    public XStreamSerializer xstreamSerializer() {
        new XStreamSerializer()
    }

    @Bean
    public CommandGateway commandGateway() {
        new DefaultCommandGateway(commandBus())
    }

    @Bean
    EventBus eventBus() {
        def bus = new SimpleEventBus()
        return bus
    }

    @Bean
    MongoTemplate axonMongoTemplate(MongoClient client) {
        new DefaultMongoTemplate(
                client
        )
    }

    @Bean
    EventStore eventStore(MongoTemplate template) {
        new MongoEventStore(
                new JacksonSerializer(),
                template
        )
    }

    // aggregates
    @Bean
    public Repository<ToDoItem> eventSourcingRepository(MongoClient client) {
        EventSourcingRepository repository = new EventSourcingRepository(ToDoItem.class, eventStore(axonMongoTemplate(client)))
        repository.setEventBus(eventBus())

        return repository
    }

//    @Bean
//    public <T extends AbstractAnnotatedAggregateRoot> EventSourcingRepository<T> todoRepo(Class<T> clazz, MongoClient client) {
//        EventSourcingRepository repository = new EventSourcingRepository(clazz, eventStore(axonMongoTemplate(client)));
//        repository.setEventBus(eventBus());
//        return repository;
//    }

    // register all aggregates
    @PostConstruct
    public void registerAggregates() {
        new AggregateAnnotationCommandHandler<ToDoItem>(
                ToDoItem,
                eventSourcingRepository(new MongoClient()),
                commandBus()
        );
    }
}
