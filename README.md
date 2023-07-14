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
