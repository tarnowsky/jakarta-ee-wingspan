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


@ApplicationScoped
public class InitializedData {

    
    private final MusicianService musicianService;

    
    private final UserService userService;

    
    private final GenreService genreService;

    
    private final RequestContextController requestContextController;

    
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
                .age(60)
                .biography("A not so young rock star with a black hat on.")
                .experience(100)
                .level(90)
                .genre(rock)
                .toneQuality(10)
                .resonance(10)
                .volume(10)
                .condition(4)
                .portrait(getResourceAsByteArray("../musician/slash.png"))
                .user(kevin)
                .build();

        Musician mayer = Musician.builder()
                .id(UUID.fromString("cc0b0577-bb6f-45b7-81d6-3db88e6ac19f"))
                .name("John Mayer")
                .age(48)
                .biography("Quite experienced bluesman.")
                .experience(80)
                .level(80)
                .genre(blues)
                .toneQuality(10)
                .resonance(10)
                .volume(6)
                .condition(9)
                .portrait(getResourceAsByteArray("../musician/mayer.png"))
                .user(kevin)
                .build();

        Musician meola = Musician.builder()
                .id(UUID.fromString("f08ef7e3-7f2a-4378-b1fb-2922d730c70d"))
                .name("Al di Meola")
                .age(71)
                .biography("He spent at least one saturday night in San Francisco")
                .experience(110)
                .level(110)
                .genre(jazz)
                .toneQuality(10)
                .resonance(8)
                .volume(6)
                .condition(5)
                .portrait(getResourceAsByteArray("../musician/meola.png"))
                .user(alice)
                .build();

        Musician jackson = Musician.builder()
                .id(UUID.fromString("ff327e8a-77c0-4f9b-90a2-89e16895d1e1"))
                .name("Michael Jackson")
                .age(50)
                .biography("King of pop.")
                .experience(90)
                .level(100)
                .genre(pop)
                .toneQuality(10)
                .resonance(10)
                .volume(8)
                .condition(0)
                .portrait(getResourceAsByteArray("../musician/jackson.png"))
                .user(alice)
                .build();

        musicianService.create(meola);
        musicianService.create(slash);
        musicianService.create(jackson);
        musicianService.create(mayer);

        requestContextController.deactivate();
    }

    
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
