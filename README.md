<h1 align="center">Backend Case MELI</h1>

<div align="center" id="top" style="margin-bottom: 100px;"> 
  <a href="https://mateus-case-meli-back-end.herokuapp.com">Demo</a>
</div>


<p align="center">
  <a href="#dart-sobre">Sobre</a> &#xa0; | &#xa0; 
  <a href="#sparkles-features">Features</a> &#xa0; | &#xa0;
  <a href="#rocket-tecnologias">Tecnologias</a> &#xa0; | &#xa0;
  <a href="#memo-autor">Autores</a>
</p>

<br>

## :dart: Sobre ##

Em um futuro distante, na cadeia de evolução, os símios e os humanos estão
cada vez mais próximos. Por esse motivo ficou muito difícil distinguir quem é
humano e quem é símio.
Você é um cientista contratado para desenvolver um projeto que detecta se
uma sequência de DNA pertence a um humano ou a um símio.

## :sparkles: Features ##

:heavy_check_mark: API para verificar DNA;\

Api cadastra DNAs validos clasificando seu tipo

POST → https://mateus-case-meli-back-end.herokuapp.com/simian

{

"dna": ["ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"]

}

:heavy_check_mark: API de consultar metricas;\
GET → https://mateus-case-meli-back-end.herokuapp.com/stats

trazendo um retorno como o exemplo a baixo:

{
    "count_human_dna": 5.0,
    "count_mutant_dna": 5.0,
    "ratio": 1.0 
}


## :rocket: Tecnologias ##

Tecnologias ultilizadas:

- [Java](https://www.java.com/pt-BR/)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Data](https://spring.io/projects/spring-data)
- [Heroku](https://heroku.com/)
- [PostgreSQL](https://www.postgresql.org/)

## :memo: Autor ##

Projeto realizado por:
 <p><a href="https://github.com/mateusbrazza" target="_blank">Mateus Santana</a></p>
&#xa0;

<a href="#top">Voltar ao início</a>
