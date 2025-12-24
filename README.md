# 📦 Sistema de Gerenciamento de Estoque

## Descrição do Projeto

O projeto **Estoque** é um sistema de gerenciamento de inventário desenvolvido em **Java** para ser executado via linha de comando (CLI). Ele foi projetado para auxiliar no controle de produtos, permitindo o registro, a consulta e a gestão básica de um estoque.

A aplicação segue uma arquitetura modular, separando as responsabilidades em entidades (`Entity`), controladores (`Controller`) e a lógica principal de gerenciamento (`GerenciarEstoque`).

## ✨ Funcionalidades Principais

*   **Gerenciamento de Produtos:** Adicionar, listar, atualizar e remover produtos do estoque.
*   **Interface de Linha de Comando (CLI):** Interação simples e direta através do console.

## 🛠 Tecnologias Utilizadas

O projeto foi construído utilizando as seguintes tecnologias:

| Tecnologia | Versão | Propósito |
| :--- | :--- | :--- |
| **Java** | 17+ | Linguagem de programação principal. |
| **Maven** | 3.x | Ferramenta de automação de construção e gerenciamento de dependências. |

## ⚙ Pré-requisitos

Para executar este projeto, você precisará ter instalado:

*   **Java Development Kit (JDK)**: Versão 17 ou superior.
*   **Apache Maven**: Para construir e executar o projeto.

## 🚀 Instalação

Siga os passos abaixo para clonar o repositório e configurar o projeto:

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/GilvanPedro/Estoque.git
    cd Estoque/Estoque
    ```

2.  **Compile o projeto com Maven:**
    ```bash
    mvn clean install
    ```
    Este comando irá compilar o código e criar o arquivo JAR executável na pasta `target`.

## 🕹 Como Usar

Após a instalação, você pode executar o sistema diretamente a partir da linha de comando.

1.  **Execute o arquivo JAR:**
    ```bash
    java -jar target/Estoque-1.0-SNAPSHOT.jar
    ```
    *Nota: O nome do arquivo JAR pode variar ligeiramente dependendo da versão do projeto.*

2.  **Interação no Console:**
    O sistema será iniciado no console, onde você será guiado por um menu para realizar as operações de gerenciamento de estoque e usuários.

## 📄 Licença

Este projeto está licenciado sob a Licença MIT. Consulte o arquivo [LICENSE](LICENSE) para mais detalhes.

## 🧑‍💻 Autor

Este projeto foi desenvolvido por [Gilvan Pedro](https://github.com/GilvanPedro).
