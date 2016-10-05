package fullstack.to

import com.mongodb.MongoClient
import fullstack.to.models.ToDoItem
import org.axonframework.commandhandling.CommandBus
import org.axonframework.commandhandling.SimpleCommandBus
import org.axonframework.commandhandling.annotation.AggregateAnnotationCommandHandler
import org.axonframework.commandhandling.annotation.AnnotationCommandHandlerBeanPostProcessor
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.commandhandling.gateway.CommandGatewayFactoryBean
import org.axonframework.commandhandling.interceptors.BeanValidationInterceptor
import org.axonframework.contextsupport.spring.AnnotationDriven
import org.axonframework.eventhandling.EventBus
import org.axonframework.eventhandling.SimpleEventBus
import org.axonframework.eventhandling.annotation.AnnotationEventListenerBeanPostProcessor
import org.axonframework.eventsourcing.EventSourcingRepository
import org.axonframework.eventstore.EventStore
import org.axonframework.eventstore.mongo.DefaultMongoTemplate
import org.axonframework.eventstore.mongo.MongoEventStore
import org.axonframework.eventstore.mongo.MongoTemplate
import org.axonframework.serializer.json.JacksonSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@AnnotationDriven
public class AxonConfiguration {

    @Bean
    public CommandBus commandBus() {
        SimpleCommandBus commandBus = new SimpleCommandBus();
        commandBus.setHandlerInterceptors(Arrays.asList(new BeanValidationInterceptor()));
        return commandBus;
    }

    @Bean
    public EventBus eventBus() {
        return new SimpleEventBus();
    }

    @Bean
    public CommandGatewayFactoryBean<CommandGateway> commandGatewayFactoryBean() {
        CommandGatewayFactoryBean<CommandGateway> factory = new CommandGatewayFactoryBean<CommandGateway>();
        factory.setCommandBus(commandBus());
        return factory;
    }

    @Bean
    MongoTemplate axonMongoTemplate(MongoClient client) {
        new DefaultMongoTemplate(
                client
        )
    }

    @Bean
    EventStore eventStore(MongoTemplate template) {
        new MongoEventStore(new JacksonSerializer(), template)
    }

    @Bean
    public EventSourcingRepository<ToDoItem> todoRepository() {
        EventSourcingRepository<ToDoItem> repository = new EventSourcingRepository<ToDoItem>(ToDoItem.class, eventStore());
        repository.setEventBus(eventBus());
        return repository;
    }

    @Bean
    public AggregateAnnotationCommandHandler<ToDoItem> todoCommandHandler() {
        AggregateAnnotationCommandHandler<ToDoItem> commandHandler = AggregateAnnotationCommandHandler.subscribe(ToDoItem.class, todoRepository(), commandBus());
        return commandHandler;
    }

}
