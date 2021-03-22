# Sistema de cadastro de planetas

Link para documentação da API:
	[Documentacao da API](http://localhost:8080/q/swagger-ui/)

Execução:
- Para realizar a execução do projeto que esta na versão *Java 11*, execute no raiz do projeto:
```
docker-compose up
./mvnw quarkus:dev
``` 

Para realizar a execução do projeto em modo nativo do Quarkus, primeiramente a maquina necessita do GraalVM 21.0.0.2

Instale o [jEnv](https://www.jenv.be/) (OPCIONAL) para agilizar no processo.

Execute as etapas dentro do diretorio raiz do projeto:
```
	- jenv add "diretorio do graalVM"
	- jenv local 11.0.10
	- gu install native-image
	- ./mvnw package -Pnative -Dquarkus.native.container-build=true -Dquarkus.container-image.build=true
	cd target/
	./b2w-1.0.0-SNAPSHOT-runner
```

## Endpoint para cadastrar um planeta
Request:

	POST http://localhost:8080/v1/planet
	
Body:

```json 
{
    "planetName": "Tatooine",
    "climate": "arid",
    "terrain": "desert"
}
```
Response: 201 (Created) Com id do recurso criado no atributo Location do header da resposta:

	ex: Location → http://localhost:8080/v1/planet/8e25d9fe-b0ba-4105-a2be-687f715acf1c


## Endpoint para listar todos planetas cadastrados

Request:

	GET http://localhost:8080/v1/planet/all
	
Response: 200 (Ok)
	
```json
[
    {
        "planetId": "c8251072-280a-4ccc-98cd-b0cb9817641c",
        "name": "Dagobah",
        "climate": "arid",
        "terrain": "desert",
        "amountOfFilmAppearances": 3
    },
    {
        "planetId": "f0b65246-56c6-4e7b-8ecd-39a98303a4aa",
        "name": "Hoth",
        "climate": "arid",
        "terrain": "desert",
        "amountOfFilmAppearances": 1
    },
    {
        "planetId": "ca3bf641-6adb-461c-97e2-5d455db6cbbd",
        "name": "Naboo",
        "climate": "arid",
        "terrain": "desert",
        "amountOfFilmAppearances": 4
    }
]
```
	

## Endpoint para buscar planeta por nome

Request:

	GET http://localhost:8080/v1/planet/{name}
	
Response: 200 (Ok)
	
```json
{
    "planetId": "ca3bf641-6adb-461c-97e2-5d455db6cbbd",
    "name": "Naboo",
    "climate": "arid",
    "terrain": "desert",
    "amountOfFilmAppearances": 4
}
```
## Endpoint para buscar planeta por ID

Request:

	GET http://localhost:8080/v1/planet/test?id={planetId}
	
Response: 200 (Ok)
	
```json
{
    "planetId": "ca3bf641-6adb-461c-97e2-5d455db6cbbd",
    "name": "Naboo",
    "climate": "arid",
    "terrain": "desert",
    "amountOfFilmAppearances": 4
}
```

## Endpoint para remover um planeta

Request:

	DELETE http://localhost:8080/v1/planet/{planetId}
	
Response: 204 (No Content)
	

