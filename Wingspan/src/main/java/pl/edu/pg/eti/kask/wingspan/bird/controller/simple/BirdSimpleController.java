package pl.edu.pg.eti.kask.wingspan.bird.controller.simple;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import pl.edu.pg.eti.kask.wingspan.bird.controller.api.BirdController;
import pl.edu.pg.eti.kask.wingspan.bird.dto.GetBirdResponse;
import pl.edu.pg.eti.kask.wingspan.bird.dto.GetBirdsResponse;
import pl.edu.pg.eti.kask.wingspan.bird.dto.PatchBirdRequest;
import pl.edu.pg.eti.kask.wingspan.bird.dto.PutBirdRequest;
import pl.edu.pg.eti.kask.wingspan.bird.entity.Bird;
import pl.edu.pg.eti.kask.wingspan.bird.service.BirdService;
import pl.edu.pg.eti.kask.wingspan.component.DtoFunctionFactory;
import pl.edu.pg.eti.kask.wingspan.controller.servlet.exception.BadRequestException;
import pl.edu.pg.eti.kask.wingspan.controller.servlet.exception.NotFoundException;

import java.io.InputStream;
import java.util.UUID;

/**
 * Simple framework agnostic implementation of controller.
 */
@RequestScoped
public class BirdSimpleController implements BirdController {

    /**
     * Bird service.
     */
    private final BirdService service;

    /**
     * Factory producing functions for conversion between DTO and entities.
     */
    private final DtoFunctionFactory factory;

    /**
     * @param service bird service
     * @param factory factory producing functions for conversion between DTO and entities
     */
    @Inject
    public BirdSimpleController(BirdService service, DtoFunctionFactory factory) {
        this.service = service;
        this.factory = factory;
    }

    @Override
    public GetBirdsResponse getBirds() {
        return factory.birdsToResponse().apply(service.findAll());
    }

    @Override
    public GetBirdsResponse getActionBirds(UUID id) {
        return service.findAllByAction(id)
                .map(factory.birdsToResponse())
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public GetBirdsResponse getUserBirds(UUID id) {
        return service.findAllByUser(id)
                .map(factory.birdsToResponse())
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public GetBirdResponse getBird(UUID uuid) {
        return service.find(uuid)
                .map(factory.birdToResponse())
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public void putBird(UUID id, PutBirdRequest request) {
        try {
            service.create(factory.requestToBird().apply(id, request));
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException(ex);
        }
    }

    @Override
    public void patchBird(UUID id, PatchBirdRequest request) {
        service.find(id).ifPresentOrElse(
                entity -> service.update(factory.updateBird().apply(entity, request)),
                () -> {
                    throw new NotFoundException();
                }
        );
    }

    @Override
    public void deleteBird(UUID id) {
        service.find(id).ifPresentOrElse(
                entity -> service.delete(id),
                () -> {
                    throw new NotFoundException();
                }
        );
    }

    @Override
    public byte[] getBirdIllustration(UUID id) {
        return service.find(id)
                .map(Bird::getIllustration)
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public void putBirdIllustration(UUID id, InputStream illustration) {
        service.find(id).ifPresentOrElse(
                entity -> service.updateIllustration(id, illustration),
                () -> {
                    throw new NotFoundException();
                }
        );
    }

}
