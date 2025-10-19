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
import pl.edu.pg.eti.kask.wingspan.bird.controller.api.ActionController;
import pl.edu.pg.eti.kask.wingspan.bird.controller.api.BirdController;
import pl.edu.pg.eti.kask.wingspan.bird.dto.PatchBirdRequest;
import pl.edu.pg.eti.kask.wingspan.bird.dto.PutBirdRequest;
import pl.edu.pg.eti.kask.wingspan.user.controller.api.UserController;
import pl.edu.pg.eti.kask.wingspan.user.dto.PatchUserRequest;
import pl.edu.pg.eti.kask.wingspan.user.dto.PutUserRequest;

import java.io.IOException;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(urlPatterns = {ApiServlet.Paths.API + "/*"})
@MultipartConfig(maxFileSize = 200 * 1024)
public class ApiServlet extends HttpServlet {

    /**
     * Controller for managing collections birds' representations.
     */
    private final BirdController birdController;

    /**
     * Controller for managing collections actions' representations.
     */
    private final ActionController actionController;

    /**
     * Controller for managing collections users' representations.
     */
    private final UserController userController;

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
         * All birds.
         */
        public static final Pattern BIRDS = Pattern.compile("/birds/?");

        /**
         * Single bird.
         */
        public static final Pattern BIRD = Pattern.compile("/birds/(%s)".formatted(UUID.pattern()));

        /**
         * Single bird's illustration.
         */
        public static final Pattern BIRD_ILLUSTRATION = Pattern.compile("/birds/(%s)/illustration".formatted(UUID.pattern()));

        /**
         * All actions.
         */
        public static final Pattern ACTIONS = Pattern.compile("/actions/?");

        /**
         * All birds of single action.
         */
        public static final Pattern ACTION_BIRDS = Pattern.compile("/actions/(%s)/birds/?".formatted(UUID.pattern()));

        /**
         * All birds of single user.
         */
        public static final Pattern USER_BIRDS = Pattern.compile("/users/(%s)/birds/?".formatted(UUID.pattern()));

