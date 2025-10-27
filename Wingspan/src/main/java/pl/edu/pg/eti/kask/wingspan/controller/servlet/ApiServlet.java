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

/**
 * Central API servlet for fetching all request from the client and preparing responses. Servlet API does not allow
 * named path parameters so wildcard is used.
 */
@WebServlet(urlPatterns = {
        ApiServlet.Paths.API + "/*"
})
@MultipartConfig(maxFileSize = 200 * 1024)
public class ApiServlet extends HttpServlet {

    /**
     * Controller for managing collections musicians' representations.
     */
    private final MusicianController musicianController;

    /**
     * Controller for managing collections genres' representations.
     */
    private final GenreController genreController;

    /**
     * Definition of paths supported by this servlet. Separate inner class provides composition for static fields.
     */
    public static final class Paths {

        /**
         * All API operations. Version v1 will be used to distinguish from other implementations.
         */
        public static final String API = "/api";

    }

    /**
     * Patterns used for checking servlet path.
     */
    public static final class Patterns {

        /**
         * UUID
         */
        private static final Pattern UUID = Pattern.compile("[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}");

        /**
         * All musicians.
         */
        public static final Pattern MUSICIANS = Pattern.compile("/musicians/?");

        /**
         * Single musician.
         */
        public static final Pattern MUSICIAN = Pattern.compile("/musicians/(%s)".formatted(UUID.pattern()));

        /**
         * Single musician's portrait.
         */
        public static final Pattern MUSICIAN_PORTRAIT = Pattern.compile("/musicians/(%s)/portrait".formatted(UUID.pattern()));

        /**
         * All genres.
         */
        public static final Pattern GENRE = Pattern.compile("/genres/?");

        /**
         * All musicians of single genre.
         */
        public static final Pattern PROFESSION_MUSICIANS = Pattern.compile("/genres/(%s)/musicians/?".formatted(UUID.pattern()));

        /**
         * All musicians of single user.
         */
        public static final Pattern USER_MUSICIANS = Pattern.compile("/users/(%s)/musicians/?".formatted(UUID.pattern()));

    }

    /**
     * JSON-B mapping object. According to open liberty documentation creating this is expensive. The JSON-B is only one
     * of many solutions. JSON strings can be built by hand {@link StringBuilder} or with JSON-P API. Both JSON-B and
     * JSON-P are part of Jakarta EE whereas JSON-B is newer standard.
     */
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

    /**
     * Called by the server (via the <code>service</code> method) to allow a servlet to handle a PATCH request.
     *
     * @param request  {@link HttpServletRequest} object that contains the request the client made of the servlet
     * @param response {@link HttpServletResponse} object that contains the response the servlet returns to the client
     * @throws ServletException if the request for the PATCH cannot be handled
     * @throws IOException      if an input or output error occurs while the servlet is handling the PATCH request
     */
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

    /**
     * Extracts UUID from path using provided pattern. Pattern needs to contain UUID in first regular expression group.
     *
     * @param pattern regular expression pattern with
     * @param path    request path containing UUID
     * @return extracted UUID
     */
    private static UUID extractUuid(Pattern pattern, String path) {
        Matcher matcher = pattern.matcher(path);
        if (matcher.matches()) {
            return UUID.fromString(matcher.group(1));
        }
        throw new IllegalArgumentException("No UUID in path.");
    }

    /**
     * Gets path info from the request and returns it. No null is returned, instead empty string is used.
     *
     * @param request original servlet request
     * @return path info (not null)
     */
    private String parseRequestPath(HttpServletRequest request) {
        String path = request.getPathInfo();
        path = path != null ? path : "";
        return path;
    }

    /**
     * Creates URL using host, port and context root from servlet request and any number of path elements. If any of
     * path elements starts or ends with '/' musician, that musician is removed.
     *
     * @param request servlet request
     * @param paths   any (can be none) number of path elements
     * @return created url
     */
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
