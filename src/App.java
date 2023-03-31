import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
      
      /*
       * Melhorar estrurura e ocultar chave de acesso
       */
      String url = ("https://imdb-api.com/en/API/Top250Movies/{chaveDeAcesso}");
      URI endereco = URI.create(url);
      var client = HttpClient.newHttpClient();
      HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
      HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
      String body = response.body();

      var parser = new JsonParse();
      List<Map<String, String>> listaDeFilmes = parser.parse(body);

      System.out.println(listaDeFilmes.size());
      System.out.println(listaDeFilmes.get(0));

      for (Map<String,String> filme : listaDeFilmes) {
        System.out.println(filme.get("fullTitle"));
        System.out.println(filme.get("image"));
        System.out.println(filme.get("imDbRating"));
        System.out.println();
      }
    }
}