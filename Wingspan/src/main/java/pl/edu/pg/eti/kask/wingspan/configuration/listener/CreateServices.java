package pl.edu.pg.eti.kask.wingspan.configuration.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import pl.edu.pg.eti.kask.wingspan.bird.repository.api.BirdRepository;
import pl.edu.pg.eti.kask.wingspan.bird.repository.api.ActionRepository;
import pl.edu.pg.eti.kask.wingspan.bird.repository.memory.BirdInMemoryRepository;
import pl.edu.pg.eti.kask.wingspan.bird.repository.memory.ActionInMemoryRepository;
import pl.edu.pg.eti.kask.wingspan.bird.service.BirdService;
import pl.edu.pg.eti.kask.wingspan.bird.service.ActionService;
import pl.edu.pg.eti.kask.wingspan.crypto.component.Pbkdf2PasswordHash;
import pl.edu.pg.eti.kask.wingspan.datastore.component.DataStore;
import pl.edu.pg.eti.kask.wingspan.user.repository.api.UserRepository;
import pl.edu.pg.eti.kask.wingspan.user.repository.memory.UserInMemoryRepository;
import pl.edu.pg.eti.kask.wingspan.user.service.UserService;

/**
 * Listener started automatically on servlet context initialized. Creates an instance of services (business layer) and
 * puts them in the application (servlet) context.
 */
@WebListener//using annotation does not allow configuring order
public class CreateServices implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        DataStore dataSource = (DataStore) event.getServletContext().getAttribute("datasource");

        UserRepository userRepository = new UserInMemoryRepository(dataSource);
        ActionRepository actionRepository = new ActionInMemoryRepository(dataSource);
        BirdRepository birdRepository = new BirdInMemoryRepository(dataSource);

        event.getServletContext().setAttribute("userService", new UserService(userRepository, new Pbkdf2PasswordHash()));
        event.getServletContext().setAttribute("birdService", new BirdService(birdRepository, actionRepository, userRepository));
        event.getServletContext().setAttribute("actionService", new ActionService(actionRepository));
    }

}

