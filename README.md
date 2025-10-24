# CatOng API 🐾


API para gerenciamento de uma ONG fictícia que acolhe gatos e cachorros, chamada **CatOng**.  
Desenvolvida com **Java e Spring em Arquitetura limpa**, a API oferece funcionalidades de cadastro, autenticação, listagem, atualização e adoção de animais, além de gerenciamento de usuários.

---

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot**
- **Spring Security** com autenticação via **JWT**
- **JUnit & Mockito** para testes unitários
- **Swagger** para documentação da API
- **Docker** para containerização

---

## 🏗️ Por que Arquitetura Limpa?

A escolha da **Arquitetura Limpa** para este projeto foi intencional, pois ela traz diversos benefícios para o desenvolvimento de software e para meu aprendizado como dev a lidar com esse tipo de arquitetura.

### Benefícios da Arquitetura Limpa:

- **Código mais organizado e legível:** separa responsabilidades de forma clara, facilitando entender e manter o projeto.  
- **Facilidade para testar:** com as dependências bem definidas, é mais simples escrever testes unitários e de integração.  
- **Manutenção simplificada:** alterações em uma parte do sistema têm menos chances de quebrar outras partes.  
- **Flexibilidade:** possibilita trocar tecnologias (banco de dados, frameworks, etc.) sem grandes impactos na lógica principal da aplicação.  
- **Escalabilidade:** o projeto cresce de forma mais estruturada, suportando novos recursos e funcionalidades com menos dor de cabeça.  

> Essa abordagem ajuda a manter o projeto sustentável e profissional, mesmo com o tempo ou a entrada de novos desenvolvedores na equipe.

---

## 🚀 Como Rodar

Para rodar a aplicação certifique-se de ter o **Docker** e o **Git** instalados no seu sistema.  

Abra um terminal na **pasta raiz do projeto** e execute os seguintes comandos:

### Comandos:

```bash
# Build da imagem 
docker-compose build

# Rodar os containers
docker-compose up
```

Para fazer as requisições, você precisa estar autenticado, pra isso existe um **admin** que sempre é registrado na **primeira vez que o projeto roda**.

Acesse `/auth/admin/login` logue com nome "inicial" e senha "Aa@1345" e use o token fornecido pra fazer suas requisições.

Tudo relacionado a esse admin está dentro da pasta `src/main/java/com/arthurhenrique_Dev/CatOng/UsoPessoal`

---

## Endpoints

### 🐶 Cachorros

| Verbo | Endpoint | Descrição | Parâmetros |
|-------|----------|-----------|------------|
| GET | `/pets/cachorros` | Lista todos os cachorros | `pages` (opcional, default=0), `size` (opcional, default=10) |
| GET | `/pets/cachorros/off` | Lista todos os cachorros apagados | `pages` (opcional, default=0), `size` (opcional, default=10) |
| GET | `/pets/cachorros/adotados`| Lista todos os cachorros adotados | `pages` (opcional, default=0), `size` (opcional, default=10)
| GET | `/pets/cachorros/nome/{nome}` | Lista cachorros pelo nome | `nome` (path), `pages`, `size` |
| GET | `/pets/cachorros/id/{id}` | Busca um cachorro pelo ID | `id` (path) |
| POST | `/pets/gerenciamento/salvar/cachorros` | Cadastra um novo cachorro | JSON do cachorro |
| PUT | `/pets/gerenciamento/atualizar_id/{id}/cachorros` | Atualiza um cachorro pelo ID | `id` (path), JSON de atualização |
| DELETE | `/pets/gerenciamento/deletar_id/{id}/cachorros` | Deleta um cachorro pelo ID | `id` (path) |
| DELETE | `/pets/gerenciamento/adoção_id/{id}/cachorros` | Marca um cachorro como adotado | `id` (path) |

---

### 🐱 Gatos

