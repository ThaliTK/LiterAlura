# 📚 LiterAlura

Bem-vindo ao **LiterAlura**! Este é um projeto de catálogo de livros interativo desenvolvido em Java com Spring Boot. O sistema consome dados de uma API externa e salva as informações em um banco de dados relacional (PostgreSQL), permitindo diversas buscas e filtragens avançadas.

Projeto desenvolvido como parte do desafio do programa **Oracle Next Education (ONE)** em parceria com a **Alura**.

---

## 🚀 Funcionalidades

O sistema funciona através de um menu interativo no console, onde o usuário pode escolher as seguintes opções:

1. **Buscar livro pelo título:** Consome a API [Gutendex](https://gutendex.com/) para buscar o livro e o salva automaticamente no banco de dados.
2. **Listar livros registrados:** Exibe todos os livros que já foram pesquisados e salvos localmente.
3. **Listar autores registrados:** Extrai e exibe os nomes de todos os autores presentes no banco de dados, sem repetições.
4. **Listar livros por idioma:** Filtra os livros salvos no banco de dados pelo idioma escolhido (ex: `pt`, `en`, `es`).
5. **Listar autores vivos em um determinado ano:** Realiza uma busca lógica cruzando o ano de nascimento e falecimento dos autores registrados.

---

## 💻 Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot**
- **Spring Data JPA** (Hibernate)
- **PostgreSQL** (Banco de Dados)
- **Jackson** (Desserialização de JSON)
- **API Gutendex**

---

## 🛠️ Como executar o projeto

1. Clone este repositório:
   ```bash
   git clone [https://github.com/ThaliTK/LiterAlura.git](https://github.com/ThaliTK/LiterAlura.git)
