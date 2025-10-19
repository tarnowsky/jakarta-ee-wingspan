package pl.edu.pg.eti.kask.wingspan.configuration.observer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.context.control.RequestContextController;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.SneakyThrows;
import pl.edu.pg.eti.kask.wingspan.bird.entity.Action;
import pl.edu.pg.eti.kask.wingspan.bird.entity.ActionType;
import pl.edu.pg.eti.kask.wingspan.bird.entity.Bird;
import pl.edu.pg.eti.kask.wingspan.bird.service.ActionService;
import pl.edu.pg.eti.kask.wingspan.bird.service.BirdService;
import pl.edu.pg.eti.kask.wingspan.card.entity.Environment;
import pl.edu.pg.eti.kask.wingspan.card.entity.Food;
import pl.edu.pg.eti.kask.wingspan.user.entity.User;
import pl.edu.pg.eti.kask.wingspan.user.entity.UserRoles;
import pl.edu.pg.eti.kask.wingspan.user.service.UserService;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * Listener started automatically on servlet context initialized. Fetches instance of the datasource from the servlet
 * context and fills it with default content. Normally this class would fetch database datasource and init data only in
 * cases of empty database. When using persistence storage application instance should be initialized only during first
 * run in order to init database with starting data. Good place to create first default admin user.
 */
@ApplicationScoped
public class InitializedData {

    /**
     * Bird service.
     */
    private final BirdService birdService;

    /**
     * User service.
     */
    private final UserService userService;

    /**
     * Action service.
     */
    private final ActionService actionService;

    /**
     * The CDI container provides a built-in instance of {@link RequestContextController} that is dependent scoped for
     * the purposes of activating and deactivating.
     */
    private final RequestContextController requestContextController;

    @Inject
    public InitializedData(
            BirdService birdService,
            UserService userService,
            ActionService actionService,
            RequestContextController requestContextController
            ) {
        this.birdService = birdService;
        this.userService = userService;
        this.actionService = actionService;
        this.requestContextController = requestContextController;
    }

    public void contextInitialized(@Observes @Initialized(ApplicationScoped.class) Object init) { init(); }

