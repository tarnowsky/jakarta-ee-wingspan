package pl.edu.pg.eti.kask.wingspan.musician.servlet;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pl.edu.pg.eti.kask.wingspan.musician.view.MusicianCreate;

import java.io.IOException;


@WebServlet(urlPatterns = PortraitServlet.Paths.NEW_PORTRAIT)
public class PortraitServlet extends HttpServlet {

    
    private final MusicianCreate musicianCreate;

    @Inject
    public PortraitServlet(MusicianCreate musicianCreate) {
        this.musicianCreate = musicianCreate;
    }

    
    public static class Paths {

        
        public static final String NEW_PORTRAIT = "/view/api/v1/musicians/new/portrait";

    }

    @SuppressWarnings("RedundantThrows")
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getPortrait(request, response);
    }

    
    private void getPortrait(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (musicianCreate.getMusician() != null && musicianCreate.getMusician().getPortrait() != null) {
            byte[] portrait = musicianCreate.getMusician().getPortrait().getInputStream().readAllBytes();
            if (portrait != null) {
                response.setContentType("image/png");//could be dynamic but atm we support only one format
                response.setContentLength(portrait.length);
                response.getOutputStream().write(portrait);
                return;
            }
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }

    }
}