        public static final Pattern USERS = Pattern.compile("/users/?");
        public static final Pattern USER = Pattern.compile("/users/(%s)".formatted(UUID.pattern()));
        public static final Pattern USER_AVATAR = Pattern.compile("/users/(%s)/avatar".formatted(UUID.pattern()));
    }

    private final Jsonb jsonb = JsonbBuilder.create();

    @Inject
    public ApiServlet(BirdController birdController, ActionController actionController, UserController userController) {
        this.birdController = birdController;
        this.actionController = actionController;
        this.userController = userController;
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
            if (path.matches(Patterns.BIRDS.pattern())) {
                response.setContentType("application/json");
                response.getWriter().write(jsonb.toJson(birdController.getBirds()));
                return;
            } else if (path.matches(Patterns.BIRD.pattern())) {
                response.setContentType("application/json");
                UUID uuid = extractUuid(Patterns.BIRD, path);
                response.getWriter().write(jsonb.toJson(birdController.getBird(uuid)));
                return;
            } else if (path.matches(Patterns.ACTIONS.pattern())) {
                response.setContentType("application/json");
                response.getWriter().write(jsonb.toJson(actionController.getActions()));
                return;
            } else if (path.matches(Patterns.ACTION_BIRDS.pattern())) {
                response.setContentType("application/json");
                UUID uuid = extractUuid(Patterns.ACTION_BIRDS, path);
                response.getWriter().write(jsonb.toJson(birdController.getActionBirds(uuid)));
                return;
            } else if (path.matches(Patterns.USER_BIRDS.pattern())) {
                response.setContentType("application/json");
                UUID uuid = extractUuid(Patterns.USER_BIRDS, path);
                response.getWriter().write(jsonb.toJson(birdController.getUserBirds(uuid)));
                return;
            } else if (path.matches(Patterns.BIRD_ILLUSTRATION.pattern())) {
                response.setContentType("image/png");//could be dynamic but atm we support only one format
                UUID uuid = extractUuid(Patterns.BIRD_ILLUSTRATION, path);
                byte[] illustration = birdController.getBirdIllustration(uuid);
                response.setContentLength(illustration.length);
                response.getOutputStream().write(illustration);
                return;
            } else if (path.matches(Patterns.USERS.pattern())) {
                response.setContentType("application/json");
                response.getWriter().write(jsonb.toJson(userController.getUsers()));
                return;
            } else if (path.matches(Patterns.USER.pattern())) {
                response.setContentType("application/json");
                UUID uuid = extractUuid(Patterns.USER, path);
                response.getWriter().write(jsonb.toJson(userController.getUser(uuid)));
                return;
            } else if (path.matches(Patterns.USER_AVATAR.pattern())) {
                response.setContentType("image/png");
                UUID uuid = extractUuid(Patterns.USER_AVATAR, path);
                try {
                    byte[] avatar = userController.getUserAvatar(uuid);
                    response.setContentLength(avatar.length);
                    response.getOutputStream().write(avatar);
                } catch (IllegalStateException e) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Avatar not found");
                }
                return;
            }
        }
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = parseRequestPath(request);
        String servletPath = request.getServletPath();
        if (Paths.API.equals(servletPath)) {
            if (path.matches(Patterns.BIRD.pattern())) {
                UUID uuid = extractUuid(Patterns.BIRD, path);
                birdController.putBird(uuid, jsonb.fromJson(request.getReader(), PutBirdRequest.class));
                response.addHeader("Location", createUrl(request, Paths.API, "birds", uuid.toString()));
                return;
            } else if (path.matches(Patterns.BIRD_ILLUSTRATION.pattern())) {
                UUID uuid = extractUuid(Patterns.BIRD_ILLUSTRATION, path);
                birdController.putBirdIllustration(uuid, request.getPart("illustration").getInputStream());
                return;
            } else if (path.matches(Patterns.USER.pattern())) {
                UUID uuid = extractUuid(Patterns.USER, path);
                try {
                    userController.putUser(uuid, jsonb.fromJson(request.getReader(), PutUserRequest.class));
                    response.addHeader("Location", createUrl(request, Paths.API, "users", uuid.toString()));
                } catch (IllegalArgumentException e) {
                    response.sendError(HttpServletResponse.SC_CONFLICT, e.getMessage());
                }
                return;
            } else if (path.matches(Patterns.USER_AVATAR.pattern())) {
                UUID uuid = extractUuid(Patterns.USER_AVATAR, path);
                userController.putUserAvatar(uuid, request.getPart("avatar").getInputStream());
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
            if (path.matches(Patterns.BIRD.pattern())) {
                UUID uuid = extractUuid(Patterns.BIRD, path);
                birdController.deleteBird(uuid);
                return;
            } else if (path.matches(Patterns.USER.pattern())) {
                UUID uuid = extractUuid(Patterns.USER, path);
                userController.deleteUser(uuid);
                return;
            } else if (path.matches(Patterns.USER_AVATAR.pattern())) {
                UUID uuid = extractUuid(Patterns.USER_AVATAR, path);
                userController.deleteUserAvatar(uuid);
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
            if (path.matches(Patterns.BIRD.pattern())) {
                UUID uuid = extractUuid(Patterns.BIRD, path);
                birdController.patchBird(uuid, jsonb.fromJson(request.getReader(), PatchBirdRequest.class));
                return;
            } else if (path.matches(Patterns.USER.pattern())) {
                UUID uuid = extractUuid(Patterns.USER, path);
                userController.patchUser(uuid, jsonb.fromJson(request.getReader(), PatchUserRequest.class));
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
     * path elements starts or ends with '/' bird, that bird is removed.
     *
     * @param request servlet request
     * @param paths   any (can be none) number of path elements
     * @return created url
     */
    public static String createUrl(HttpServletRequest request, String... paths) {
        StringBuilder builder = new StringBuilder();
        builder.append(request.getScheme()).append("://").append(request.getServerName()).append(":").append(request.getServerPort()).append(request.getContextPath());
        for (String path : paths) {
            builder.append("/").append(path, path.startsWith("/") ? 1 : 0, path.endsWith("/") ? path.length() - 1 : path.length());
        }
        return builder.toString();
    }

}

