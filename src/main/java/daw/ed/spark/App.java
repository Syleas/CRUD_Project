package daw.ed.spark;

import java.util.ArrayList;
import spark.Request;
import spark.Response;
import spark.Route;
import static spark.Spark.get;
import static spark.Spark.post;

public class App {
    private static ArrayList<Song> favoritos;
    public static void main(String[] args) {
        favoritos = new ArrayList();

        get(new Route("/") {
            @Override
            public Object handle(Request request, Response response) {
                return "<html>\n"
                        + "  <head><title>Musix</title></head>\n"
                        + "  <body>\n"
                        + "     <form action=\"http://localhost:4567/create\" method=\"GET\">\n"
                        + "        <fieldset>\n"
                        + "        <legend>Añadir un nuevo favorito</legend>\n"
                        + "        <input type=\"submit\" value=\"Nuevo Favorito\"/>\n"
                        + "        </fieldset>\n"
                        + "     </form>\n"
                        + "     <form action=\"http://localhost:4567/read\" method=\"GET\">\n"
                        + "        <fieldset>\n"
                        + "        <legend>Mostrar los favoritos</legend>\n"
                        + "        <input type=\"submit\" value=\"Mostrar Favoritos\"/>\n"
                        + "        </fieldset>\n"
                        + "     </form>\n"
                        + "     <form action=\"http://localhost:4567/update\" method=\"GET\">\n"
                        + "        <fieldset>\n"
                        + "        <legend>Actualizar un favorito</legend>\n"
                        + "        <input type=\"submit\" value=\"Actualizar Favoritos\"/>\n"
                        + "        </fieldset>\n"
                        + "     </form>\n"
                        + "     <form action=\"http://localhost:4567/delete\" method=\"GET\">\n"
                        + "        <fieldset>\n"
                        + "        <legend>Eliminar un favorito</legend>\n"
                        + "        <input type=\"submit\" value=\"Eliminar Favoritos\"/>\n"
                        + "        </fieldset>\n"
                        + "     </form>\n"
                        + "  </body>\n"
                        + "</html>";
            }
        });
        
        get(new Route("/create") {
            @Override
            public Object handle(Request request, Response response) {

                return "<html>\n"
                        + "  <head><title>Musix: Nuevo Favorito</title></head>\n"
                        + "  <body>\n"
                        + "     <form action=\"http://localhost:4567/create\" method=\"POST\">\n"
                        + "        <fieldset>\n"
                        + "        <legend>Información de la Canción:</legend>\n"
                        + "        <p>\n"
                        + "            <input type=\"text\" name=\"name\" placeholder=\"Nombre\"></input>\n"
                        + "        </p>    \n"
                        + "        <p>\n"
                        + "            <input type=\"text\" name=\"author\" placeholder=\"Autor\"></input>\n"
                        + "        </p>    \n"
                        + "        <p>\n"
                        + "            <input type=\"text\" name=\"length\" placeholder=\"Duración\"></input>\n"
                        + "        </p>    \n"
                        + "        <input type=\"submit\" value=\"Crear\"/>\n"
                        + "        </fieldset>\n"
                        + "     </form>\n"
                        + "     <form action=\"http://localhost:4567/redirect\" method=\"GET\">\n"
                        + "        <fieldset>\n"
                        + "        <legend>Volver al menú principal</legend>\n"
                        + "        <input type=\"submit\" value=\"Volver\"/>\n"
                        + "        </fieldset>\n"
                        + "     </form>\n"
                        + "  </body>\n"
                        + "</html>";
            }
        });
        
        post(new Route("/create"){
            @Override
            public Object handle(Request request, Response response){
                String length = request.queryParams("length");
                String author = request.queryParams("author");
                String name = request.queryParams("name");
                Song s = new Song(length, name, author);
                favoritos.add(s);
                return("<html>\n"
                        + "<head><title>Musix: Favorito Añadido</title></head>\n"
                        + "<body>\n"
                        + "<table border=\"1\"><th>Favorito Creado:</th><tr><td>Nombre: </td><td>" + name + "</td></tr><br/>\n"
                        + "<tr><td>Autor: </td><td>" + author + "</td></tr><br/>\n"
                        + "<tr><td>Duración: </td><td>" + length + "</td></tr></table><br/><br/>\n"
                        + "     <form action=\"http://localhost:4567/redirect\" method=\"GET\">\n"
                        + "        <fieldset>\n"
                        + "        <legend>Volver al menú principal</legend>\n"
                        + "        <input type=\"submit\" value=\"Volver\"/>\n"
                        + "        </fieldset>\n"
                        + "     </form>\n"
                        + "</body></html>");
            }
        });
        
        get(new Route("/redirect") {
            @Override
            public Object handle(Request request, Response response) {
                response.redirect("/");
                return response;
            }
        });
        
        get(new Route("/read") {
            @Override
            public Object handle(Request request, Response response) {
                String html = new String("<html><head><title>Musix: Listado de Favoritos</title></head><body>");
                for (int i = 0; i < favoritos.size(); i++){
                    html = html.concat("<table border=\"1\"><th>Favorito Número "+ (i + 1) +"</th>"
                            + "<tr><td>Nombre: </td><td>"+ favoritos.get(i).getName() +"</td></tr>"
                            + "<tr><td>Autor: </td><td>"+ favoritos.get(i).getAuthor() + "</td></tr>"
                            + "<tr><td>Duración: </td><td>"+ favoritos.get(i).getLength() + "</td></tr></table><br/><br/>");
                }
                html = html.concat(""
                        + "     <form action=\"http://localhost:4567/redirect\" method=\"GET\">\n"
                        + "        <fieldset>\n"
                        + "        <legend>Volver al menú principal</legend>\n"
                        + "        <input type=\"submit\" value=\"Volver\"/>\n"
                        + "        </fieldset>\n"
                        + "     </form>\n"
                        + "</body></html>");
                return html;
            }
        });
        
        get(new Route("/update") {
            @Override
            public Object handle(Request request, Response response) {
                String html = new String("<html><head><title>Musix: Listado de Favoritos</title></head><body>");
                for (int i = 0; i < favoritos.size(); i++){
                    html = html.concat("<table border=\"1\"><th>Favorito Número "+ (i + 1) +"</th>"
                            + "<tr><td>Nombre: </td><td>"+ favoritos.get(i).getName() +"</td></tr>"
                            + "<tr><td>Autor: </td><td>"+ favoritos.get(i).getAuthor() + "</td></tr>"
                            + "<tr><td>Duración: </td><td>"+ favoritos.get(i).getLength() + "</td></tr>"
                            + "<tr><td colspan=\"2\">"
                            + "     <form action=\"http://localhost:4567/updateSong\" method=\"POST\">\n"
                            + "        <fieldset>\n"
                            + "        <legend>Editar Favorito</legend>\n"
                            + "        <input type=\"hidden\" name=\"name\" value=\""+ favoritos.get(i).getName() +"\"/>"
                            + "        <input type=\"hidden\" name=\"author\" value=\""+ favoritos.get(i).getAuthor() +"\"/>"
                            + "        <input type=\"hidden\" name=\"length\" value=\""+ favoritos.get(i).getLength() +"\"/>"
                            + "        <input type=\"submit\" value=\"Editar\"/>\n"
                            + "        </fieldset>\n"
                            + "     </form>\n"
                            + "</td></tr></table><br>");
                }
                html = html.concat(""
                        + "     <form action=\"http://localhost:4567/redirect\" method=\"GET\">\n"
                        + "        <fieldset>\n"
                        + "        <legend>Volver al menú principal</legend>\n"
                        + "        <input type=\"submit\" value=\"Volver\"/>\n"
                        + "        </fieldset>\n"
                        + "     </form>\n"
                        + "</body></html>");
                return html;
            }
        });
        
        post(new Route("/updateSong") {
            @Override
            public Object handle(Request request, Response response) {
                String lengthP = request.queryParams("length");
                String authorP = request.queryParams("author");
                String nameP = request.queryParams("name");
                String html = new String("<html><head><title>Musix: Editar un Favorito</title></head><body>");
                return "<html>\n"
                        + "  <head><title>Musix: Editar Favorito</title></head>\n"
                        + "  <body>\n"
                        + "     <table border=\"1\"><th>Información del Favorito:</th><tr><td>Nombre: </td><td>" + nameP + "</td></tr><br/>\n"
                        + "     <tr><td>Autor: </td><td>" + authorP + "</td></tr><br/>\n"
                        + "     <tr><td>Duración: </td><td>" + lengthP + "</td></tr></table><br/><br/>\n"
                        + "     <form action=\"http://localhost:4567/update\" method=\"POST\">\n"
                        + "        <fieldset>\n"
                        + "        <legend>Nueva Información:</legend>\n"
                        + "        <p>\n"
                        + "            <input type=\"text\" name=\"name\" placeholder=\"Nombre\"></input>\n"
                        + "        </p>    \n"
                        + "        <p>\n"
                        + "            <input type=\"text\" name=\"author\" placeholder=\"Autor\"></input>\n"
                        + "        </p>    \n"
                        + "        <p>\n"
                        + "            <input type=\"text\" name=\"length\" placeholder=\"Duración\"></input>\n"
                        + "        </p>    \n"
                        + "        <input type=\"hidden\" name=\"nameP\" value=\""+ nameP +"\"/>"
                        + "        <input type=\"hidden\" name=\"authorP\" value=\""+ authorP +"\"/>"
                        + "        <input type=\"hidden\" name=\"lengthP\" value=\""+ lengthP +"\"/>"
                        + "        <input type=\"submit\" value=\"Crear\"/>\n"
                        + "        </fieldset>\n"
                        + "     </form>\n"
                        + "     <form action=\"http://localhost:4567/redirect\" method=\"GET\">\n"
                        + "        <fieldset>\n"
                        + "        <legend>Volver al menú principal</legend>\n"
                        + "        <input type=\"submit\" value=\"Volver\"/>\n"
                        + "        </fieldset>\n"
                        + "     </form>\n"
                        + "  </body>\n"
                        + "</html>";
            }
        });
        
        post(new Route("/update"){
            @Override
            public Object handle(Request request, Response response){
                String length = request.queryParams("length");
                String author = request.queryParams("author");
                String name = request.queryParams("name");
                String lengthP = request.queryParams("lengthP");
                String authorP = request.queryParams("authorP");
                String nameP = request.queryParams("nameP");
                for (int i = 0; i < favoritos.size(); i++){
                    if(favoritos.get(i).getAuthor().equals(authorP) && favoritos.get(i).getName().equals(nameP) && favoritos.get(i).getLength().equals(lengthP)){
                        favoritos.get(i).setAuthor(author);
                        favoritos.get(i).setLength(length);
                        favoritos.get(i).setName(name);
                    }
                }
                return("<html>\n"
                        + "<head><title>Musix: Favorito Editado</title></head>\n"
                        + "<body>\n"
                        + "<table border=\"1\"><tr><td>Nombre: </td><td>" + name + "</td></tr><br/>\n"
                        + "<tr><td>Autor: </td><td>" + author + "</td></tr><br/>\n"
                        + "<tr><td>Duración: </td><td>" + length + "</td></tr></table><br/><br/>\n"
                        + "     <form action=\"http://localhost:4567/redirect\" method=\"GET\">\n"
                        + "        <fieldset>\n"
                        + "        <legend>Volver al menú principal</legend>\n"
                        + "        <input type=\"submit\" value=\"Volver\"/>\n"
                        + "        </fieldset>\n"
                        + "     </form>\n"
                        + "</body></html>");
            }
        });
        
        get(new Route("/delete") {
            @Override
            public Object handle(Request request, Response response) {
                String html = new String("<html><head><title>Musix: Listado de Favoritos</title></head><body>");
                for (int i = 0; i < favoritos.size(); i++){
                    html = html.concat("<table border=\"1\"><th>Favorito Número "+ (i + 1) +"</th>"
                            + "<tr><td>Nombre: </td><td>"+ favoritos.get(i).getName() +"</td></tr>"
                            + "<tr><td>Autor: </td><td>"+ favoritos.get(i).getAuthor() + "</td></tr>"
                            + "<tr><td>Duración: </td><td>"+ favoritos.get(i).getLength() + "</td></tr>"
                            + "<tr><td colspan=\"2\">"
                            + "     <form action=\"http://localhost:4567/delete\" method=\"POST\">\n"
                            + "        <fieldset>\n"
                            + "        <legend>Eliminar Favorito</legend>\n"
                            + "        <input type=\"hidden\" name=\"name\" value=\""+ favoritos.get(i).getName() +"\"/>"
                            + "        <input type=\"hidden\" name=\"author\" value=\""+ favoritos.get(i).getAuthor() +"\"/>"
                            + "        <input type=\"hidden\" name=\"length\" value=\""+ favoritos.get(i).getLength() +"\"/>"
                            + "        <input type=\"submit\" value=\"Eliminar\"/>\n"
                            + "        </fieldset>\n"
                            + "     </form>\n"
                            + "</td></tr></table><br/>");
                }
                html = html.concat(""
                        + "     <form action=\"http://localhost:4567/redirect\" method=\"GET\">\n"
                        + "        <fieldset>\n"
                        + "        <legend>Volver al menú principal</legend>\n"
                        + "        <input type=\"submit\" value=\"Volver\"/>\n"
                        + "        </fieldset>\n"
                        + "     </form>\n"
                        + "</body></html>");
                return html;
            }
        });
        
        post(new Route("/delete"){
            @Override
            public Object handle(Request request, Response response){
                String lengthP = request.queryParams("length");
                String authorP = request.queryParams("author");
                String nameP = request.queryParams("name");
                for (int i = 0; i < favoritos.size(); i++){
                    if(favoritos.get(i).getAuthor().equals(authorP) && favoritos.get(i).getName().equals(nameP) && favoritos.get(i).getLength().equals(lengthP)){
                        favoritos.remove(i);
                        break;
                    }
                }
                return("<html>\n"
                        + "<head><title>Musix: Favorito Eliminado</title></head>\n"
                        + "<body>\n"
                        + "     <h2>Favorito Eliminado.</h1><br/><br/>"
                        + "     <form action=\"http://localhost:4567/redirect\" method=\"GET\">\n"
                        + "        <fieldset>\n"
                        + "        <legend>Volver al menú principal</legend>\n"
                        + "        <input type=\"submit\" value=\"Volver\"/>\n"
                        + "        </fieldset>\n"
                        + "     </form>\n"
                        + "</body></html>");
            }
        });

        /*
        get(new Route("/hello") {
            @Override
            public Object handle(Request request, Response response) {
                return "<h1>Hello Spark MVC Framework!</h1>";
            }
        });
        
        get(new Route("/hello/:name") {
            @Override
            public Object handle(Request request, Response response) {
                return "<h1>Hello " + request.params(":name") + " </h1>";
            }
        });

        get(new Route("/request/info") {
            @Override
            public Object handle(Request request, Response response) {
                return ("<h1>Información de la petición:</h1>"
                        + "<ul><li>IP del cliente: " + request.ip() + " </li>"
                        + "<li> Request method: " + request.requestMethod() + " </li>"
                        + "<li> Url solicitada: " + request.url() + " </li>"
                        + "<li> User Agent: " + request.userAgent() + "</li>"
                        + "<li> Host: " + request.host() + "</li>"
                        + "<li> Content type: " + request.contentType() + "</li>"
                        + "<li> Content length: " + request.contentLength() + "</li>"
                        + "<li> Headers: " + request.headers(null) + "</li></ul>");

            }
        });

        get(new Route("/protected") {
            @Override
            public Object handle(Request request, Response response) {
                halt(401, "Acceso no autorizado");

                return null;
            }
        });

        get(new Route("/favorite_fruit") {
            @Override
            public Object handle(Request request, Response response) {
                return request.queryParams("fruit");

            }
        });
        get(new Route("/favorite_fruit") {
            @Override
            public Object handle(Request request, Response response) {

                return "<html>\n"
                        + "  <head><title>Fruit Picker</title></head>\n"
                        + "  <body>\n"
                        + "     <form action=\"http://localhost:4567/favorite_fruit\" method=\"POST\">\n"
                        + "        <p>What is your favorite fruit?</p>\n"
                        + "          <p>\n"
                        + "             <input type=\"radio\" name=\"fruit\" value=\"Peras\">Peras</input>\n"
                        + "          </p>    \n"
                        + "          <p>\n"
                        + "             <input type=\"radio\" name=\"fruit\" value=\"Manzanas\">Manzanas</input>\n"
                        + "          </p>    \n"
                        + "          <p>\n"
                        + "             <input type=\"radio\" name=\"fruit\" value=\"Naranjas\">Naranjas</input>\n"
                        + "          </p>    \n"
                        + "          <p>\n"
                        + "             <input type=\"radio\" name=\"fruit\" value=\"Naranjas\">Naranjas</input>\n"
                        + "          </p>            \n"
                        + "        <input type=\"submit\" value=\"Submit\"/>\n"
                        + "     </form>\n"
                        + "  </body>\n"
                        + "</html>";
            }
        });
        
        
        get(new FreeMarkerRoute("/freemarker/hello") {
            @Override
            public ModelAndView handle(Request request, Response response) {
                response.redirect("/");
                return modelAndView(null, "hello.ftl");
            }
        });
        
        get(new FreeMarkerRoute("/freemarker/hello2") {
            @Override
            public ModelAndView handle(Request request, Response response) {
                Map<String,Object> data = new HashMap<>();
                data.put("name","Fulajnjna");
                data.put("numero",1);
                data.put("colores", Arrays.asList("Rojo", "Azul", "Verde", "Amarillo"));
                return modelAndView(data, "hello2.ftl");
            }
        });
        
        get(new FreeMarkerRoute("/freemarker/base") {
            @Override
            public ModelAndView handle(Request request, Response response) {
                //response.redirect("/");
                //Map<String,Object> data = new HashMap<>();
                //data.put("name","dedede");
                return modelAndView(null, "base.ftl");
            }
        });
        
        get(new FreeMarkerRoute("/freemarker/content") {
            @Override
            public ModelAndView handle(Request request, Response response) {
                //response.redirect("/");
                Map<String,Object> data = new HashMap<>();
                data.put("name","dedede");
                return modelAndView(data, "content.ftl");
            }
        });*/
    }
}
