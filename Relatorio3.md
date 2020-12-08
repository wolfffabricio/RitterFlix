# Aplicativo RitterFlix

# Relatório de entrega Final N2 - 07/12/2020

###### Grupo:

- [Fabrício Wolff](https://www.linkedin.com/in/fabricio-wolff-b621251b1)
- [Filipe Drago](https://www.linkedin.com/in/filipe-drago-0848856a/)
- [Lucas Rambo](https://www.linkedin.com/in/lucas-rambo89/)
- [Márcio Alves](http://linkedin.com/in/m%C3%A1rcio-moreira-alves-357168149/)
- [Rafael Panzenhagen](https://www.linkedin.com/in/rafael-panzenhagen/)

### Índice:

- [1. Inovações e implementações pós 02/12/2020](#item1)

  - [1.1 Validação de dados com FireBase](#item11)
  - [1.2 Ajustes no Compartilhamento de filmes](#item12)
  - [1.3 Pesquisa de Filmes](#item13)

- [2. Funcionalidades e implementações exigidas na entrega](#item2)

  - [2.1 Itens obrigatórios](#item21)
  - [2.2 Itens opcionais](#item22)
  - [2.3 Detalhamento dos itens concluídos](#item23)

  <!-- Item detalhamento: referenciar tudo para o relatório 1 -->

- [3. Detalhamento do Aplicativo](#item3)

  - [3.1 Informações do Relatório 1](#item31)

- [4. Animações demonstrativas](#item4)
  - [4.1 Login no aplicativo](#item41)
  - [4.2 Compartilhamento externo de informações](#item42)
  - [4.3 Acesso a informações compartilhadas externamente](#item43)
  - [4.4 Acesso a detalhes através das categorias](#item44)
- [5. Telas do Aplicativo](#item5)
  - [5.1 Telas já desenvolvidas - Relatório 1](#item51)
  - [5.2 Telas já desenvolvidas - Relatório 2 e 3](#item52)
  - [5.3 Telas a serem desenvolvidas](#item53)
- [6. Dificuldades encontradas](#item6)
- [7. Tarefas previstas no Relatório 1](#item7)
- [8. Tarefas previstas para a entrega final](#item8)

---

## <a name="item1"></a>1. Inovações e implementações pós 04/11/2020

### <a name="item11"></a>1.1 Login com Firebase

Implementamos o Login utilizando Firebase, a fim de disponibilizar uma facilidade de acesso à aplicação.
Este desenvolvimento trouxe alguma complexidade devido as alterações na estrutura da interface de Login e também no código propriamente.


### <a name="item12"></a>1.2 Compartilhamento de Filmes

Aprimoramos o compartilhamento de filmes, a fim de gerar maior facilidade e tornar a aplicação mais próxima da realidade do usuário, assim tornando possível compartilhar filmes pelo do uso de outros aprlicativos. Assim, escolhendo a Activity (e com isso a aplicação), que a aplicação irá utilizar para fazer o compartilhamento.

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

Concatena a URL bem como o ID do Filme a ser compartilhado, desta forma o método envia uma string com uma mensagem.

Para receber compartilhamentos implementamos um intent filter na Activity Home, no Android Manifest, o qual recebe a mesma URL e consegue tratá-las.

```
<intent-filter>
  <action android:name="android.intent.action.VIEW" />
  <category android:name="android.intent.category.DEFAULT" />
  <category android:name="android.intent.category.BROWSABLE" />
  <data android:host="filmes.uniritter.edu.br" android:path="/filme" android:scheme="http" />
</intent-filter>
```

Abaixo os GIFs que demonstram o funcinamento do recebimento e compartilhamento de informações [aqui](#item42) e [aqui](#item43).

### <a name="item11"></a>1.3 Implementação Pesquisa por Filmes

Implementada a Pesquisa por Filmes desntro da Aplicação. Permite realizar as buscas, emite mensagem quando não localiza o filme, e apresenta o filme caso ele seja localizado.


## <a name="item2"></a>2. Funcionalidades do aplicativo:

### <a name="item21"></a>2.1 Itens obrigatórios

- [x] Estar no padrão MVP ou MVVM
- [x] Fazer algum tipo de persistência de informação
- [x] Usar databinding
- [x] Fazer envio de informações exernamente na forma de link
- [ ] Fazer envio externamente de imagem
- [x] Receber e tratar informações através de link
- [ ] Fazer uso de algum sensor

### <a name="item22"></a>2.2 Itens opcionais

- [ ] Capacidade guardar comentários ou pontuações dos filmes dadas pelos usuários
- [ ] Listar filmes prediletos e/ou tenho interesse
- [ ] Favoritar filmes
- [ ] Capacidade de navegação entre filmes direto da tela de detalhe
- [ ] Apresentar uma mini-lista dos filmes da categoria na tela de detalhe do filme

### <a name="item23"></a>2.3 Detalhamento dos itens concluídos

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

## <a name="item4"></a>4. Animações Demonstartivas

- [4.1 Login no aplicativo](#item41)

- [4.2 Compartilhamento externo de informações](#item42)
- [4.3 Acesso a informações compartilhadas externamente](#item43)  
  <img src="/app/src/main/res/drawable/acesso-info-externa.gif">

- [4.4 Acesso a detalhes através das categorias](#item44)  
  <img src="/app/src/main/res/drawable/acesso-categoria-detalhe.gif">

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

### <a name="item52"></a>5.2 Telas já desenvolvidas - Relatório 2:

### <a name="item52"></a>4.2 Telas a serem desenvolvidas:

- Tela de cadastro de conta
- Tela resultados de busca

---

## <a name="item4"></a>4. Dificuldades encontradas:

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

| Tarefa                                      | Responsável        | Prioridade     |     Observação     |
| ------------------------------------------- | ------------------ | -------------- | ------------------ |
| Componente "bottom bar"                     | Filipe Drago       | -----sim------ |                    |
| Função de login com _FireBase_              | Filipe / Márcio    | -----sim------ |                    |
| Função de logout                            | Filipe / Márcio    | -----sim------ |                    |
| Atualização do modelo do banco de dados     | Márcio Alves       | -----sim------ |                    |
| Criação do ícone do aplicativo              | a definir          |                | Backlog do time    |
| Botão "assistir trailer no _YouTube_"       | Márcio Alves       | ----sim-----   |                    |
| Botão "compartilhar filme"                  | Fabrício Wolff     | ----sim-----   |                    |
| Listar filmes a partir do botão "categoria" | Fabrício Wolff     |                |                    |
| Validação de login com _FireBase_           | Fabrício Wolff     |                |                    |
| Formulário de criação de conta              | Rafael Panzenhagen | ----sim-----   |                    |
| Função de busca                             | Fabrício Wolff     | ----sim-----   |                    |
| Implementação do banco de dados             | a definir          |                | Backlog do time    |
| Validação da criação de conta               | Filipe Drago       |                |                    |
| Implementação de Sensores                   | a definir          |                | Backlog do time    |
