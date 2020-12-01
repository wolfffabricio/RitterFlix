# Aplicativo RitterFlix

# Relatório de entrega A1 - 02/12/2020

###### Grupo:

- [Fabrício Wolff](https://www.linkedin.com/in/fabricio-wolff-b621251b1)
- [Filipe Drago](https://www.linkedin.com/in/filipe-drago-0848856a/)
- [Lucas Rambo](https://www.linkedin.com/in/lucas-rambo89/)
- [Márcio Alves](http://linkedin.com/in/m%C3%A1rcio-moreira-alves-357168149/)
- [Rafael Panzenhagen](https://www.linkedin.com/in/rafael-panzenhagen/)

### Índice:

- [1. Inovações e implementações pós 04/11/2020](#item1)
- [2. Funcionalidades do do Aplicativo](#item2)

  - [2.1 Resumo das funcionalidades](#item21)

- [2. Detalhamento do Aplicativo](#item2)
  - [2.1 Tecnologias utilizadas na aplicação](#item21)
  - [2.2 Arquitetura MVVM](#item22)
  - [2.3 Conexão com a API](#item23)
  - [2.4 Componentes](#item24)
  - [2.5 Banco de Dados](#item25)
- [3. Telas do Aplicativo](#item3)
  - [3.1 Telas já desenvolvidas](#item31)
  - [3.2 Telas a serem desenvolvidas](#item32)
- [4. Dificuldades encontradas](#item4)
  - [4.1 Dificuldades individuais](#item41)
- [5. Tarefas para 11/11](#item5)
- [6. Tarefas sem data prevista](#item6)

---

## <a name="item1"></a>1. Inovações e implementações pós 04/11/2020

---

## <a name="item2"></a>2. Funcionalidades do aplicativo:

### <a name="item21"></a>2.1 Resumo das funcionalidades:

- [ ] Login no aplicativo
- [x] Criação de conta
- [x] Listagem de catálogo de filmes
- [x] Acesso a detalhes de filmes
- [ ] Salvar categorias favoritas
- [ ] Salvar filmes favoritos
- [ ] Compartilhamento externo de links

**Atenção:** As funcionalidades e componentes, descritos ou desenvolvidos até 04/11/2020, já se encontram no [Relatório 1](/Relatorio1.md). Estes itens serão apresentados apenas de forma resumida aqui.

---

## <a name="item3"></a>3. Detalhamento do aplicativo:

### <a name="item21"></a>3.1 Tecnologias utilizadas na aplicação

###### As seguintes tecnologias serão utilizadas no desenvolvimento da aplicação:

- [IDE Android Studio](https://developer.android.com/studio)
- [Linguagem Kotlin](https://kotlinlang.org/)
- [Plataforma FireBase](https://firebase.google.com/)
- [The Movie DB API](https://www.themoviedb.org/)
- [Banco de dados MySQL](https://www.mysql.com/)

### <a name="item22"></a>3.2 Arquitetura MVVM

A arquitetura utilizada no projeto é a _Model-View-ViewModel_, arquitetura atualmente indicada pela Google no desenvolvimento de aplicativos _Android_.  
 Nesse modelo de arquitetura, os componentes _View_ são responsáveis pela estrutura, layout e aparência do que será exibido na tela. Os componentes _ViewModel_ são responsáveis pelo tratamento dos dados a serem exibidos na camada _View_, conforme regras de negócio e validações estabelecidas nos componentes da camada _Model_.

### <a name="item23"></a>3.3 Conexão com a API

A conexão com a API _The Movie DB_ é realizada através de três componentes que se encontram no repositório [http](src/main/java/com/dispositivosmoveis/ritterflix/repository/http).
A classe _ApiClient_ atua como cliente da API e é implementa os componentes que viabilizam o consumo da API.  
 A interface _ApiService_ é implementada em um objeto dentro da classe [Repository](src/main/java/com/dispositivosmoveis/ritterflix/repository/Repository.kt). Dessa forma, a classe _Repository_ é chamada na inicialização da activity [Home](src/main/java/com/dispositivosmoveis/ritterflix/ui/home/HomeActivity.kt) e popula a tela com os dados fornecidos pela API.  
 O componente [HttpUtils](src/main/java/com/dispositivosmoveis/ritterflix/repository/http/HttpUtils.kt) apresenta os dados utilizados na validação de acesso à API.

### <a name="item24"></a>3.4 Componentes

Até o momento, as seguintes _Activities_ foram desenvolvidas no aplicativo:

- [SplashScreen Activity](/app/src/main/java/com/dispositivosmoveis/ritterflix/ui/splash/SplashScreenActivity.kt)
- [Login Activity](/app/src/main/java/com/dispositivosmoveis/ritterflix/ui/login/LoginActivity.kt)
- [HomeActivity](/app/src/main/java/com/dispositivosmoveis/ritterflix/ui/home/HomeActivity.kt)

  Além disso, os seguintes _Fragments_ são inicializados através das _activities_:

- [Login Fragment](/app/src/main/java/com/dispositivosmoveis/ritterflix/ui/login/LoginFragment.kt)
- [Home Fragment](/app/src/main/java/com/dispositivosmoveis/ritterflix/ui/home/HomeFragment.kt)
- [MovieDetail Fragment](/app/src/main/java/com/dispositivosmoveis/ritterflix/ui/detail/MovieDetailFragment.kt)

### <a name="item25"></a>3.5 Banco de Dados

- Desenho do modelo do banco de dados (a ser implementado)4
  ![](/app/src/main/res/drawable/modelo_bd.JPG)

---

## <a name="item3"></a>4. Telas do aplicativo:

### <a name="item31"></a>4.1 Telas já desenvolvidas:

- <a name="item311"></a>Splash screen  
  <img src="/app/src/main/res/drawable/splash_screen.png" height="500" width="250">
- <a name="item312"></a>Tela de login  
  <img src="/app/src/main/res/drawable/login.jpeg" height="500" width="250">
- <a name="item313"></a>Tela principal  
  <img src="/app/src/main/res/drawable/home.png" height="500" width="250">
- <a name="item314"></a>Tela de detalhe do filme  
  <img src="/app/src/main/res/drawable/movie_detail.png" height="500" width="250">

### <a name="item32"></a>4.2 Telas a serem desenvolvidas:

- Tela de cadastro de conta
- Tela resultados de busca

---

## <a name="item4"></a>5. Dificuldades encontradas:

---

## <a name="item5"></a>5. Tarefas para 11/11:

| Tarefa                                      | Responsável        | Desenvolvido |
| ------------------------------------------- | ------------------ | ------------ |
| Componente "bottom bar"                     | Filipe Drago       | X            |
| Função de logout                            | Lucas Rambo        |              |
| Atualização do modelo do banco de dados     | Márcio Alves       | X            |
| Criação do ícone do aplicativo              | Rafael Panzenhagen |              |
| Botão "assistir trailer no _YouTube_"       | Márcio Alves       | X            |
| Botão "compartilhar filme"                  | Fabrício Wolff     | X            |
| Listar filmes a partir do botão "categoria" | Fabrício Wolff     |              |
| Validação de login com _FireBase_           | Fabrício Wolff     |              |
| Formulário de criação de conta              | Rafael Panzenhagen | X            |

## <a name="item6"></a>6. Tarefas sem data prevista:

| Tarefa                          | Responsável    | Desenvolvido |
| ------------------------------- | -------------- | ------------ |
| Função de busca                 | _a definir_    | X            |
| Implementação do banco de dados | Fabrício Wolff |              |
| Validação da criação de conta   | _a definir_    |              |
