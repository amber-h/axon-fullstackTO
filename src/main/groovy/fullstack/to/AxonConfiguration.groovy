package fullstack.to

import fullstack.to.aggregates.ToDoItem
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
import org.axonframework.eventstore.fs.FileSystemEventStore
import org.axonframework.eventstore.fs.SimpleEventFileResolver
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@AnnotationDriven //all command handlers and event handlers will be scanned and registered with their respective bus
public class AxonConfiguration {
    @Bean
    public AnnotationEventListenerBeanPostProcessor annotationEventListenerBeanPostProcessor() {
        AnnotationEventListenerBeanPostProcessor processor = new AnnotationEventListenerBeanPostProcessor();
        processor.setEventBus(eventBus());
        return processor;
    }

    @Bean
    public AnnotationCommandHandlerBeanPostProcessor annotationCommandHandlerBeanPostProcessor() {
        AnnotationCommandHandlerBeanPostProcessor processor = new AnnotationCommandHandlerBeanPostProcessor();
        processor.setCommandBus(commandBus());
        return processor;
    }

    @Bean
    public CommandBus commandBus() {
        SimpleCommandBus commandBus = new SimpleCommandBus();
        commandBus.setHandlerInterceptors(Arrays.asList(new BeanValidationInterceptor()));
//		commandBus.setTransactionManager(new SpringTransactionManager(transactionManager));
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

//	@Bean
//	public EntityManagerProvider entityManagerProvider() {
//		return new ContainerManagedEntityManagerProvider();
//	}

    @Bean
    public EventSourcingRepository<ToDoItem> todoRepository() {
        FileSystemEventStore eventStore = new FileSystemEventStore(new SimpleEventFileResolver(new File("data/evenstore")));
        EventSourcingRepository<ToDoItem> repository = new EventSourcingRepository<ToDoItem>(ToDoItem.class, eventStore);
        repository.setEventBus(eventBus());
        return repository;
    }

    @Bean
    public AggregateAnnotationCommandHandler<ToDoItem> todoCommandHandler() {
        AggregateAnnotationCommandHandler<ToDoItem> commandHandler = AggregateAnnotationCommandHandler.subscribe(ToDoItem.class, todoRepository(), commandBus());
        return commandHandler;
    }

}
