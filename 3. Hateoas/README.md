# hateoas

Не завершена документация - выдает ошибку, при встрече _links (пытаюсь фиксить - StackOverflow не помог :( )

```
org.springframework.restdocs.snippet.SnippetException: The following parts of the payload were not documented:
{
  "_links" : {
    "setNoTickets" : {
      "href" : "http://localhost:8080/attractions/25/update/2"
    },
    "setAsBroken" : {
      "href" : "http://localhost:8080/attractions/25/update/3"
    }
  }
}
```
