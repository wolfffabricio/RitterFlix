# Aplicativo RitterFlix

# Relatório de entrega A1 - 04/11/2020

###### Grupo:

- [Fabrício Wolff](https://www.linkedin.com/in/fabricio-wolff-b621251b1)
- [Filipe Drago](https://www.linkedin.com/in/filipe-drago-0848856a/)
- [Lucas Rambo](https://www.linkedin.com/in/lucas-rambo89/)
- [Márcio Alves](http://linkedin.com/in/m%C3%A1rcio-moreira-alves-357168149/)
- [Rafael Panzenhagen](https://www.linkedin.com/in/rafael-panzenhagen/)

### Índice:

- [1. Funcionalidades do do Aplicativo](#item1)
  - [1.1 Resumo das funcionalidades](#item11)
  - [1.2 Login no aplicativo](#item12)
  - [1.3 Criação de conta](#item13)
  - [1.4 Listagem de catálogo de filmes](#item14)
  - [1.5 Acesso a detalhes de filmes](#item15)
  - [1.6 Salvar categorias favoritas](#item16)
  - [1.7 Salvar filmes favoritos](#item17)
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

## <a name="item1"></a>1. Funcionalidades do aplicativo:

### <a name="item11"></a>1.1 Resumo das funcionalidades:

- [ ] Login no aplicativo
- [ ] Criação de conta
- [x] Listagem de catálogo de filmes
- [x] Acesso a detalhes de filmes
- [ ] Salvar categorias favoritas
- [ ] Salvar filmes favoritos

### <a name="item12"></a>1.2 Login no aplicativo

[Tela de Login](#item312)  
 O login do usuário fica disponível após a _Splash Screen_, com um delay de 4 segundos. A funcionalidade está sendo desenvolvida e no momento, para fins de testes, o usuário deve acessar o aplicativo com os dados _default_: login e senha "admin".  
 O aplicativo possui suporte para salvar os dados de login inseridos pelo usuário em _shared preferences_, para que posteriores acessos já se iniciem por padrão com estes dados.  
 O desenvolvimento da funcionalidade de login do aplicativo depende de tarefa posterior: a conexão com banco de dados, onde dados de usuário serão persistidos e permitirão a validação destes.

### <a name="item13"></a>1.3 Criação de conta

**A desenvolver:** A funcionalidade de criação de conta do usuário ainda será desenvolvida. Ficará acessível através de botão na tela de login.

### <a name="item14"></a>1.4 Listagem de catálogo de filmes

[Tela Principal](#item313)  
 Uma vez realizado login no aplicativo, a tela inicial trás o catálogo de filmes, dividido em categorias, através de requisição à API _Movie DB_.
As categorias são dinamicamente apresentadas, de forma a dar destaque àquelas que o usuário selecionou como favoritas.

### <a name="item15"></a>1.5 Acesso a detalhes de filmes

Cada banner de filme apresentado é um _link_ para que se acesse seus detalhes. Os detalhes do filme apresentam o banner na parte superior da tela, em formato horizontal. Abaixo, há uma breve sinopse do filme.  
 **A desenvolver:** Abaixo da sinopse haverá duas funcionalidades disponíveis ao usuário: acessar trailer do filme no _YouTube_ e compartilhar o filme por _WhatsApp_.

### <a name="item16"></a>1.6 Salvar categorias favoritas

**A desenvolver:** O usuário poderá salvar suas categorias favoritas. Esse dado será utilizado na organização da tela inicial, mostrando em destaque as categorias favoritas do usuário.

### <a name="item17"></a>1.7 Salvar filmes favoritos

**A desenvolver:** O uusário poderá salvar seus filmes favoritos. Esse dado será utilização na organização da tela inicial, mostrando em destaque uma categoria chamada "favoritos".

---

## <a name="item2"></a>2. Detalhamento do aplicativo:

### <a name="item21"></a>2.1 Tecnologias utilizadas na aplicação

###### As seguintes tecnologias serão utilizadas no desenvolvimento da aplicação:

- [IDE Android Studio](https://developer.android.com/studio)
- [Linguagem Kotlin](https://kotlinlang.org/)
- [Plataforma FireBase](https://firebase.google.com/)
- [The Movie DB API](https://www.themoviedb.org/)
- [Banco de dados MySQL](https://www.mysql.com/)

### <a name="item22"></a>2.2 Arquitetura MVVM

A arquitetura utilizada no projeto é a _Model-View-ViewModel_, arquitetura atualmente indicada pela Google no desenvolvimento de aplicativos _Android_.  
 Nesse modelo de arquitetura, os componentes _View_ são responsáveis pela estrutura, layout e aparência do que será exibido na tela. Os componentes _ViewModel_ são responsáveis pelo tratamento dos dados a serem exibidos na camada _View_, conforme regras de negócio e validações estabelecidas nos componentes da camada _Model_.

### <a name="item23"></a>2.3 Conexão com a API

A conexão com a API _The Movie DB_ é realizada através de três componentes que se encontram no repositório [http](src/main/java/com/dispositivosmoveis/ritterflix/repository/http).
A classe _ApiClient_ atua como cliente da API e é implementa os componentes que viabilizam o consumo da API.  
 A interface _ApiService_ é implementada em um objeto dentro da classe [Repository](src/main/java/com/dispositivosmoveis/ritterflix/repository/Repository.kt). Dessa forma, a classe _Repository_ é chamada na inicialização da activity [Home](src/main/java/com/dispositivosmoveis/ritterflix/ui/home/HomeActivity.kt) e popula a tela com os dados fornecidos pela API.  
 O componente [HttpUtils](src/main/java/com/dispositivosmoveis/ritterflix/repository/http/HttpUtils.kt) apresenta os dados utilizados na validação de acesso à API.

### <a name="item24"></a>2.4 Componentes

Até o momento, as seguintes _Activities_ foram desenvolvidas no aplicativo:

- [SplashScreen Activity](/app/src/main/java/com/dispositivosmoveis/ritterflix/ui/splash/SplashScreenActivity.kt)
- [Login Activity](/app/src/main/java/com/dispositivosmoveis/ritterflix/ui/login/LoginActivity.kt)
- [HomeActivity](/app/src/main/java/com/dispositivosmoveis/ritterflix/ui/home/HomeActivity.kt)

  Além disso, os seguintes _Fragments_ são inicializados através das _activities_:

- [Login Fragment](/app/src/main/java/com/dispositivosmoveis/ritterflix/ui/login/LoginFragment.kt)
- [Home Fragment](/app/src/main/java/com/dispositivosmoveis/ritterflix/ui/home/HomeFragment.kt)
- [MovieDetail Fragment](/app/src/main/java/com/dispositivosmoveis/ritterflix/ui/detail/MovieDetailFragment.kt)

### <a name="item25"></a>2.5 Banco de Dados

- Desenho do modelo do banco de dados (a ser implementado)
  ![](/app/src/main/res/drawable/modelo_bd.JPG)

---

## <a name="item3"></a>3. Telas do aplicativo:

### <a name="item31"></a>3.1 Telas já desenvolvidas:

- <a name="item311"></a>Splash screen  
  <img src="/app/src/main/res/drawable/splash_screen.png" height="500" width="250">
- <a name="item312"></a>Tela de login  
  <img src="/app/src/main/res/drawable/login.jpeg" height="500" width="250">
- <a name="item313"></a>Tela principal  
  <img src="/app/src/main/res/drawable/home.png" height="500" width="250">
- <a name="item314"></a>Tela de detalhe do filme  
  <img src="/app/src/main/res/drawable/movie_detail.png" height="500" width="250">

### <a name="item32"></a>3.2 Telas a serem desenvolvidas:

- Tela de cadastro de conta
- Tela resultados de busca

---

## <a name="item4"></a>4. Dificuldades encontradas:

### <a name="item41"></a>4.1 Dificuldades individuais

- Fabrício

  > "Não vejo como dificuldades, mas sim como desafios que tive: Implementação da arquitetura MVVM, estudar e implementar o Jetpack component e conseguir conciliar tempo de desenvolvimento com várias outras demandas do dia a dia."

- Filipe

  > "Em relação ao desenvolvimento do projeto, minha pricipal dificuldade foi com o atributo "id" dos componentes. Algumas vezes a IDE não acusa erro na compilação, mas a aplicação não funciona corretamente. Também tive dificuldades de hardware durante o estudo da disciplina, pois a máquina virtual acabava prejudicando muito o desempenho. O principal gargalo parece ser o HDD."

- Lucas

  > "Minha principal dificuldade tem sido o desempenho da minha máquina. A utilização do Android Studio + Máquina Virtual exige muito do hardware. Tenho tido dificuldades também de entender a matéria (e consequentemente o desenvolvimento do projeto) mas aos poucos estou buscando dominar o assunto."

- Márcio

  > "Minha principal dificuldade é em relação ao conhecimento prévio em programação mobile android que a disciplina exige. Minha área de atuação não é de front-end, então não possuo esse conhecimento necessário já adquirido. Some-se a esta dificuldade o volume de conteúdo e a falta de tempo em aula para praticar, exercícios propriamente, somente assistindo as aulas certamente as dúvidas e dificuldade/erros/problemas não aparecem, então esses problemas acumulam para o momento de por a "mão na massa".
  > Não estou com isso reclamando da didática, do professor, da condução das aulas, há um conteúdo à ser transmitido, ele requer um volume por aula e tudo certo."

- Rafael
  > "Minha principal dificuldade está sendo entender todos os componentes utilizados no desenvolvimento _mobile_. São conceitos muito novos para mim, que não estão presentes em outras arquiteturas, como _Web_ e _Desktop_. Como os componentes parecem formar um paradigma na programação _mobile_, entendê-los é crucial para acompanhar a disciplina. A quantidade nova de conteúdo também não é bem conciliável com uma rotina corrida, com pouco tempo disponível para investir em assuntos mais específicos. Meu grande desafio tem sido a administração do tempo."

---

## <a name="item5"></a>5. Tarefas para 11/11:

| Tarefa                                      | Responsável        |
| ------------------------------------------- | ------------------ |
| Componente "bottom bar"                     | Filipe Drago       |
| Função de logout                            | Lucas Rambo        |
| Atualização do modelo do banco de dados     | Márcio Alves       |
| Criação do ícone do aplicativo              | Rafael Panzenhagen |
| Botão "assistir trailer no _YouTube_"       | Márcio Alves       |
| Botão "compartilhar filme"                  | Fabrício Wolff     |
| Listar filmes a partir do botão "categoria" | Fabrício Wolff     |
| Validação de login com _FireBase_           | Fabrício Wolff     |
| Formulário de criação de conta              | Rafael Panzenhagen |

## <a name="item6"></a>6. Tarefas sem data prevista:

| Tarefa                          | Responsável    |
| ------------------------------- | -------------- |
| Função de busca                 | _a definir_    |
| Implementação do banco de dados | Fabrício Wolff |
| Validação da criação de conta   | _a definir_    |
