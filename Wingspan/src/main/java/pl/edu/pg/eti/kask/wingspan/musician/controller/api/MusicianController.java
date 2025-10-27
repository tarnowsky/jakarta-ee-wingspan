package pl.edu.pg.eti.kask.wingspan.musician.controller.api;

import pl.edu.pg.eti.kask.wingspan.musician.dto.GetMusicianResponse;
import pl.edu.pg.eti.kask.wingspan.musician.dto.GetMusiciansResponse;
import pl.edu.pg.eti.kask.wingspan.musician.dto.PatchMusicianRequest;
import pl.edu.pg.eti.kask.wingspan.musician.dto.PutMusicianRequest;

import java.io.InputStream;
import java.util.UUID;

/**
 * Controller for managing collections musicians' representations.
 */
public interface MusicianController {

    /**
     * @return all musicians representation
     */
    GetMusiciansResponse getMusicians();

    /**
     * @param id genre's id
     * @return musicians representation
     */
    GetMusiciansResponse getGenreMusicians(UUID id);

    /**
     * @param id user's id
     * @return musicians representation
     */
    GetMusiciansResponse getUserMusicians(UUID id);

    /**
     * @param uuid musician's id
     * @return musician representation
     */
    GetMusicianResponse getMusician(UUID uuid);

    /**
     * @param id      musician's id
     * @param request new musician representation
     */
    void putMusician(UUID id, PutMusicianRequest request);

    /**
     * @param id      musician's id
     * @param request musician update representation
     */
    void patchMusician(UUID id, PatchMusicianRequest request);

    /**
     * @param id musician's id
     */
    void deleteMusician(UUID id);

    /**
     * @param id musician's id
     * @return musician's portrait
     */
    byte[] getMusicianPortrait(UUID id);

    /**
     * @param id       musician's id
     * @param portrait musician's new avatar
     */
    void putMusicianPortrait(UUID id, InputStream portrait);

}
