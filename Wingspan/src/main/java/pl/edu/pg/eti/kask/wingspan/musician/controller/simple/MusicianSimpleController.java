package pl.edu.pg.eti.kask.wingspan.musician.controller.simple;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import pl.edu.pg.eti.kask.wingspan.musician.controller.api.MusicianController;
import pl.edu.pg.eti.kask.wingspan.musician.dto.GetMusicianResponse;
import pl.edu.pg.eti.kask.wingspan.musician.dto.GetMusiciansResponse;
import pl.edu.pg.eti.kask.wingspan.musician.dto.PatchMusicianRequest;
import pl.edu.pg.eti.kask.wingspan.musician.dto.PutMusicianRequest;
import pl.edu.pg.eti.kask.wingspan.musician.entity.Musician;
import pl.edu.pg.eti.kask.wingspan.musician.service.MusicianService;
import pl.edu.pg.eti.kask.wingspan.component.DtoFunctionFactory;
import pl.edu.pg.eti.kask.wingspan.controller.servlet.exception.BadRequestException;
import pl.edu.pg.eti.kask.wingspan.controller.servlet.exception.NotFoundException;

import java.io.InputStream;
import java.util.UUID;

/**
 * Simple framework agnostic implementation of controller.
 */
@RequestScoped
public class MusicianSimpleController implements MusicianController {

    /**
     * Musician service.
     */
    private final MusicianService service;

    /**
     * Factory producing functions for conversion between DTO and entities.
     */
    private final DtoFunctionFactory factory;

    /**
     * @param service musician service
     * @param factory factory producing functions for conversion between DTO and entities
     */
    @Inject
    public MusicianSimpleController(MusicianService service, DtoFunctionFactory factory) {
        this.service = service;
        this.factory = factory;
    }

    @Override
    public GetMusiciansResponse getMusicians() {
        return factory.musiciansToResponse().apply(service.findAll());
    }

    @Override
    public GetMusiciansResponse getGenreMusicians(UUID id) {
        return service.findAllByGenre(id)
                .map(factory.musiciansToResponse())
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public GetMusiciansResponse getUserMusicians(UUID id) {
        return service.findAllByUser(id)
                .map(factory.musiciansToResponse())
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public GetMusicianResponse getMusician(UUID uuid) {
        return service.find(uuid)
                .map(factory.musicianToResponse())
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public void putMusician(UUID id, PutMusicianRequest request) {
        try {
            service.create(factory.requestToMusician().apply(id, request));
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException(ex);
        }
    }

    @Override
    public void patchMusician(UUID id, PatchMusicianRequest request) {
        service.find(id).ifPresentOrElse(
                entity -> service.update(factory.updateMusician().apply(entity, request)),
                () -> {
                    throw new NotFoundException();
                }
        );
    }

    @Override
    public void deleteMusician(UUID id) {
        service.find(id).ifPresentOrElse(
                entity -> service.delete(id),
                () -> {
                    throw new NotFoundException();
                }
        );
    }

    @Override
    public byte[] getMusicianPortrait(UUID id) {
        return service.find(id)
                .map(Musician::getPortrait)
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public void putMusicianPortrait(UUID id, InputStream portrait) {
        service.find(id).ifPresentOrElse(
                entity -> service.updatePortrait(id, portrait),
                () -> {
                    throw new NotFoundException();
                }
        );
    }

}