| Verbo | Endpoint | Descrição | Parâmetros |
|-------|----------|-----------|------------|
| GET | `/pets/gatos` | Lista todos os gatos | `pages` (opcional, default=0), `size` (opcional, default=10) |
| GET | `/pets/gatos/off` | Lista todos os gatos apagados | `pages` (opcional, default=0), `size` (opcional, default=10) |
| GET | `/pets/gatos/adotados` | Lista todos os gatos adotados | `pages` (opcional, default=0), `size` (opcional, default=10) |
| GET | `/pets/gatos/nome/{nome}` | Lista gatos pelo nome | `nome` (path), `pages` (opcional, default=0), `size` (opcional, default=10) |
| GET | `/pets/gatos/id/{id}` | Busca um gato pelo ID | `id` (path)|
| POST | `/pets/gerenciamento/salvar/gatos` | Cadastra um novo gato | JSON do gato |
| PUT | `/pets/gerenciamento/atualizar_id/{id}/gatos` | Atualiza um gato pelo ID | `id` (path), JSON de atualização |
| DELETE | `/pets/gerenciamento/deletar_id/{id}/gatos` | Deleta um gato pelo ID | `id` (path) |
| DELETE | `/pets/gerenciamento/adoção_id/{id}/gatos` | Marca um gato como adotado | `id` (path) |

---

### 👤 Usuários

#### Usuário Comum
| Verbo | Endpoint | Descrição | Parâmetros |
|-------|----------|-----------|------------|
| GET | `/user/gerenciamento/comum` | Lista usuários comuns | `pages` (opcional, default=0), `size` (opcional, default=10) |
| GET | `/user/gerenciamento/comum/off` | Lista usuários comuns apagados | `pages` (opcional, default=0), `size` (opcional, default=10) |
| GET | `/user/gerenciamento/comum/nome/{nome}` | Busca usuário por nome | `nome` (path), `pages` (opcional, default=0), `size` (opcional, default=10) |
| GET | `/user/gerenciamento/comum/cpf/{cpf}` | Busca usuário por CPF | `cpf` (path) |
| PUT | `/user/atualizar/{cpf}` | Atualiza usuário comum | `cpf` (path), JSON de atualização |
| DELETE | `/user/gerenciamento/comum/remover_cpf/{cpf}` | Remove usuário | `cpf` (path) |

#### 👤 Usuário de Gerenciamento
| Verbo | Endpoint | Descrição | Parâmetros |
|-------|----------|-----------|------------|
| GET | `/user/gerenciamento/funcionario` | Lista funcionários | `pages` (opcional, default=0), `size` (opcional, default=10 |
| GET | `/user/gerenciamento/funcionario/off` | Lista funcionários apagados | `pages` (opcional, default=0), `size` (opcional, default=10 |
| GET | `/user/gerenciamento/funcionario/nr/{nr}` | Busca funcionário pelo NR | `nr` (path) |
| GET | `/user/gerenciamento/funcionario/cpf/{cpf}` | Busca funcionário pelo cpf | `cpf` (path) |
| PUT | `/user/gerenciamento/funcionario/atualizar_nr/{nr}` | Atualiza funcionário | `nr` (path) , JSON de atualização |
| DELETE | `/user/gerenciamento/funcionario/remover_nr/{nr}` | Remove funcionário | `nr` (path) |

---

### 🔐 Autenticação

| Verbo | Endpoint | Descrição | Parâmetros |
|-------|----------|-----------|------------|
| POST | `/auth/login` | Login usuário comum | JSON `{ cpf, senha }` |
| POST | `/auth/func/login` | Login funcionário | JSON `{ cpf, senha }` |
| POST | `/auth/admin/login` | Login admin | JSON `{ nome, senha }` |
| POST | `/auth/sign_up` | Cadastrar usuário comum | JSON com dados do usuário |
| POST | `/auth/gerenciamento/sign_up` | Cadastrar funcionário | JSON com dados do funcionário |

---
