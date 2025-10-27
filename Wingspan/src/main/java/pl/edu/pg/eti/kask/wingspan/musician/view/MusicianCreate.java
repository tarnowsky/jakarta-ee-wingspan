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


@ConversationScoped
@Named
@Log
@NoArgsConstructor(force = true)
public class MusicianCreate implements Serializable {

    
    private final MusicianService musicianService;

    
    private final GenreService genreService;

    
    private final ModelFunctionFactory factory;

    
    @Getter
    private MusicianCreateModel musician;

    
    @Getter
    private List<GenreModel> genres;

    
    private final Conversation conversation;

    
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

    
    public String goToGenreAction() {
        return "/musician/musician_create__genre.xhtml?faces-redirect=true";
    }

    
    public String goToTechniquesAction() {
        return "/musician/musician_create__techniques.xhtml?faces-redirect=true";
    }

    
    public String goToPortraitAction() {
        return "/musician/musician_create__portrait.xhtml?faces-redirect=true";
    }

    public String resetPortraitAction() {
        musician.setPortrait(null);
        return goToPortraitAction();
    }

    
    public Object goToBasicAction() {
        return "/musician/musician_create__basic.xhtml?faces-redirect=true";
    }

    
    public String cancelAction() {
        conversation.end();
        return "/musician/musician_list.xhtml?faces-redirect=true";
    }

    
    public String goToConfirmAction() {
        musician.setLevel(1);
        musician.setExperience(0);
        musician.setCondition(musician.getResonance() == null ? 0 : musician.getResonance() * 2);
        return "/musician/musician_create__confirm.xhtml?faces-redirect=true";
    }

    
    public String saveAction() {
        musicianService.create(factory.modelToMusician().apply(musician));
        conversation.end();
        return "/musician/musician_list.xhtml?faces-redirect=true";
    }

    
    public String getConversationId() {
        return conversation.getId();
    }

    public String getMusicianPortraitUrl() {
        return "/view/api/v1/musicians/new/portrait?cid=%s".formatted(getConversationId());
    }

}
