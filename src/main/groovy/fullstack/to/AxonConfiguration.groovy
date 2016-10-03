package fullstack.to

import fullstack.to.aggregates.ToDoItem
import org.axonframework.commandhandling.CommandBus
import org.axonframework.commandhandling.SimpleCommandBus
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.commandhandling.gateway.DefaultCommandGateway
import org.axonframework.contextsupport.spring.AnnotationDriven
import org.axonframework.eventhandling.EventBus
import org.axonframework.eventhandling.SimpleEventBus
import org.axonframework.eventsourcing.EventSourcingRepository
import org.axonframework.eventstore.EventStore
import org.axonframework.eventstore.jdbc.JdbcEventStore
import org.axonframework.repository.Repository
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

import javax.sql.DataSource

@Configuration
@AnnotationDriven //all command handlers and event handlers will be scanned and registered with their respective bus
public class AxonConfiguration {

    @Bean
    public DataSource dataSource() {
        DataSourceBuilder
                .create()
                .username("sa")
                .password("")
                .url("jdbc:h2:mem:axondb")
                .driverClassName("org.h2.Driver")
                .build();
    }

    @Bean
    public EventStore jdbcEventStore() {
         new JdbcEventStore(dataSource())
    }

    @Bean
    CommandBus commandBus() {
        SimpleCommandBus simpleCommandBus = new SimpleCommandBus()
        simpleCommandBus
    }

    @Bean
    public CommandGateway commandGateway() {
        new DefaultCommandGateway(commandBus())
    }

    @Bean
    EventBus eventBus() {
        new SimpleEventBus()
    }

    @Bean
    public Repository<ToDoItem> eventSourcingRepository() {
        EventSourcingRepository repository = new EventSourcingRepository(ToDoItem.class, jdbcEventStore())
        repository.setEventBus(eventBus())

        return repository
    }

}
