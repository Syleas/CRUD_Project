import static spark.Spark.*;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.freemarker.FreeMarkerRoute;

import java.util.*;

public class App {
    //Utilizar ArrayList si MongoDB no funciona
    //public static SongArrayList<Song> songDbService = new SongArrayList<Song>();
    public static MongoDB_Access<Song> songDbService = new MongoDB_Access<Song>();

    public static void main(String[] args) {
        get(new FreeMarkerRoute("/") {
            @Override
            public ModelAndView handle(Request request, Response response) {
                Map<String, Object> viewObjects = new HashMap<String, Object>();
                ArrayList<Song> songs = songDbService.readAll();
                if (songs.isEmpty()) {
                    viewObjects.put("hasNoSongs", "No tienes canciones favoritas.");
                } else {
                    Deque<Song> showSongs = new ArrayDeque<Song>();
                    for (Song song : songs) {
                        if (song.readable()) {
                            showSongs.addFirst(song);
                        }
                    }
                    viewObjects.put("songs", showSongs);
                }
                viewObjects.put("templateName", "songList.ftl");
                return modelAndView(viewObjects, "layout.ftl");
            }
        });

        get(new FreeMarkerRoute("/song/create") {
            @Override
            public Object handle(Request request, Response response) {
                Map<String, Object> viewObjects = new HashMap<String, Object>();
                viewObjects.put("templateName", "songForm.ftl");
                return modelAndView(viewObjects, "layout.ftl");
            }
        });

        post(new Route("/song/create") {
            @Override
            public Object handle(Request request, Response response) {
                String Name = request.queryParams("song-Name");
                String Length = request.queryParams("song-Length");
                String Author = request.queryParams("song-Author");
                Song song = new Song(songDbService.readAll().size(), Name, Length, Author);
                songDbService.create(song);
                response.status(201);
                response.redirect("/");
                return "";
            }
        });

        get(new FreeMarkerRoute("/song/read/:id") {
            @Override
            public Object handle(Request request, Response response) {
                Integer id = Integer.parseInt(request.params(":id"));
                Map<String, Object> viewObjects = new HashMap<String, Object>();
                viewObjects.put("templateName", "songRead.ftl");
                viewObjects.put("song", songDbService.readOne(id));
                return modelAndView(viewObjects, "layout.ftl");
            }
        });

        get(new FreeMarkerRoute("/song/update/:id") {
            @Override
            public Object handle(Request request, Response response) {
                Integer id = Integer.parseInt(request.params(":id"));
                Map<String, Object> viewObjects = new HashMap<String, Object>();
                viewObjects.put("templateName", "songForm.ftl");
                viewObjects.put("song", songDbService.readOne(id));
                return modelAndView(viewObjects, "layout.ftl");
            }
        });

        post(new Route("/song/update/:id") {
            @Override
            public Object handle(Request request, Response response) {
                Integer id = Integer.parseInt(request.queryParams("song-id"));
                String Name = request.queryParams("song-Name");
                String Length = request.queryParams("song-Length");
                String Author = request.queryParams("song-Author");
                songDbService.update(id, Name, Length, Author);
                response.status(200);
                response.redirect("/");
                return "";
            }
        });

        get(new Route("/song/delete/:id") {
            @Override
            public Object handle(Request request, Response response) {
                Integer id = Integer.parseInt(request.params(":id"));
                songDbService.delete(id);
                response.status(200);
                response.redirect("/");
                return "";
            }
        });
    }
}