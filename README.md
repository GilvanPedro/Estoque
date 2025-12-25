# 📦 Sistema de Gerenciamento de Estoque (Estoque)

## 🎯 Visão Geral do Projeto

O **Estoque** é uma aplicação de gerenciamento de inventário desenvolvida em **Java** que opera via **Interface de Linha de Comando (CLI)**. O projeto foi concebido para demonstrar a aplicação de princípios de **Programação Orientada a Objetos (POO)** e a utilização de um *build tool* moderno como o **Maven** em um contexto de aplicação de console.

O sistema permite o controle básico de produtos e a gestão de usuários, incluindo um mecanismo de autenticação simples, mas seguro, utilizando *hashing* de senhas.

## 🏛 Arquitetura e Design

A aplicação adota uma arquitetura modular que segue, de forma simplificada, o padrão **Model-View-Controller (MVC)**, embora adaptado para um ambiente de console. O código está estruturado em pacotes claros, promovendo a separação de responsabilidades:

| Pacote | Responsabilidade | Descrição |
| :--- | :--- | :--- |
| `br.com.Entity` | **Model** (Entidades) | Contém as classes de dados, como `Estoque` (Produto) e `Usuario`, que representam as informações do sistema. |
| `br.com.Controller` | **Controller** (Lógica de Negócio) | Contém a lógica de negócio e as operações de manipulação de dados, como `EstoqueController`, `MovimentacaoEstoqueController` e `UsuarioController`. |
| `br.com` | **View/Main** (Interface e Inicialização) | Contém a classe `Main` para inicialização e a classe `GerenciarEstoque`, que atua como a **View** (apresentando o menu e recebendo a entrada do usuário) e o **Controlador Principal** (orquestrando as chamadas aos *Controllers* de negócio). |
| `br.com.Criptografia` | **Serviço** (Segurança) | Contém a lógica para *hashing* de senhas, garantindo que as credenciais dos usuários sejam armazenadas de forma segura. |

O fluxo de controle é iniciado em `Main.java`, que instancia `GerenciarEstoque` e chama o método `controleEstoque()`, que é o *loop* principal de interação com o usuário.

## ✨ Funcionalidades Detalhadas

O sistema oferece as seguintes funcionalidades, acessíveis via menu de console:

1.  **Gestão de Produtos:**
    *   Adicionar novos produtos ao estoque.
    *   Listar todos os produtos com seus detalhes.
    *   Remover produtos existentes.
    *   Buscar produtos por ID.
2.  **Gestão de Usuários:**
    *   Criação de novas contas de usuário.
    *   Autenticação (Login) de usuários.
3.  **Movimentação de Estoque:**
    *   Registro de entrada e saída de produtos, mantendo um histórico de movimentações.

## 🛠 Tecnologias e Dependências

O projeto é construído com **Java 17+** e utiliza o **Maven** para gerenciamento de dependências e ciclo de vida do projeto.

| Tecnologia | Versão | Tipo | Detalhe |
| :--- | :--- | :--- | :--- |
| **Java** | 17+ | Linguagem | Requisito de *runtime* e compilação. |
| **Maven** | 3.x | Build Tool | Gerenciamento de dependências e empacotamento. |
| **jBCrypt** | 0.4 | Dependência | Utilizada para o *hashing* seguro de senhas no módulo de `Criptografia`. |

## ⚙ Pré-requisitos

Certifique-se de ter os seguintes softwares instalados em sua máquina:

*   **Java Development Kit (JDK)**: Versão 17 ou superior.
*   **Apache Maven**: Versão 3.x ou superior.

## 🚀 Instalação e Execução

Siga os passos abaixo para configurar e rodar a aplicação:

1.  **Clone o Repositório:**
    ```bash
    git clone https://github.com/GilvanPedro/Estoque.git
    cd Estoque/Estoque
    ```

2.  **Compile e Empacote o Projeto:**
    Utilize o Maven para limpar, compilar e empacotar o projeto em um arquivo JAR executável:
    ```bash
    mvn clean install
    ```

3.  **Execute a Aplicação:**
    O arquivo JAR será gerado no diretório `target`. Execute-o com o comando `java -jar`:
    ```bash
    java -jar target/Estoque-1.0-SNAPSHOT.jar
    ```
    *Nota: O nome do arquivo JAR (`Estoque-1.0-SNAPSHOT.jar`) pode variar ligeiramente dependendo da versão do projeto.*

4.  **Interação:**
    A aplicação será iniciada no console, solicitando o login ou a criação de uma nova conta. Siga as instruções do menu para interagir com o sistema de estoque.

## 📄 Licença

Este projeto está licenciado sob a **Licença MIT**. Para detalhes completos, consulte o arquivo [LICENSE](LICENSE) no repositório.

## 🧑‍💻 Autor

Este projeto foi desenvolvido por [Gilvan Pedro](https://github.com/GilvanPedro).
