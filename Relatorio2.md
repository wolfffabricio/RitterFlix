# Aplicativo RitterFlix

# Relatório de entrega N2 - 02/12/2020

###### Grupo:

- [Fabrício Wolff](https://www.linkedin.com/in/fabricio-wolff-b621251b1)
- [Filipe Drago](https://www.linkedin.com/in/filipe-drago-0848856a/)
- [Lucas Rambo](https://www.linkedin.com/in/lucas-rambo89/)
- [Márcio Alves](http://linkedin.com/in/m%C3%A1rcio-moreira-alves-357168149/)
- [Rafael Panzenhagen](https://www.linkedin.com/in/rafael-panzenhagen/)

### Índice:

- [1. Inovações e implementações pós 04/11/2020](#item1)
  - [1.1 Validação de dados com FireBase](#item11)
  - [1.2 Compartilhamento de filmes](#item12)
  - [1.3 Bottom Bar](#item13)
  - [1.4 Carrossel destaques](#item14)
  - [1.5 Acesso de filmes através das categorias](#item15)
  - [1.6 Atualização do banco de dados](#item16)
- [2. Funcionalidades e implementações exigidas na entrega](#item2)
  - [2.1 Itens obrigatórios](#item21)
  - [2.2 Itens opcionais](#item22)
- [3. Detalhamento do Aplicativo](#item3)
- [4. Animações demonstrativas](#item4)
  - [4.1 Login no aplicativo](#item41)
  - [4.2 Compartilhamento externo de informações](#item42)
  - [4.3 Acesso a informações compartilhadas externamente](#item43)
  - [4.4 Acesso a detalhes através das categorias](#item44)
- [5. Telas do Aplicativo](#item5)
  - [5.1 Telas já desenvolvidas - Relatório 1](#item51)
  - [5.2 Telas a serem desenvolvidas](#item52)
- [6. Tarefas previstas no Relatório 1](#item6)
- [7. Tarefas previstas para a entrega final](#item7)

---

## <a name="item1"></a>1. Inovações e implementações pós 04/11/2020

### <a name="item11"></a>1.1 Autenticação de login com FireBase

Está sendo implementada a validação de usuário através do FireBase. Os métodos ainda estão passando por testes e serão incluídos no repositório até a entrega final.
Para a autenticação, um objeto da classe FireBaseAuth é instanciado na Activity Login.

Há uma pré-validação do preenchimento dos antes de se executar a validação do FireBase em si. Isso acontece através do seguinte método:

```
private fun signUpUser(){
  if  (edtEmail!!.text.toString().isEmpty()){
    edtEmail!!.error = "Por favor insira o e-mail"
    edtEmail!!.requestFocus()
    return
  }

  if (!Patterns.EMAIL_ADDRESS.matcher(edtEmail!!.text.toString()).matches()){
    edtEmail!!.error ?: "Por favor coloque um e-mail válido"
    edtEmail!!.requestFocus()
    return
  }

  if (edtPassword!!.text.toString().isEmpty()){
    edtPassword!!.error = "Por favor insira o password"
    edtPassword!!.requestFocus()
    return
  }

  loginUser(email = edtEmail!!.text.toString(), password = edtPassword!!.text.toString())
}
```

Como se pode notar, após as validações o método _loginUser_ é executado.
Tal método executa a validação de login do FireBase:

```
class LoginFirebase : AppCompatActivity() {
  ...
  private var mAuth: FirebaseAuth? = null
  ...
  }
  ...
  private fun loginUser(email: String, password: String) {
    mAuth!!.createUserWithEmailAndPassword(email, password)
      .addOnCompleteListener(this) { task ->
        if (task.isSuccessful) {
          // Sign in success, update UI with the signed-in user's information
          Log.d("TAG", "createUserWithEmail:success")
          val user = mAuth!!.currentUser
          Toast.makeText(applicationContext, "Login OK!", Toast.LENGTH_SHORT).show()
          openHomeActivity()
        } else {
          // If sign in fails, display a message to the user.
          Log.w("TAG", "createUserWithEmail:failure", task.exception)
          Toast.makeText(applicationContext, "Authentication failed.",
          Toast.LENGTH_SHORT).show()
        }
    }
  }
  ...
}
```

_AVISO: Os métodos apresentados ainda não estão presentes neste repositório. Serão integrados até a entrega final._

### <a name="item12"></a>1.2 Bottom bar

A bottom bar possui três botões: Home, Search e More. O botão Home direciona para a tela inicial do aplicativo, o botão Search abre a tela de pesquisa e o botão More abre um menu inicial. Funcionalidades Search e More ainda não foram implementadas.  
A bottom bra é inflada dentro dos layouts através do seguinte método:

```
bottom_navigation.setOnNavigationItemSelectedListener{
  when (it.itemId){
    R.id.ic_home_nav -> makeCurrentFragment(homeFragment)
    R.id.ic_search_nav -> makeCurrentFragment(searchFragment)
    R.id.ic_more_nav -> makeCurrentFragment(moreFragment)
  }
  true
}
```

### <a name="item13"></a>1.3 Compartilhamento externo de filmes

O compartilhamento de filmes permite escolher qual Activity (e, consequentemente, qual aplicação) no dispositivo será utilizada para fazê-lo.
O compartilhamento é realizado pelo seguinte método:

```
private fun shareMovie() {
  viewModel.movie.value.let {
    if (it != null) {
      val shareContent = "Olha que massa esse filme! Confira agora no RitterFlix.\n\n filmes.uniritter.edu.br/filme?id=" + it.id + "&de=Jean"

      Picasso.get()
        .load(BASE_POST_URL + it.posterPath)
        .into(object : Target {
          override fun onBitmapLoaded(bitmap: Bitmap, from: LoadedFrom) {
            getLocalBitmapUri(bitmap, activity?.applicationContext!!)?.let { it1 ->
              shareContentWithImage(it1, shareContent)
            }
          }

          override fun onPrepareLoad(placeHolderDrawable: Drawable) {}

          override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
            Log.e("Bitmap Failed: ", e.toString())
          }
        })
    }
  }
}
```

O método envia uma string com uma mensagem, concatenando a URL prevista em aula e o id do filme compartilhado.

Da mesma forma, há um intent filter na Activity Home, no Android Manifest, para receber a mesma URL. Desta forma, o aplicativo também consegue tratar o recebimento de informações compartilhadas.

```
<intent-filter>
  <action android:name="android.intent.action.VIEW" />
  <category android:name="android.intent.category.DEFAULT" />
  <category android:name="android.intent.category.BROWSABLE" />
  <data android:host="filmes.uniritter.edu.br" android:path="/filme" android:scheme="http" />
</intent-filter>
```

Os GIFs demonstrando o funcinamento do compartilhamento e do recebimento de informações foram apresentados [aqui](#item42) e [aqui](#item43).

### <a name="item14"></a>1.4 Carrossel destaques

O carrossel é apresentado dentro da Activity Home e inflado por método na HomeFragment. Ele é um layout horizontal inserido no layout home. No momento, os filmes apresentados no carrossel são uma lista estática presente no próprio método.
O seguinte método ativa o carrossel:

```
private fun observeCarouselMovies(){
  val imgs:IntArray = intArrayOf(
    R.drawable.pantera_negra,
    R.drawable.spider_man_far_from_home,
    R.drawable.aquaman,
    R.drawable.jurassic_world
  )

  carousel_view.setImageListener{position, imageView ->
    imageView.setImageResource(imgs[position])
  }
  carousel_view.pageCount = imgs.size
}
```

### <a name="item15"></a>1.5 Acesso de filmes através de categorias

A implementação da funcionalidade foi iniciada pelo método de acesso às categorias de filmes (retornadas por consulta à API) na HomeActivity.
Segue o método:

```
fun goToCategoryFilmsList(categoryId: Int) {
  supportFragmentManager
    .beginTransaction()
    .replace(R.id.home_container, CategoryFilmsFragment.newInstance(categoryId), "categoryFilmsFragment")
    .addToBackStack(null)
    .commit()
}
```

Na aplicação, a categoria de filmes como funcionalidade é dividida em três compomentes:

- [CategoryFilmsAdapter](src/main/java/com/dispositivosmoveis/ritterflix/ui/CategoryFilms/CategoryFilmsAdapter.kt)
- [CategoryFilmsFragment](src/main/java/com/dispositivosmoveis/ritterflix/ui/CategoryFilms/CategoryFilmsFragment.kt)
- [CategoryFilmsViewModel](src/main/java/com/dispositivosmoveis/ritterflix/ui/CategoryFilms/CategoryFilmsFragment.kt)

O método responsável pelo acesso ao detalhe dos filmes dentro das categorias é o seguinte:

```
override fun onOptionsItemSelected(item: MenuItem): Boolean {
  when (item.itemId) {
    android.R.id.home -> activity?.onBackPressed()
  }

  return super.onOptionsItemSelected(item)
}
```

É possível ver uma demonstração do acesso aos filmes através das categorias [aqui](#item44).

### <a name="item16"></a>1.6 Atualização do banco de dados

<img src="/app/src/main/res/drawable/bd-atualizado.png">

## <a name="item2"></a>2. Funcionalidades do aplicativo:

### <a name="item21"></a>2.1 Itens obrigatórios

- [x] Estar no padrão MVP ou MVVM
- [x] Fazer algum tipo de persistência de informação
- [x] Usar databinding
- [x] Fazer envio de informações exernamente na forma de link
- [x] Fazer envio externamente de imagem
- [x] Receber e tratar informações através de link
- [ ] Fazer uso de algum sensor

### <a name="item22"></a>2.2 Itens opcionais

- [ ] Capacidade guardar comentários ou pontuações dos filmes dadas pelos usuários
- [ ] Listar filmes prediletos e/ou tenho interesse
- [ ] Favoritar filmes
- [ ] Capacidade de navegação entre filmes direto da tela de detalhe
- [ ] Apresentar uma mini-lista dos filmes da categoria na tela de detalhe do filme

---

## <a name="item3"></a>3. Detalhamento do aplicativo:

As seguintes informações constam no [Relatório 1](/Relatorio1.md):

- Tecnologias utilizaddas na aplicação
- Arquitetura MVVM
- Conexão com a API
- Componentes
- Banco de dados (atualizado neste relatório)

---

## <a name="item4"></a>4. Animações Demonstrativas

### <a name="#item41"></a>4.1 Login no aplicativo

  <img src="/app/src/main/res/drawable/login.gif" height="500" width="250">

### <a name="#item42"></a>4.2 Compartilhamento externo de informações

  <img src="/app/src/main/res/drawable/compartilhar-externo.gif" height="500" width="250">

### <a name="#item43"></a>4.3 Acesso a informações compartilhadas externamente

  <img src="/app/src/main/res/drawable/acesso-info-externa.gif" height="500" width="250">

### <a name="#item44"></a>4.4 Acesso a detalhes através das categorias

  <img src="/app/src/main/res/drawable/acesso-categoria-detalhe.gif" height="500" width="250">

---

## <a name="item5"></a>5. Telas do aplicativo:

### <a name="item51"></a>5.1 Telas já desenvolvidas - Relatório 1:

- <a name="item311"></a>Splash screen  
  <img src="/app/src/main/res/drawable/splash_screen.png" height="500" width="250">
- <a name="item312"></a>Tela de login  
  <img src="/app/src/main/res/drawable/login.jpeg" height="500" width="250">
- <a name="item313"></a>Tela principal  
  <img src="/app/src/main/res/drawable/home.png" height="500" width="250">
- <a name="item314"></a>Tela de detalhe do filme  
  <img src="/app/src/main/res/drawable/movie_detail.png" height="500" width="250">

### <a name="item52"></a>5.2 Telas a serem desenvolvidas:

- Tela de cadastro de conta
- Tela resultados de busca

---

## <a name="item5"></a>5. Tarefas previstas na entrega Relatório 1:

| Tarefa                                      | Responsável        | Desenvolvido   |
| ------------------------------------------- | ------------------ | -------------- |
| Componente "bottom bar"                     | Filipe Drago       | -----sim------ |
| Função de logout                            | Lucas Rambo        |                |
| Atualização do modelo do banco de dados     | Márcio Alves       | ----sim-----   |
| Criação do ícone do aplicativo              | Rafael Panzenhagen |                |
| Botão "assistir trailer no _YouTube_"       | Márcio Alves       | ----sim-----   |
| Botão "compartilhar filme"                  | Fabrício Wolff     | ----sim-----   |
| Listar filmes a partir do botão "categoria" | Fabrício Wolff     |                |
| Validação de login com _FireBase_           | Fabrício Wolff     |                |
| Formulário de criação de conta              | Rafael Panzenhagen | ----sim-----   |
| Função de busca                             | _a definir_        | ----sim-----   |
| Implementação do banco de dados             | Fabrício Wolff     |                |
| Validação da criação de conta               | _a definir_        |                |

---

## <a name="item6"></a>6. Tarefas previstas para a entrega final:

| Tarefa                                      | Responsável        | Prioridade     |
| ------------------------------------------- | ------------------ | -------------- |
| Componente "bottom bar"                     | Filipe Drago       | -----sim------ |
| Função de logout                            | Lucas Rambo        |                |
| Atualização do modelo do banco de dados     | Márcio Alves       | ----sim-----   |
| Criação do ícone do aplicativo              | Rafael Panzenhagen |                |
| Botão "assistir trailer no _YouTube_"       | Márcio Alves       | ----sim-----   |
| Botão "compartilhar filme"                  | Fabrício Wolff     | ----sim-----   |
| Listar filmes a partir do botão "categoria" | Fabrício Wolff     |                |
| Validação de login com _FireBase_           | Fabrício Wolff     |                |
| Formulário de criação de conta              | Rafael Panzenhagen | ----sim-----   |
| Função de busca                             | _a definir_        | ----sim-----   |
| Implementação do banco de dados             | Fabrício Wolff     |                |
| Validação da criação de conta               | _a definir_        |                |
