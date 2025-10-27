package pl.edu.pg.eti.kask.wingspan.controller.servlet;

import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pl.edu.pg.eti.kask.wingspan.musician.controller.api.MusicianController;
import pl.edu.pg.eti.kask.wingspan.musician.controller.api.GenreController;
import pl.edu.pg.eti.kask.wingspan.musician.dto.PatchMusicianRequest;
import pl.edu.pg.eti.kask.wingspan.musician.dto.PutMusicianRequest;

import java.io.IOException;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@WebServlet(urlPatterns = {
        ApiServlet.Paths.API + "/*"
})
@MultipartConfig(maxFileSize = 200 * 1024)
public class ApiServlet extends HttpServlet {

    
    private final MusicianController musicianController;

    
    private final GenreController genreController;

    
    public static final class Paths {

        
        public static final String API = "/api";

    }

    
    public static final class Patterns {

        
        private static final Pattern UUID = Pattern.compile("[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}");

        
        public static final Pattern MUSICIANS = Pattern.compile("/musicians/?");

        
        public static final Pattern MUSICIAN = Pattern.compile("/musicians/(%s)".formatted(UUID.pattern()));

        
        public static final Pattern MUSICIAN_PORTRAIT = Pattern.compile("/musicians/(%s)/portrait".formatted(UUID.pattern()));

        
        public static final Pattern GENRE = Pattern.compile("/genres/?");

        
        public static final Pattern PROFESSION_MUSICIANS = Pattern.compile("/genres/(%s)/musicians/?".formatted(UUID.pattern()));

        
        public static final Pattern USER_MUSICIANS = Pattern.compile("/users/(%s)/musicians/?".formatted(UUID.pattern()));

    }

    
    private final Jsonb jsonb = JsonbBuilder.create();

    @Inject
    public ApiServlet(MusicianController musicianController, GenreController genreController) {
        this.musicianController = musicianController;
        this.genreController = genreController;
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getMethod().equals("PATCH")) {
            doPatch(request, response);
        } else {
            super.service(request, response);
        }
    }

    @SuppressWarnings("RedundantThrows")
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = parseRequestPath(request);
        String servletPath = request.getServletPath();
        if (Paths.API.equals(servletPath)) {
            if (path.matches(Patterns.MUSICIANS.pattern())) {
                response.setContentType("application/json");
                response.getWriter().write(jsonb.toJson(musicianController.getMusicians()));
                return;
            } else if (path.matches(Patterns.MUSICIAN.pattern())) {
                response.setContentType("application/json");
                UUID uuid = extractUuid(Patterns.MUSICIAN, path);
                response.getWriter().write(jsonb.toJson(musicianController.getMusician(uuid)));
                return;
            } else if (path.matches(Patterns.GENRE.pattern())) {
                response.setContentType("application/json");
                response.getWriter().write(jsonb.toJson(genreController.getGenres()));
                return;
            } else if (path.matches(Patterns.PROFESSION_MUSICIANS.pattern())) {
                response.setContentType("application/json");
                UUID uuid = extractUuid(Patterns.PROFESSION_MUSICIANS, path);
                response.getWriter().write(jsonb.toJson(musicianController.getGenreMusicians(uuid)));
                return;
            } else if (path.matches(Patterns.USER_MUSICIANS.pattern())) {
                response.setContentType("application/json");
                UUID uuid = extractUuid(Patterns.USER_MUSICIANS, path);
                response.getWriter().write(jsonb.toJson(musicianController.getUserMusicians(uuid)));
                return;
            } else if (path.matches(Patterns.MUSICIAN_PORTRAIT.pattern())) {
                response.setContentType("image/png");//could be dynamic but atm we support only one format
                UUID uuid = extractUuid(Patterns.MUSICIAN_PORTRAIT, path);
                byte[] portrait = musicianController.getMusicianPortrait(uuid);
                response.setContentLength(portrait.length);
                response.getOutputStream().write(portrait);
                return;
            }
        }
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = parseRequestPath(request);
        String servletPath = request.getServletPath();
        if (Paths.API.equals(servletPath)) {
            if (path.matches(Patterns.MUSICIAN.pattern())) {
                UUID uuid = extractUuid(Patterns.MUSICIAN, path);
                musicianController.putMusician(uuid, jsonb.fromJson(request.getReader(), PutMusicianRequest.class));
                response.addHeader("Location", createUrl(request, Paths.API, "musicians", uuid.toString()));
                return;
            } else if (path.matches(Patterns.MUSICIAN_PORTRAIT.pattern())) {
                UUID uuid = extractUuid(Patterns.MUSICIAN_PORTRAIT, path);
                musicianController.putMusicianPortrait(uuid, request.getPart("portrait").getInputStream());
                return;
            }
        }
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    @SuppressWarnings("RedundantThrows")
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = parseRequestPath(request);
        String servletPath = request.getServletPath();
        if (Paths.API.equals(servletPath)) {
            if (path.matches(Patterns.MUSICIAN.pattern())) {
                UUID uuid = extractUuid(Patterns.MUSICIAN, path);
                musicianController.deleteMusician(uuid);
                return;
            }
        }
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    
    @SuppressWarnings("RedundantThrows")
    protected void doPatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = parseRequestPath(request);
        String servletPath = request.getServletPath();
        if (Paths.API.equals(servletPath)) {
            if (path.matches(Patterns.MUSICIAN.pattern())) {
                UUID uuid = extractUuid(Patterns.MUSICIAN, path);
                musicianController.patchMusician(uuid, jsonb.fromJson(request.getReader(), PatchMusicianRequest.class));
                return;
            }
        }
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    
    private static UUID extractUuid(Pattern pattern, String path) {
        Matcher matcher = pattern.matcher(path);
        if (matcher.matches()) {
            return UUID.fromString(matcher.group(1));
        }
        throw new IllegalArgumentException("No UUID in path.");
    }

    
    private String parseRequestPath(HttpServletRequest request) {
        String path = request.getPathInfo();
        path = path != null ? path : "";
        return path;
    }

    
    public static String createUrl(HttpServletRequest request, String... paths) {
        StringBuilder builder = new StringBuilder();
        builder.append(request.getScheme())
                .append("://")
                .append(request.getServerName())
                .append(":")
                .append(request.getServerPort())
                .append(request.getContextPath());
        for (String path : paths) {
            builder.append("/")
                    .append(path, path.startsWith("/") ? 1 : 0, path.endsWith("/") ? path.length() - 1 : path.length());
        }
        return builder.toString();
    }

}
