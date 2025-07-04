# Loja de Discos - Sistema de Cadastro e Vendas

## Descrição

Este projeto implementa um sistema simples para gerenciamento de uma loja de discos. O sistema permite:

- Cadastro de clientes.
- Cadastro de discos (produtos) com informações como título, artista, gênero, preço e estoque.
- Realização de vendas associadas a clientes, incluindo controle de estoque e itens vendidos.

O sistema é desenvolvido em Java com interface gráfica usando Swing e um padrão simples de acesso a dados (DAO).

---

## Funcionalidades

- **Cadastro de Clientes**  
  Cadastro com nome, e-mail e telefone.  
  Listagem de clientes cadastrados.

- **Cadastro de Discos**  
  Cadastro de discos com título, artista, gênero, preço e quantidade em estoque.  
  Listagem de discos cadastrados.

- **Realizar Venda**  
  Seleção de cliente e discos para adicionar ao carrinho.  
  Controle da quantidade vendida e atualização automática do estoque.  
  Registro da venda com data e itens.

- **Navegação entre telas**  
  Troca fácil entre telas de cadastro e venda.

---

## Tecnologias Utilizadas

- Java 
- Swing 
- DAO para acesso a dados (pode ser adaptado para banco de dados real)
- Estrutura orientada a objetos com uso de herança e polimorfismo para telas
- Hibernate para a conexão com Banco de Dados MySQL

---

## Como Executar

1. Clone este repositório para dentro de uma pasta com o nome que desejar, então use alguma IDE (IntelliJ IDEA, NetBeans, Eclipse) que tenha compatibilidade com o Java:


```bash
git clone https://github.com/henrique-zz/lojadediscos.git
```

OBS: Caso deseje utilizar a internacionalização para o inglês e espanhol, basta utilizar os comandos:

```bash
mvn clean package
```
   
```bash
java -jar target/nomedoseuarquivo.jar en US (Para Inglês)
```
```bash
java -jar target/nomedoseuarquivo.jar es ES (Para Espanhol)
```

2. Baixe e instale o MySQL Workbench, crie uma Database chamada "lojadediscos", e dentro do arquivo "hibernate.cfg.xml" dentro da linha
```bash
<property name="hibernate.connection.password"></property>
```
e coloque a senha do seu banco, para funcionar corretamente.

---

## To Do

1. Uma interface melhor, com cores, botões com melhor organização.
   
2. Fazer esta interface de forma Web, apenas conectando toda a lógica em Java com o front-end.  

