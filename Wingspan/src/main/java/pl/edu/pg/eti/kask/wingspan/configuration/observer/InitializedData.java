package pl.edu.pg.eti.kask.wingspan.configuration.observer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.context.control.RequestContextController;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import lombok.SneakyThrows;
import pl.edu.pg.eti.kask.wingspan.musician.entity.Musician;
import pl.edu.pg.eti.kask.wingspan.musician.entity.Genre;
import pl.edu.pg.eti.kask.wingspan.musician.entity.Technique;
import pl.edu.pg.eti.kask.wingspan.musician.service.MusicianService;
import pl.edu.pg.eti.kask.wingspan.musician.service.GenreService;
import pl.edu.pg.eti.kask.wingspan.user.entity.User;
import pl.edu.pg.eti.kask.wingspan.user.entity.UserRoles;
import pl.edu.pg.eti.kask.wingspan.user.service.UserService;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * Listener started automatically on CDI application context initialized. Injects proxy to the services and fills
 * database with default content. When using persistence storage application instance should be initialized only during
 * first run in order to init database with starting data. Good place to create first default admin user.
 */
@ApplicationScoped
public class InitializedData {

    /**
     * Musician service.
     */
    private final MusicianService musicianService;

    /**
     * User service.
     */
    private final UserService userService;

    /**
     * Genre service.
     */
    private final GenreService genreService;

    /**
     * The CDI container provides a built-in instance of {@link RequestContextController} that is dependent scoped for
     * the purposes of activating and deactivating.
     */
    private final RequestContextController requestContextController;

    /**
     * @param musicianService         musician service
     * @param userService              user service
     * @param genreService        genre service
     * @param requestContextController CDI request context controller
     */
    @Inject
    public InitializedData(
            MusicianService musicianService,
            UserService userService,
            GenreService genreService,
            RequestContextController requestContextController
    ) {
        this.musicianService = musicianService;
        this.userService = userService;
        this.genreService = genreService;
        this.requestContextController = requestContextController;
    }

    public void contextInitialized(@Observes @Initialized(ApplicationScoped.class) Object init) {
        init();
    }

    /**
     * Initializes database with some example values. Should be called after creating this object. This object should be
     * created only once.
     */
    @SneakyThrows
    private void init() {
        requestContextController.activate();// start request scope in order to inject request scoped repositories

        User admin = User.builder()
                .id(UUID.fromString("c4804e0f-769e-4ab9-9ebe-0578fb4f00a6"))
                .login("admin")
                .name("System")
                .surname("Admin")
                .birthDate(LocalDate.of(1990, 10, 21))
                .email("admin@example.com")
                .password("adminadmin")
                .roles(List.of(UserRoles.ADMIN, UserRoles.USER))
                .build();

        User kevin = User.builder()
                .id(UUID.fromString("81e1c2a9-7f57-439b-b53d-6db88b071e4e"))
                .login("kevin")
                .name("Kevin")
                .surname("Pear")
                .birthDate(LocalDate.of(2001, 1, 16))
                .email("kevin@example.com")
                .password("useruser")
                .roles(List.of(UserRoles.USER))
                .build();

        User alice = User.builder()
                .id(UUID.fromString("ed6cfb2a-cad7-47dd-9b56-9d1e3c7a4197"))
                .login("alice")
                .name("Alice")
                .surname("Grape")
                .birthDate(LocalDate.of(2002, 3, 19))
                .email("alice@example.com")
                .password("useruser")
                .roles(List.of(UserRoles.USER))
                .build();

        userService.create(admin);
        userService.create(kevin);
        userService.create(alice);

        Technique soloRiff = Technique.builder()
                .name("Solo Riff")
                .description("Surprise musician with the Solo Riff.")
                .build();

        Technique powerChord = Technique.builder()
                .name("Power Chord")
                .description("Power chord is not only simple but also effective.")
                .build();

        Technique recoverTone = Technique.builder()
                .name("Recover Tone")
                .description("Recovers instruments tone.")
                .build();

        Technique enchantAudience = Technique.builder()
                .name("Enchant Audience")
                .description("Enchant audience to pay you more for the gig.")
                .build();

        Technique heavySoloRiff = Technique.builder()
                .name("Heavy Solo Riff")
                .description("Surprise another musician with the even heavier Solo Riff")
                .build();

        Genre rock = Genre.builder()
                .id(UUID.fromString("f5875513-bf7b-4ae1-b8a5-5b70a1b90e76"))
                .name("Rock")
                .technique(1, soloRiff)
                .technique(2, enchantAudience)
                .build();

        Genre jazz = Genre.builder()
                .id(UUID.fromString("5d1da2ae-6a14-4b6d-8b4f-d117867118d4"))
                .name("Jazz")
                .technique(1, soloRiff)
                .technique(2, recoverTone)
                .build();

        Genre blues = Genre.builder()
                .id(UUID.fromString("2d9b1e8c-67c5-4188-a911-5f064a63d8cd"))
                .name("Blues")
                .technique(1, soloRiff)
                .technique(2, heavySoloRiff)
                .build();

        Genre pop = Genre.builder()
                .id(UUID.randomUUID())
                .name("Pop")
                .technique(1, soloRiff)
                .technique(2, powerChord)
                .build();

        genreService.create(rock);
        genreService.create(jazz);
        genreService.create(blues);
        genreService.create(pop);

        Musician slash = Musician.builder()
                .id(UUID.fromString("525d3e7b-bb1f-4c13-bf17-926d1a12e4c0"))
                .name("Slash")
                .age(18)
                .biography("A not so young rock star with a black hat on.")
                .experience(0)
                .level(1)
                .genre(rock)
                .toneQuality(16)
                .resonance(12)
                .volume(8)
                .condition(2 * 12)
                .portrait(getResourceAsByteArray("../musician/slash.png"))
                .user(kevin)
                .build();

        Musician mayer = Musician.builder()
                .id(UUID.fromString("cc0b0577-bb6f-45b7-81d6-3db88e6ac19f"))
                .name("John Mayer")
                .age(37)
                .biography("Quite experienced bluesman.")
                .experience(0)
                .level(1)
                .genre(blues)
                .toneQuality(8)
                .resonance(10)
                .volume(18)
                .condition(2 * 10)
                .portrait(getResourceAsByteArray("../musician/mayer.png"))
                .user(kevin)
                .build();

        Musician meola = Musician.builder()
                .id(UUID.fromString("f08ef7e3-7f2a-4378-b1fb-2922d730c70d"))
                .name("Al di Meola")
                .age(32)
                .biography("He spent at least one saturday night in San Francisco")
                .experience(0)
                .level(1)
                .genre(jazz)
                .toneQuality(8)
                .resonance(12)
                .volume(14)
                .condition(2 * 12)
                .portrait(getResourceAsByteArray("../musician/meola.png"))
                .user(alice)
                .build();

        Musician jackson = Musician.builder()
                .id(UUID.fromString("ff327e8a-77c0-4f9b-90a2-89e16895d1e1"))
                .name("Michael Jackson")
                .age(20)
                .biography("King of pop.")
                .experience(0)
                .level(1)
                .genre(pop)
                .toneQuality(14)
                .resonance(12)
                .volume(10)
                .condition(2 * 12)
                .portrait(getResourceAsByteArray("../musician/jackson.png"))
                .user(alice)
                .build();

        musicianService.create(meola);
        musicianService.create(slash);
        musicianService.create(jackson);
        musicianService.create(mayer);

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
