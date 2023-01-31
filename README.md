# API REST de Gerenciamento de Pessoas

API Rest em Spring boot de gerenciamento de pessoas. Trata-se de um teste para uma vaga de desenvolvedor Back-end na empresa Attornatus.

## Tecnologias Utilizadas
- Spring Web
- JPA (Java Persistence API)
- Spring Validation
- Lombok
- JUnit 
- Mockito
- Model Mapper
- Banco de dados H2

## Princípios Seguidos
- Respeito aos princípios do SOLID 
- Implementação de Clean Code 

Os testes unitários foram feitos utilizando JUnit e Mockito para garantir a qualidade do código e a sua funcionalidade. Também buscou-se aplicar os princípios do SOLID e Clean Code ao longo do desenvolvimento da API, garantindo assim uma boa estruturação e manutenção do código. Todas as respostas da API estão em JSON, e foi realizado o tratamento de exceções.

## Endpoints e exemplos de requisições:


### POST /person
Descrição: Cria uma nova pessoa

#### Parâmetros: 
Body: 
```json
{
"name" : "Rafael Dias Simeoni",
"birthDate" : "1997-02-28"
}
```
#### Resposta:
```json
201 CREATED
{
"id": "d661654b-889e-4ca1-8bb4-d0461735f446",
"name": "Rafael Dias Simeoni",
"birthDate": "1997-02-28"
 }
```
------------------
### GET /person/{personId}
Descrição: Busca uma pessoa pelo id

#### Parâmetros: 
```json
URL: /person/d661654b-889e-4ca1-8bb4-d0461735f446
```
#### Resposta:
```json
200 OK 
{
"id": "d661654b-889e-4ca1-8bb4-d0461735f446",
"name": "Rafael Dias Simeoni", 
"birthDate": "1997-02-28"
}
```
------------------
### GET /person
Descrição: Lista todas as pessoas

#### Parâmetros: 
```json
Sem parâmetros
```
#### Resposta:
```json
200 OK
[
{"id": "d661654b-889e-4ca1-8bb4-d0461735f446", 
"name": "Rafael Dias Simeoni", 
"birthDate": "1997-02-28"}
]
```
------------------
### PUT /person/{personId}
Descrição: Edita as informações da pessoa informada

#### Parâmetros: 
```json
URL: /person/d661654b-889e-4ca1-8bb4-d0461735f446
```
Body:
```json
{
"name" : "Rafael atualizado", 
"birthDate" : "1995-02-28"
}
```
#### Resposta:
```json
200 OK
{
"id": "d661654b-889e-4ca1-8bb4-d0461735f446", 
"name": "Rafael atualizado", 
"birthDate": "1995-02-28"
}
```
------------------
### POST /person/address/{personId}
Descrição: Adiciona um novo endereço para a pessoa informada

#### Parâmetros: 
```json
URL: /person/address/d661654b-889e-4ca1-8bb4-d0461735f446
```
Body: 
```json
{
"publicPlace" : "Rua das Flores", 
"zipCode" : "81015-036", 
"number" : 123, 
"city" : "Curitiba"
}
```
#### Resposta:
```json
201 CREATED 
{
"id": "86b88cf8-defa-48b1-8ee5-2fd030a8f0ad", 
"publicPlace": "Rua das Flores", 
"zipCode": "81015-036", 
"number": 123, 
"city": "Curitiba", 
"isMainAddress": true
}
```
------------------
### GET /person/address/{personId}
Descrição: Lista os endereços da pessoa informada

#### Parâmetros: 
```json
URL /person/address/d661654b-889e-4ca1-8bb4-d0461735f446
```
#### Resposta:
```json
200 OK
{
"id": "d661654b-889e-4ca1-8bb4-d0461735f446", 
"name": "Rafael atualizado", 
"addressList": [ 
{"id": "86b88cf8-defa-48b1-8ee5-2fd030a8f0ad", 
"publicPlace": "Rua das Flores", 
"zipCode": "81015-036", 
"number": 123, "city": "Curitiba", "isMainAddress": true} 
]}
```
------------------
### PUT /person/address/{personId}/{addressId}
Descrição: Modifica o endereço principal da pessoa informada

#### Parâmetros: 
```json
URL /person/address/d661654b-889e-4ca1-8bb4-d0461735f446/86b88cf8-defa-48b1-8ee5-2fd030a8f0ad
```
#### Resposta
```json
200 OK
{
"id": "86b88cf8-defa-48b1-8ee5-2fd030a8f0ad", 
"publicPlace": "Rua das Flores", 
"zipCode": "81015-036", 
"number": 123, 
"city": "Curitiba", 
"isMainAddress": true
}
```

