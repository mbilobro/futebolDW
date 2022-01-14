# Futebol - DW

Luis Guilherme Molotto - RA: 18016126 <br />
Matheus Bilobrovec - RA: 18011426

## Sobre o projeto
Trabalho realizado para a disciplina de Desenvolvimento Web da UEPG, que consiste em desenvolver um sistema simplista para gerenciamento de pagamentos de um futebol.

## Requisitos
- Java 11
- Apache Maven 3.8.4
- XAMPP
- MySql WorkBench 
- Visual Studio Code 
- Git

## Como criar o projeto Spring
Utilizando a plataforma (https://start.spring.io/), conseguimos realizar o download de um zip contendo a estrutura inicial do nosso projeto a ser desenvolvido, adicionando as seguintes dependências: <br />
- Spring Web
- Spring Data JPA
- MySQL Driver

## Configurações de SGBD
Utilizando como base a vídeo aula número 2 da playlist citada em "Materiais complementares" [Spring Data #2 - Configurando o projeto e o banco de dados] (https://www.youtube.com/watch?v=KPPoAONmP9w&list=PL3ZslI15yo2p5LMl-r7KtsVkC6hsucsJp&index=2),
conseguimos configurar a conexão entre o nosso projeto e o banco de dados, no documento "application.properties".

## Packages desenvolvidos

### Model
- Jogador.java <br />
- Pagamento.java <br />
Os modelos desenvolvidos contém as instâncias dos atributos, os getters e os setters para cada atributo, métodos construtores, método ToSring, além de utilizar notações do Spring para determinar regras do BD, como chaves primárias, chaves estrangeiras, se um atributo pode ser falso ou não

### Repository
- JogadorRepository.java <br />
- PagamentoRepository.java <br />
Nos repositórios criados extendemos o Repositório JPA que contém diversos métodos para utilizarmos no Controller e além disso é possível criar métodos novos.

### Controllers
- JogadorController.java <br />
- PagamentoController.java <br />
Nos controladores criados as regras de negócio do nosso sistema, podendo utilizar os métodos do Repositório criado.

### Funcionalidades
Os dois controllers possuem os mesmos métodos implementados: busca, inserção, edição, exclusão de um ou todos os registros. <br />

**OBS:** o controller de jogador tem implementado a busca por nome, além da busca de todos os registros do banco de dados.

## Testes
### Banco de dados
- Utilizamos o MySQL Workbench para visualizar de melhor forma o que estava acontecendo com o banco. <br />
- Quando foi configurada a conexão entre projeto e banco de dados do XAMPP, os modelos já estavam programados, quando a aplicação era iniciada as tabelas foram criadas de forma automática. <br />
- Foi configurado o relacionamento do Jogador OneToMany e do Pagamento ManyToOne, de forma com que exista uma chave estrangeira cod_jogador que ligue as duas classes.

### Rotas
- Para que as rotas fossem testadas, foi utilizada a Talend API Tester, passada em aula pelo professor Adriano em aula e todas as CINCO rotas de cada um dos Controllers foram testadas e funcionaram de forma correta.

## Materiais complementares
- [Como instalar e configurar Apache Maven - Loiane Groiner] (https://www.youtube.com/watch?v=jIa8R69pKh8) <br />
- [Playlist Spring Data - xavecoding - prof. samuka] (https://www.youtube.com/watch?v=YDTW9e_17e8&list=PL3ZslI15yo2p5LMl-r7KtsVkC6hsucsJp&index=1)
