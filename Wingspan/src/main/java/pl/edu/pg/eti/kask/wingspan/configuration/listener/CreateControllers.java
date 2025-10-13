package pl.edu.pg.eti.kask.wingspan.configuration.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import pl.edu.pg.eti.kask.wingspan.bird.controller.simple.ActionSimpleController;
import pl.edu.pg.eti.kask.wingspan.bird.controller.simple.BirdSimpleController;
import pl.edu.pg.eti.kask.wingspan.bird.service.ActionService;
import pl.edu.pg.eti.kask.wingspan.bird.service.BirdService;
import pl.edu.pg.eti.kask.wingspan.component.DtoFunctionFactory;
import pl.edu.pg.eti.kask.wingspan.user.controller.api.UserController;
import pl.edu.pg.eti.kask.wingspan.user.controller.simple.UserSimpleController;
import pl.edu.pg.eti.kask.wingspan.user.dto.function.RequestToUserFunction;
import pl.edu.pg.eti.kask.wingspan.user.dto.function.UpdateUserWithRequestFunction;
import pl.edu.pg.eti.kask.wingspan.user.dto.function.UserToResponseFunction;
import pl.edu.pg.eti.kask.wingspan.user.dto.function.UsersToResponseFunction;
import pl.edu.pg.eti.kask.wingspan.user.service.UserAvatarService;
import pl.edu.pg.eti.kask.wingspan.user.service.UserService;

/**
 * Listener started automatically on servlet context initialized. Creates an instance of controllers and puts them in
 * the application (servlet) context.
 */
@WebListener//using annotation does not allow configuring order
public class CreateControllers implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {

        BirdService birdService = (BirdService) event.getServletContext().getAttribute("birdService");
        ActionService actionService = (ActionService) event.getServletContext().getAttribute("actionService");


        event.getServletContext().setAttribute("birdController", new BirdSimpleController(
                birdService,
                new DtoFunctionFactory()
        ));

        event.getServletContext().setAttribute("actionController", new ActionSimpleController(
                actionService,
                new DtoFunctionFactory()
        ));

        ServletContext context = event.getServletContext();

        String avatarDirectory = context.getInitParameter("avatar-directory");
        if (avatarDirectory == null) {
            avatarDirectory = System.getProperty("java.io.tmpdir") + "/avatars";
        }

        UserAvatarService avatarService = new UserAvatarService(avatarDirectory);
        UserService userService = (UserService) context.getAttribute("userService");

        UserController userController = new UserSimpleController(
                userService,
                avatarService,
                new UsersToResponseFunction(),
                new UserToResponseFunction(),
                new RequestToUserFunction(),
                new UpdateUserWithRequestFunction()
        );

        context.setAttribute("userController", userController);
    }
}
