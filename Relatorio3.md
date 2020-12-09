# Aplicativo RitterFlix

# Relatório de entrega Final N2 - 07/12/2020

###### Grupo:

- [Fabrício Wolff](https://www.linkedin.com/in/fabricio-wolff-b621251b1)
- [Filipe Drago](https://www.linkedin.com/in/filipe-drago-0848856a/)
- [Lucas Rambo](https://www.linkedin.com/in/lucas-rambo89/)
- [Márcio Alves](http://linkedin.com/in/m%C3%A1rcio-moreira-alves-357168149/)
- [Rafael Panzenhagen](https://www.linkedin.com/in/rafael-panzenhagen/)

### Índice:

- [1. Desenvolvimentos e Melhorias implementados após 02/12/2020](#item1)
  - [1.1 Validação de dados com FireBase](#item11)
  - [1.2 Novos usuários](#item12)
  - [1.3 Compartilhamento de filmes](#item13)
  - [1.4 Assistir Trailer dos Filmes](#item14)
  - [1.5 Pesquisa de Filmes](#item15)

- [2. Funcionalidades e implementações exigidas na entrega](#item2)

  - [2.1 Itens obrigatórios](#item21)
  - [2.2 Itens opcionais](#item22)

  <!-- Item detalhamento: referenciar tudo para o relatório 1 -->

- [3. Detalhamento do Aplicativo](#item3)
  - [3.1 Informações do Relatório 1](#item31)
  - [3.2 Informações do Relatório 2](#item32)

- [4. Animações demonstrativas](#item4)
  - [4.1 Login no aplicativo](#item41)
  - [4.2 Compartilhamento externo de informações](#item42)
  - [4.3 Acesso a informações compartilhadas externamente](#item43)
  - [4.4 Pesquisa de Filmes](#item44)
  
- [5. Telas do Aplicativo](#item5)
  - [5.1 Telas desenvolvidas - Relatório 1](#item51)
  - [5.2 Telas desenvolvidas - Relatório 2 e 3](#item52)

- [6. Tarefas implementadas na entrega do Relatório 1](#item6)

- [7. Status das tarefas na entrega do Relatório 2](#item7)

- [8. Tarefas implementadas para a entrega final](#item8)

---

### Histórico de diário de bordo

- [Setembro](/diario-de-bordo/diario-grupo-set-2020.pdf)
- [Outubro](/diario-de-bordo/diario-grupo-out-2020.pdf)
- [Novembro](/diario-de-bordo/diario-grupo-nov-2020.pdf)
- [Dezembro](/diario-de-bordo/diario-grupo-dez-2020.pdf)

---

## <a name="item1"></a>1. Inovações e implementações após 04/11/2020

### <a name="item11"></a>1.1 Autenticação de login com FireBase

Finalizamos a implementação da validação de usuário através do FireBase. Os métodos foram testados, encontramos dificuldades nas volidações de usuário e senha.
O merge entre as branchs de desenvolvimento foi realizado, trouxe algumas necessidades de adaptação, pois altera bastante a estrutura da aplicação.

O método abaixo, apresenta uma pré-validação do preenchimento dos antes de se executar a validação do FireBase propriamente.

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
### <a name="item12"></a>1.2 Novos Usuários

Capacidade de registrar novos usuários com e-mail e senha, persistindo os dados.

```
class SignInFragment : Fragment() {

    companion object {
        fun newInstance() = SignInFragment()
    }

    private var mAuth: FirebaseAuth? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mAuth = FirebaseAuth.getInstance()
        criar_conta_btn!!.setOnClickListener {
            signUpUser()
        }
        login_back_btn!!.setOnClickListener{
            backLogin()
        }

    }

    private fun signUpUser() {
        if (et_mailSignIn!!.text.toString().isEmpty()) {
            et_mailSignIn!!.error = "Por favor insira o e-mail"
            et_mailSignIn!!.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(et_mailSignIn!!.text.toString()).matches()) {
            et_mailSignIn!!.error ?: "Por favor coloque um e-mail válido"
            et_mailSignIn!!.requestFocus()
            return
        }
        if (et_password_criar!!.text.toString().isEmpty()) {
            et_password_criar!!.error = "Por favor insira o password"
            et_password_criar!!.requestFocus()
            return
        }
        loginUser(
            email = et_mailSignIn!!.text.toString(),
            password = et_password_criar!!.text.toString()
        )
    }

    private fun loginUser(email: String, password: String) {
        mAuth!!.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "createUserWithEmail:success")
                    Toast.makeText(context, "Conta criada OK!", Toast.LENGTH_SHORT).show()
                    openHomeActivity()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        context, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun openHomeActivity() {
        val intent = Intent(context, HomeActivity::class.java)
        activity?.finish()
        startActivity(intent)
    }

    private fun backLogin() {
        val intent = Intent(context, LoginActivity::class.java)
        activity?.finish()
        startActivity(intent)
    }
}

```

### <a name="item13"></a>1.3 Compartilhamento externo de filmes

Foram efetuados ajustes no compartilhamento de filmes permite escolher qual Activity (e, consequentemente, qual aplicação) no dispositivo será utilizada para fazê-lo.
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

### <a name="item14"></a>1.4 Assistir Trailer dos Filmes

A partir dos detalhes de um determinado Filme, o usuário tem a possibilidade de assistir um trailer do respectivo Filme.

### <a name="item15"></a>1.5 Implementação Pesquisa por Filmes

Implementada a Pesquisa por Filmes desntro da Aplicação. Permite realizar as buscas, emite mensagem quando não localiza o filme, e apresenta o filme caso ele seja localizado.


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
- [x] Capacidade de pesquisar Filmes
- [x] Utilizar FireBase para efetuar login no APP
- [x] Criar novos usuários para login

---

## <a name="item3"></a>3. Detalhamento do aplicativo:

### <a name="item31"></a>3.1 Informações do Relatório 1:

As seguintes informações constam no relatório 1:

- [Tecnologias utilizaddas na aplicação]
- [Arquitetura MVVM]
- [Conexão com a API]
- [Componentes]
- [Banco de dados (atualizado neste relatório)]

---

### <a name="item32"></a>3.2 Informações dos Relatórios 2 e 3:

Devido as entregas serem combinadas, onde a entrega do relatório 2 referia-se a entrega parcial da avaliação e a entrega do relatório 3 a entrega final, optamos por criar um item adicional que apresenta o status na entrega parcial e a respectiva evolução para a entrega final.
Este detalhamento encontra-se nos itens 7 e 8 deste relatório.

---

## <a name="item4"></a>4. Animações Demonstrativas

### <a name="item41"></a>4.1 Login no aplicativo

  <img src="/app/src/main/res/drawable/login_gif.gif" height="500" width="250">

### <a name="item42"></a>4.2 Compartilhamento externo de informações

  <img src="/app/src/main/res/drawable/compartilhar_externo.gif" height="500" width="250">

### <a name="item43"></a>4.3 Acesso a informações compartilhadas externamente

  <img src="/app/src/main/res/drawable/acesso_info_externa.gif" height="500" width="250">

### <a name="item44"></a>4.4 Pesquisa de Filmes

  <img src="/app/src/main/res/drawable/pesquisa_filmes.gif" height="500" width="250">

---

## <a name="item5"></a>5. Telas do aplicativo:

### <a name="item51"></a>5.1 Telas desenvolvidas - Relatório 1:

- <a name="item511"></a>Splash screen  
  <img src="/app/src/main/res/drawable/splash_screen.png" height="500" width="250">
- <a name="item512"></a>Tela de login  
  <img src="/app/src/main/res/drawable/login.jpeg" height="500" width="250">
- <a name="item513"></a>Tela principal  
  <img src="/app/src/main/res/drawable/home.png" height="500" width="250">
- <a name="item514"></a>Tela de detalhe do filme  
  <img src="/app/src/main/res/drawable/movie_detail.png" height="500" width="250">

### <a name="item52"></a>5.2 Telas desenvolvidas - Relatórios 2 e 3:

- <a name="item521"></a>Tela de login com Firebase
  <img src="/app/src/main/res/drawable/login.jpeg" height="500" width="250">
- <a name="item522"></a>Tela de Busca/Pesquisa  
  <img src="/app/src/main/res/drawable/pesquisa_filmes.jpeg" height="500" width="250">
- <a name="item523"></a>Criação de conta 
  <img src="/app/src/main/res/drawable/cria_usuario.jpeg" height="500" width="250">
- <a name="item524"></a>Mensagem de erro Login 
  <img src="/app/src/main/res/drawable/erro_login.jpeg" height="500" width="250">


---

## <a name="item6"></a>6. Tarefas implementadas na entrega do Relatório 1:

| Tarefa                                      | Responsável        |  Desenvolvido  |
| ------------------------------------------- | ------------------ | -------------- |
| Componente "bottom bar"                     | Filipe Drago       | -----sim------ |
| Função de logout                            | Lucas Rambo        |                |
| Atualização do modelo do banco de dados     | Márcio Alves       | -----sim------ |
| Criação do ícone do aplicativo              | Rafael Panzenhagen |                |
| Botão "assistir trailer no _YouTube_"       | Márcio Alves       | -----sim------ |
| Botão "compartilhar filme"                  | Fabrício Wolff     | -----sim------ |
| Listar filmes a partir do botão "categoria" | Fabrício Wolff     |                |
| Validação de login com _FireBase_           | Fabrício Wolff     |                |
| Formulário de criação de conta              | Rafael Panzenhagen | -----sim------ |
| Função de busca                             | _a definir_        | -----sim------ |
| Implementação do banco de dados             | Fabrício Wolff     |                |
| Validação da criação de conta               | _a definir_        |                |

---

## <a name="item7"></a>7. Status das tarefas na entrega do Relatório 2:

| Tarefa                                                 | Responsável        | Prioridade |
| ------------------------------------------------------ | ------------------ | ---------- |
| Função de logout\*                                     | Filipe Drago       | 3          |
| Implementar uso de biometria                           | _a definir_        | 2          |
| Implementar comentários e avaliações dos filmes        | Fabrício Wolff     | 2          |
| Apresentar filmes de mesma categoria dentro do detalhe | Fabrício Wolff     | 2          |
| Implementar categoria favoritos                        | _a definir_        | 2          |
| Função "favoritar filmes"                              | _a definir_        | 2          |
| Botão "assistir trailer no _YouTube_"                  | Márcio Alves       | 2          |
| Função de busca\*                                      | Filipe Drago       | 3          |
| Validação de login com _FireBase_\*                    | Filipe Drago       | 3          |
| Formulário de criação de conta\*                       | Rafael Panzenhagen | 1          |
| Validação da criação de conta                          | _a definir_        | 1          |
| Criação do ícone do aplicativo                         | Rafael Panzenhagen | 1          |

\* Funções semi-implementadas ou dependendo de testes

Legenda de prioriodades:

- 1: baixa prioridade
- 2: média prioridade
- 3: alta prioridade

---

## <a name="item8"></a>8. Tarefas implementadas para a entrega final:

| Tarefa                                                 | Responsável        | Desenvolvido  | Observações |
| ------------------------------------------------------ | ------------------ | ------------- | ----------- |
| Função de logout                                       | Filipe Drago       | -----sim----- |             |
| Implementar uso de biometria                           | _a definir_        |               | Backlok     |
| Implementar comentários e avaliações dos filmes        | _a definir_        |               | Backlok     |
| Apresentar filmes de mesma categoria dentro do detalhe | _a definir_        |               | Backlok     |
| Implementar categoria favoritos                        | _a definir_        |               | Backlok     |
| Função "favoritar filmes"                              | _a definir_        |               | Backlok     |
| Botão "assistir trailer no _YouTube_"                  | Márcio Alves       | -----sim----- |             |
| Função de busca                                        | Fabricio Wolff     | -----sim----- |             |
| Implementação de login com _FireBase_                  | Filipe / Márcio    | -----sim----- |             |
| Formulário de criação de conta                         | Rafael Panzenhagen | -----sim----- |             |
| Validação da criação de conta                          | Fabricio Wolff     | -----sim----- |             |
| Criação do ícone do aplicativo                         | _a definir_        |               | Backlok     |

