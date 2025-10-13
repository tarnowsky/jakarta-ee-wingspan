package pl.edu.pg.eti.kask.wingspan.bird.controller.api;

import pl.edu.pg.eti.kask.wingspan.bird.dto.GetBirdResponse;
import pl.edu.pg.eti.kask.wingspan.bird.dto.GetBirdsResponse;
import pl.edu.pg.eti.kask.wingspan.bird.dto.PatchBirdRequest;
import pl.edu.pg.eti.kask.wingspan.bird.dto.PutBirdRequest;


import java.io.InputStream;
import java.util.UUID;

public interface BirdController {
    /**
     * @return all birds representation
     */
    GetBirdsResponse getBirds();

    /**
     * @param id action's id
     * @return birds representation
     */
    GetBirdsResponse getActionBirds(UUID id);

    /**
     * @param id user's id
     * @return birds representation
     */
    GetBirdsResponse getUserBirds(UUID id);

    /**
     * @param uuid bird's id
     * @return bird representation
     */
    GetBirdResponse getBird(UUID uuid);

    /**
     * @param id      bird's id
     * @param request new bird representation
     */
    void putBird(UUID id, PutBirdRequest request);

    /**
     * @param id      bird's id
     * @param request bird update representation
     */
    void patchBird(UUID id, PatchBirdRequest request);

    /**
     * @param id bird's id
     */
    void deleteBird(UUID id);

    /**
     * @param id bird's id
     * @return bird's illustration
     */
    byte[] getBirdIllustration(UUID id);

    /**
     * @param id       bird's id
     * @param illustration bird's new avatar
     */
    void putBirdIllustration(UUID id, InputStream illustration);


}