    /**
     * Initializes database with some example values. Should be called after creating this object. This object should be
     * created only once.
     */
    @SneakyThrows
    private void init() {
        requestContextController.activate();

        User admin = User.builder()
                .id(UUID.fromString("c4804e0f-769e-4ab9-9ebe-0578fb4f00a6"))
                .login("admin")
                .birthdate(LocalDate.of(1990, 10, 21))
                .password("admin1admin")
                .userRoles(List.of(UserRoles.ADMIN, UserRoles.USER))
                .build();

        User tarnowsky = User.builder()
                .id(UUID.fromString("81e1c2a9-7f57-439b-b53d-6db88b071e4e"))
                .login("tarnowsky")
                .birthdate(LocalDate.of(2001, 1, 16))
                .password("user1user")
                .userRoles(List.of(UserRoles.USER))
                .build();

        User deborah = User.builder()
                .id(UUID.fromString("ed6cfb2a-cad7-47dd-9b56-9d1e3c7a4197"))
                .login("deborah")
                .birthdate(LocalDate.of(2002, 3, 19))
                .password("user2user")
                .userRoles(List.of(UserRoles.USER))
                .build();

        User peter = User.builder()
                .id(UUID.fromString("ed6cfb2a-cad7-47dd-9b56-9d1e3c7a4178"))
                .login("peter")
                .birthdate(LocalDate.of(1969, 5, 17))
                .password("user3user")
                .userRoles(List.of(UserRoles.USER))
                .build();

        userService.create(admin);
        userService.create(tarnowsky);
        userService.create(deborah);
        userService.create(peter);

        Action eagleAction = Action.builder()
                .id(UUID.fromString("ed6cfb2a-cad7-47dd-9b56-9d1e3c7a4198"))
                .description("When played: For each food type symbol in this bird’s cost, you may pay 1 from your hand instead. If you do, tuck the paid behind this card.")
                .actionType(ActionType.WHEN_PLAYED)
                .build();

        Action gullAction = Action.builder()
                .id(UUID.fromString("f5875513-bf7b-4ae1-b8a5-5b70a1b90e78"))
                .description("When activated: Draw 2 bird cards. Then tuck 1 under this bird and keep the other in your hand.\n")
                .actionType(ActionType.WHEN_ACTIVATED)
                .build();

        Action warblerAction = Action.builder()
                .id(UUID.fromString("f5875513-bf7b-4ae1-b8a5-5b70a1b90e79"))
                .description("his bird counts double toward the end-of-round goal, if it qualifies for the goal.\n")
                .actionType(ActionType.END_OF_THE_ROUND)
                .build();

        Action swanAction = Action.builder()
                .id(UUID.fromString("f5875513-bf7b-4ae1-b8a5-5b70a1b90e87"))
                .description("Game end: Lay 1 egg on each of your birds with a wingspan over 100 cm, including this one.\n")
                .actionType(ActionType.END_OF_THE_GAME)
                .build();

        Action avocetAction = Action.builder()
                .id(UUID.fromString("f5875513-bf7b-4ae1-b8a5-5b70a1b90e97"))
                .description("Once between turns: When another player takes the ‘lay eggs’ action, this bird lays 1 egg on another bird with a nest.")
                .actionType(ActionType.ONCE_BETWEEN_THE_TURNS)
                .build();

        Action hawkAction = Action.builder()
                .id(UUID.fromString("f5875513-bf7b-4ae1-b8a5-5b70a1b90e86"))
                .description("When activated: Look at the top bird card of the deck. If its wingspan is less than this bird’s hunting power, tuck it under this card (you “ate” it). Otherwise discard it.")
                .actionType(ActionType.HUNTING)
                .build();

        Action catBirdAction = Action.builder()
                .id(UUID.fromString("5d1da2ae-6a14-4b6d-8b4f-d117867118d5"))
                .description("When activated: Repeat a brown power on one other bird in this habitat.")
                .actionType(ActionType.SPECIAL)
                .build();

        actionService.create(eagleAction);
        actionService.create(gullAction);
        actionService.create(warblerAction);
        actionService.create(swanAction);
        actionService.create(avocetAction);
        actionService.create(hawkAction);
        actionService.create(catBirdAction);

        Bird americanRedstart = Bird.builder()
                .id(UUID.fromString("525d3e7b-bb1f-4c13-bf17-926d1a12e4c0"))
                .points(4)
                .name("American Redstart")
                .action(warblerAction)
                .food(Food.builder()
                        .worms(1)
                        .fruits(1)
                        .build())
                .environment(Environment.WOODS)
                .illustration(getResourceAsByteArray("../bird/american-redstart.png"))
                .user(tarnowsky)
                .wingspan(20)
                .maxEggNumber(2)
                .build();

        Bird blackChinnedHummingbird = Bird.builder()
                .id(UUID.fromString("525d3e7b-bb1f-4c13-bf17-926d1a12e4c1"))
                .points(4)
                .name("Black-Chinned Hummingbird")
                .action(gullAction)
                .food(Food.builder()
                        .any(1)
                        .build())
                .environment(Environment.MEADOW)
                .illustration(getResourceAsByteArray("../bird/black---chinned-hummingbird.png"))
                .user(tarnowsky)
                .wingspan(8)
                .maxEggNumber(2)
                .build();

        Bird broadWingedHawk = Bird.builder()
                .id(UUID.fromString("525d3e7b-bb1f-4c13-bf17-926d1a12e4c2"))
                .points(4)
                .name("Broad-Winged Hawk")
                .action(hawkAction)
                .food(Food.builder()
                        .rodents(1)
                        .build())
                .environment(Environment.WOODS)
                .illustration(getResourceAsByteArray("../bird/broad---winged-hawk.png"))
                .user(tarnowsky)
                .wingspan(86)
                .maxEggNumber(2)
                .build();

        Bird scaledQuail = Bird.builder()
                .id(UUID.fromString("525d3e7b-bb1f-4c13-bf17-926d1a12e4c3"))
                .points(0)
                .name("Scaled Quail")
                .action(swanAction)
                .food(Food.builder()
                        .plants(1)
                        .build())
                .environment(Environment.MEADOW)
                .illustration(getResourceAsByteArray("../bird/scaled-quail.png"))
                .user(deborah)
                .wingspan(36)
                .maxEggNumber(6)
                .build();

        Bird canvasback = Bird.builder()
                .id(UUID.fromString("525d3e7b-bb1f-4c13-bf17-926d1a12e4c4"))
                .points(4)
                .name("Canvasback")
                .action(avocetAction)
                .food(Food.builder()
                        .plants(1)
                        .any(1)
                        .build())
                .environment(Environment.WATER)
                .illustration(getResourceAsByteArray("../bird/canvasback.png"))
                .user(deborah)
                .wingspan(82)
                .maxEggNumber(4)
                .build();

        Bird brant = Bird.builder()
                .id(UUID.fromString("525d3e7b-bb1f-4c13-bf17-926d1a12e4c5"))
                .points(3)
                .name("Brant")
                .action(catBirdAction)
                .food(Food.builder()
                        .worms(1)
                        .fruits(1)
                        .build())
                .environment(Environment.WATER)
                .illustration(getResourceAsByteArray("../bird/brant.png"))
                .user(peter)
                .wingspan(114)
                .maxEggNumber(2)
                .build();


        birdService.create(americanRedstart);
        birdService.create(blackChinnedHummingbird);
        birdService.create(broadWingedHawk);
        birdService.create(scaledQuail);
        birdService.create(canvasback);
        birdService.create(brant);

        requestContextController.deactivate();
    }

    /**
     * @param name name of the desired resource
     * @return array of bytes read from the resource
     */
    @SneakyThrows
    private byte[] getResourceAsByteArray(String name) {
        try (InputStream is = this.getClass().getResourceAsStream(name)) {
            if (is != null) {
                return is.readAllBytes();
            } else {
                throw new IllegalStateException("Unable to get resource %s".formatted(name));
            }
        }
    }

}

