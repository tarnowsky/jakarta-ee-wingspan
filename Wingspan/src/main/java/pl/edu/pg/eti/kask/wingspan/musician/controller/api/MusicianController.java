package pl.edu.pg.eti.kask.wingspan.musician.controller.api;

import pl.edu.pg.eti.kask.wingspan.musician.dto.GetMusicianResponse;
import pl.edu.pg.eti.kask.wingspan.musician.dto.GetMusiciansResponse;
import pl.edu.pg.eti.kask.wingspan.musician.dto.PatchMusicianRequest;
import pl.edu.pg.eti.kask.wingspan.musician.dto.PutMusicianRequest;

import java.io.InputStream;
import java.util.UUID;


public interface MusicianController {

    
    GetMusiciansResponse getMusicians();

    
    GetMusiciansResponse getGenreMusicians(UUID id);

    
    GetMusiciansResponse getUserMusicians(UUID id);

    
    GetMusicianResponse getMusician(UUID uuid);

    
    void putMusician(UUID id, PutMusicianRequest request);

    
    void patchMusician(UUID id, PatchMusicianRequest request);

    
    void deleteMusician(UUID id);

    
    byte[] getMusicianPortrait(UUID id);

    
    void putMusicianPortrait(UUID id, InputStream portrait);

}
