# Gerenciador de Contatos

Este é um projeto simples de Gerenciador de Contatos desenvolvido em Java, utilizando Swing para a interface gráfica e SQLite para o banco de dados.
Feito para servir de exemplo para turma de POO.

## Funcionalidades

- Adicionar um novo contato
- Editar um contato existente
- Excluir um contato
- Listar todos os contatos

## Tecnologias Utilizadas

- Java
- Swing (para a interface gráfica)
- SQLite (para o banco de dados)

## Como Executar o Projeto

### Pré-requisitos

- JDK 8 ou superior
- SQLite

### Passos para Execução

1. Clone o repositório:
   ```sh
   git clone https://github.com/professorRaphael/java_crud_gui_awt.git
    ```
2. Navegue até o diretório do projeto:
    ```
   cd java_crud_gui_awt
   ```
3. Compile o projeto:
   ```
   javac -d bin src/com/aula2024/*.java
   ```
4. Execute o projeto:
    ```
   java -cp bin com.aula2024.Main
   ```
   
### Estrutura do Projeto
```
├── src
│   └── com
│       └── aula2024
│           ├── ContatosGUI.java
│           ├── Contato.java
│           ├── ContatoDAO.java
│           └── Main.java
├── pom.xml
├── README.md
└── LICENSE

```

### Contribuição
- Fork o projeto
- Crie uma branch para sua feature (git checkout -b feature/sua-feature)
- Commit suas alterações (git commit -am 'Adiciona nova feature')
- Push para a branch (git push origin feature/sua-feature)
- Abra um Pull Request