## Descrição do projeto

Você deve criar um aplicativo que irá listar os repositórios públicos mais populares relacionados à Java no GitHub, usando a [API do GitHub](https://developer.github.com/v3/) para buscar os dados necessários.

O aplicativo deve exibir inicialmente uma lista paginada dos repositórios, ordenados por popularidade decrescente (exemplo de chamada da API: `https://api.github.com/search/repositories?q=language:Java&sort=stars&page=1`).

Cada repositório deve exibir Nome do repositório, Descrição do Repositório, Nome / Foto do autor, Número de Stars, Número de Forks.

Ao tocar em um item, deve levar a lista de Pull Requests do repositório. Cada item da lista deve exibir Nome / Foto do autor do PR, Título do PR, Data do PR e Body do PR.

Ao tocar em um item, deve abrir no browser a página do Pull Request em questão.

Você pode se basear neste mockup para criar as telas:

![mockup](https://raw.githubusercontent.com/myfreecomm/desafio-mobile-android/master/mockup-android.png)

Sua aplicação deve:

- Retrofit
- Glide/Picasso/Frescco
- persistir os dados localmente com o Room
- Fragmentsx
- Arquitetura MVVM
- possuir cobertura de testes unitários para o DAO.
