import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

import service.GeradorDeFigurinhas;
import service.JsonParse;

public class App {
    public static void main(String[] args) throws Exception {
      
      /*
       * Melhorar estrurura e ocultar chave de acesso
       */
      String url = ("https://imdb-api.com/en/API/Top250Movies/{chave}");
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
        String urlImagem = filme.get("image");
        String titulo = filme.get("title");

        InputStream inputStream = new URL(urlImagem).openStream();
        String nomeDoArquivo = titulo +".png";

        var gerador = new GeradorDeFigurinhas();
        gerador.cria(inputStream, nomeDoArquivo, titulo);
      
      }
    }
}