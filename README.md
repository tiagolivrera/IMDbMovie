# IMDbMovie
Projeto baseado no desafio ["7 days of code"](https://7daysofcode.io/) da Alura. Na trilha backend java, é proposto a criação de um programa que consome a API do IMDB e retorna os dados para o cliente.

## Dia 1
Use a API do IMDB para retornar os top 250 filmes e imprimir o JSON correspondente no console.

### Passos:
- [x] Crie uma conta no site da [API IMDB](https://imdb-api.com/)
- [x] Obter uma API KEY
- [ ] Usando a API KEY, obter os dados dos top 250 filmes através do endpoint específico.

### Problema:
Desde o dia 1 de julho de 2023, a API KEY obtida no site da [API IMDB](https://imdb-api.com/) de forma gratuita não tem mais o acesso a API. Para isso, é necessário a assinatura de um dos planos disponíveis.

### Solução proposta:
- [x] Criação de uma conta no site [Rapid API](https://rapidapi.com/hub).
- [x] Buscar uma API que disponibilize dados sobre filmes. ex. [MoviesDatabase](https://rapidapi.com/SAdrian/api/moviesdatabase/).
- [x] Escolha de um endpoint apropriado. No meu caso, optei por pegar os dados dos filmes mais aguardados (upcoming).
- [x] Fazer a captura dos dados usando alguma biblioteca específica. Por exemplo, usando java.net:
```java
HttpRequest request = HttpRequest.newBuilder()
		.uri(URI.create("https://moviesdatabase.p.rapidapi.com/titles/x/upcoming"))
		.header("X-RapidAPI-Key", "<API_KEY>")
		.header("X-RapidAPI-Host", "moviesdatabase.p.rapidapi.com")
		.method("GET", HttpRequest.BodyPublishers.noBody())
		.build();
HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
System.out.println(response.body());
```
- [x] Exibir os dados no console. Exemplo:
```json
{
	"page": 1,
	"next": "/titles/x/upcoming?page=2",
	"entries": 10,
	"results": [
		{
			"_id": "61e58c6aef99b1c54343e375",
			"id": "tt10209920",
			"primaryImage": {
				"id": "rm882431489",
				"width": 8200,
				"height": 12300,
				"url": "https://m.media-amazon.com/images/M/MV5BNjc5ZGQwNjMtNGI3NC00Yzg3LTk4MjktMDgwYTcyZDhhNmRmXkEyXkFqcGdeQXVyMjE2MzA5MDI@._V1_.jpg",
				"caption": {
					"plainText": "Alien Wars: Judgement Day (2024)",
					"__typename": "Markdown"
				},
				"__typename": "Image"
			},
			"titleType": {
				"displayableProperty": {
					"value": {
						"plainText": "",
						"__typename": "Markdown"
					},
					"__typename": "DisplayableTitleTypeProperty"
				},
				"text": "Movie",
				"id": "movie",
				"isSeries": false,
				"isEpisode": false,
				"categories": [
					{
						"value": "movie",
						"__typename": "TitleTypeCategory"
					}
				],
				"canHaveEpisodes": false,
				"__typename": "TitleType"
			},
			"titleText": {
				"text": "Alien Wars: Judgement Day",
				"__typename": "TitleText"
			},
			"originalTitleText": {
				"text": "Alien Wars: Judgement Day",
				"__typename": "TitleText"
			},
			"releaseYear": {
				"year": 2024,
				"endYear": null,
				"__typename": "YearRange"
			},
			"releaseDate": {
				"day": 29,
				"month": 12,
				"year": 2024,
				"__typename": "ReleaseDate"
			}
		}
}
```
## Dia 2
Extrair informações a partir da resposta JSON.

Dado o resultado que obtive da API, escolhi capturar os dados referentes ao título, url e data de lançamento. Existe várias formas de fazer esse tratamento, como o uso de bibliotecas que possibilitam o parsing de JSON, como o Gson ou o Jackson, mas para exercitar os fundamentos, escolhi utilizar os métodos da classe String, como indexOf e substring.

### Problema
Durante a extração dos dados, percebi que nem todos os filmes eram acompanhados por uma url correspondente, o que me obrigou a fazer uma validação simples. Exemplo:

```java
if (!entry.contains("\"url\":"))
	url = null;
else {
	int urlStartIndex = entry.indexOf("\"url\":") + "\"url\":\"".length();
	int urlEndIndex = entry.indexOf("\",", urlStartIndex);
	url = entry.substring(urlStartIndex, urlEndIndex);
}
```
Para auxiliar a captura de informações, criei uma classe POJO Movie, e depois do parse dos dados crio um objeto dessa classe e armazeno em uma lista, a ser impressa no console.

```text
Movie [title=Alien Wars: Judgement Day, url=https://m.media-amazon.com/images/M/MV5BNjc5ZGQwNjMtNGI3NC00Yzg3LTk4MjktMDgwYTcyZDhhNmRmXkEyXkFqcGdeQXVyMjE2MzA5MDI@._V1_.jpg, releaseDate=2024-12-29]
Movie [title=Supa Team 4, url=https://m.media-amazon.com/images/M/MV5BYWVhZDkyMTEtODFhYy00ZGMyLWEyY2UtYTZlYjY4MTE2ODlmXkEyXkFqcGdeQXVyMTEzMTI1Mjk3._V1_.jpg, releaseDate=2023-07-20]
Movie [title=El Principe Rojo, url=https://m.media-amazon.com/images/M/MV5BNzU0NWUxZjItNmZjNi00MmExLWEyZTYtYjc4MWY0NWNmODE0XkEyXkFqcGdeQXVyOTgzOTE0MDg@._V1_.jpg, releaseDate=2023-09-17]
Movie [title=Star Wars: New Jedi Order, url=null, releaseDate=2027-12-17]
Movie [title=Creepers 'R Us, url=null, releaseDate=2023-09-22]
Movie [title=Pizzodyssey, url=https://m.media-amazon.com/images/M/MV5BNDY1ZDU2Y2QtZGQ2Mi00YTIwLTg2NjItOWJlNzMzNjc1ZGU2XkEyXkFqcGdeQXVyMTAxNTI3MTI1._V1_.jpg, releaseDate=2023-07-15]
Movie [title=Finding Tess, url=null, releaseDate=2023-11-15]
Movie [title=The Divine Comedy: Inferno, Purgatory and Paradise, url=https://m.media-amazon.com/images/M/MV5BZWIyNzE3NzEtMGExNS00ZjRkLWJmMTYtMWFlNTNkNDgyNWUzXkEyXkFqcGdeQXVyODUwMzI5ODk@._V1_.jpg, releaseDate=2023-09-21]
Movie [title=The Hunger Games: The Ballad of Songbirds and Snakes, url=https://m.media-amazon.com/images/M/MV5BMGE1NWZkZGMtMjEzYi00NDUwLTgzNmYtMGY4ZDhjNWMxZjVhXkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_.jpg, releaseDate=2023-11-17]
Movie [title=Mom's Coming, url=https://m.media-amazon.com/images/M/MV5BNDFmYWRhYmMtMDBiNS00OWZkLTk3ZmYtODMyZjdlMDFjNWNlXkEyXkFqcGdeQXVyNjA1NjIzMzQ@._V1_.jpg, releaseDate=2024-11-24]

```

## Dia 3
Modelando uma classe Movie.

Com os dados oriundos da API, destaquei três dados interessantes: título, url da capa e data de lançamento. Com essas informações, podemos montar uma classe Movie com três propriedades, seguindo o paradigma Orientado a Objetos. Uma classe Movie poderia ser modelada na linguagem Java da seguinte forma:

```java
public class Movie {

	private String title;
	private String url;
	private LocalDate releaseDate;

	public Movie() {
	}

	public Movie(String title, String url, Integer day, Integer month, Integer year) {
		this.title = title;
		this.url = url;
		this.releaseDate = LocalDate.of(year, month, day);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	@Override
	public String toString() {
		return "Movie [title=" + title + ", url=" + url + ", releaseDate=" + releaseDate + "]";
	}

}
```
Em uma classe java padrão, geralmente temos propriedades ou atributos, um construtor padrão e métodos de controle e acesso do tipo get e set. No entanto, podemos perceber que a classe Movie não precisaria de um construtor padrão, dado que o instanciamos como meio para receber os dados. Da mesma forma, não precisamos de métodos set, pois uma vez criado o objeto, ele não será alterado. Isso possibilita que ao invés de utilizarmos uma classe comum, podemos criar um Record, um tipo de classe especial da linguagem Java onde todos os atributos derivados são finais e já implementa automaticamente métodos de leitura das propriedades. Então, reimplementando a classe Movie, temos uma estrutura da seguinte forma:

```java
public record MovieRecord(String title, String url, LocalDate releaseDate) {
}
```

## Dia 4
Com a saída, gere uma página HTML a partir da lista de objetos que você já tem no seu código Java.

Com os dados do dia anterior, é possível apresentá-los de forma visual usando uma página HTML. Hoje em dia há várias formas de fazer essa visualização, seja no projeto java usando JSF, em um projeto Spring Boot com Tymeleaf ou usando uma tecnologia a parte para o frontend, como React ou Angular. No entanto, optei por fazer um documento simples usando Writer da seguinte forma:

```java
public static void generate(List<MovieRecord> listMovies) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("movies.html"))) {
			writer.write("<!DOCTYPE html>");
			writer.newLine();
			writer.write("<html>");
			writer.newLine();
			writer.write("<head>");
			writer.newLine();
			writer.write("<title>Lista de Filmes</title>");
			writer.newLine();
			writer.write("</head>");
			writer.newLine();
			writer.write("<body>");
			writer.newLine();
			writer.write("<h1>Lista de Filmes</h1>");
			writer.newLine();
			writer.write("<table>");
			writer.newLine();
			writer.write("<tr>");
			writer.newLine();
			writer.write("<th>Título</th>");
			writer.newLine();
			writer.write("<th>Capa</th>");
			writer.newLine();
			writer.write("<th>Data de Lançamento</th>");
			writer.newLine();
			writer.write("</tr>");
			writer.newLine();
			for (MovieRecord movie : listMovies) {
				writer.write("<tr>");
				writer.newLine();
				writer.write("<td>" + movie.title() + "<td>");
				writer.newLine();
				if (movie.url() != null) {
					writer.write("<td><img src=\"" + movie.url() + "\" alt=\"Capa do Filme\" width=\"500\" height=\"600\"></td>");
					writer.newLine();
				}
				writer.write("<td>" + " Lançamento: " + movie.releaseDate().toString() + "</td>");
				writer.newLine();
				writer.write("</tr>");
				writer.newLine();
			}
			writer.write("</table>");
			writer.newLine();
			writer.write("</body>");
			writer.newLine();
			writer.write("</html>");
			writer.newLine();

			System.out.println("Arquivo HTML gerado com sucesso!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
```
Dessa forma, o resultado final é uma página HTML estática com os dados dos filmes, referentes ao título, imagem da capa e a data de lançamento, que foram obtidos ao consumir a API.

## Dia 5
Encapsular a chamada da API dentro de uma nova classe.

Em grandes projetos, é comum fazer uma estrutura em camadas, onde recebemos e enviamos apenas as informações necessárias para o cliente. Por exemplo, nessa aplicação não faz sentido mostrar ao usuário como a página HTML é construída ou em como os dados estão sendo capturados, basta apenas receber a chave de acesso e obter a página HTML correspondente. Por isso, o método main foi alterado para funcionar da seguinte forma:

```java
public class App {
	public static void main(String[] args) throws IOException, InterruptedException {
		MovieController controller = new MovieController("<API_KEY>");
		HTMLGenerator.generate(controller.getMovies());
	}
}
```
Dessa forma, ao encapsular o código do projeto, fica mais simples o entendimento, mais fácil de dar manutenção e fornecendo apenas o necesário do funcionamento da aplicação para o cliente.




