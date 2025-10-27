package pl.edu.pg.eti.kask.wingspan.musician.view;

import jakarta.enterprise.context.Conversation;
import jakarta.enterprise.context.ConversationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import pl.edu.pg.eti.kask.wingspan.musician.model.MusicianCreateModel;
import pl.edu.pg.eti.kask.wingspan.musician.model.GenreModel;
import pl.edu.pg.eti.kask.wingspan.musician.service.MusicianService;
import pl.edu.pg.eti.kask.wingspan.musician.service.GenreService;
import pl.edu.pg.eti.kask.wingspan.component.ModelFunctionFactory;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * View bean for rendering single musician create form. Creating a musician is divided into number of steps where each
 * step is separate JSF view. In order to use single bean, conversation scope is used.
 */
@ConversationScoped
@Named
@Log
@NoArgsConstructor(force = true)
public class MusicianCreate implements Serializable {

    /**
     * Service for managing musicians.
     */
    private final MusicianService musicianService;

    /**
     * Service for managing genres.
     */
    private final GenreService genreService;

    /**
     * Factory producing functions for conversion between models and entities.
     */
    private final ModelFunctionFactory factory;

    /**
     * Musician exposed to the view.
     */
    @Getter
    private MusicianCreateModel musician;

    /**
     * Available genres.
     */
    @Getter
    private List<GenreModel> genres;

    /**
     * Injected conversation.
     */
    private final Conversation conversation;

    /**
     * @param musicianService  service for managing musicians
     * @param genreService service for managing genres
     * @param factory           factory producing functions for conversion between models and entities
     * @param conversation      injected conversation
     */
    @Inject
    public MusicianCreate(
            MusicianService musicianService,
            GenreService genreService,
            ModelFunctionFactory factory,
            Conversation conversation
    ) {
        this.musicianService = musicianService;
        this.factory = factory;
        this.genreService = genreService;
        this.conversation = conversation;
    }

    /**
     * In order to prevent calling service on different steps of JSF request lifecycle, model property is cached within
     * field and initialized during init of the view. @PostConstruct method is called after h:form header is already
     * rendered. Conversation should be started in f:metadata/f:event.
     */
    public void init() {
        if (conversation.isTransient()) {
            genres = genreService.findAll().stream()
                    .map(factory.genreToModel())
                    .collect(Collectors.toList());
            musician = MusicianCreateModel.builder()
                    .id(UUID.randomUUID())
                    .build();
            conversation.begin();
        }
    }

    /**
     * @return genre navigation case
     */
    public String goToGenreAction() {
        return "/musician/musician_create__genre.xhtml?faces-redirect=true";
    }

    /**
     * @return techniques navigation case
     */
    public String goToTechniquesAction() {
        return "/musician/musician_create__techniques.xhtml?faces-redirect=true";
    }

    /**
     * @return portrait navigation case
     */
    public String goToPortraitAction() {
        return "/musician/musician_create__portrait.xhtml?faces-redirect=true";
    }

    public String resetPortraitAction() {
        musician.setPortrait(null);
        return goToPortraitAction();
    }

    /**
     * @return basic information navigation case
     */
    public Object goToBasicAction() {
        return "/musician/musician_create__basic.xhtml?faces-redirect=true";
    }

    /**
     * Cancels musician creation process.
     *
     * @return musicians list navigation case
     */
    public String cancelAction() {
        conversation.end();
        return "/musician/musician_list.xhtml?faces-redirect=true";
    }

    /**
     * Sets default musician properties (leve land condition).
     *
     * @return confirmation navigation case
     */
    public String goToConfirmAction() {
        musician.setLevel(1);
        musician.setExperience(0);
        musician.setCondition(musician.getResonance() == null ? 0 : musician.getResonance() * 2);
        return "/musician/musician_create__confirm.xhtml?faces-redirect=true";
    }

    /**
     * Stores new musician and ends conversation.
     *
     * @return musicians list navigation case
     */
    public String saveAction() {
        musicianService.create(factory.modelToMusician().apply(musician));
        conversation.end();
        return "/musician/musician_list.xhtml?faces-redirect=true";
    }

    /**
     * @return current conversation id
     */
    public String getConversationId() {
        return conversation.getId();
    }

    public String getMusicianPortraitUrl() {
        return "/view/api/v1/musicians/new/portrait?cid=%s".formatted(getConversationId());
    }

}
