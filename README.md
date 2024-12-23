# Exemplo de Conexão com Banco de Dados JDBC

## Descrição
Este projeto demonstra como estabelecer uma conexão com um banco de dados MySQL usando JDBC (Java Database Connectivity)
em uma aplicação Java simples. O código é estruturado de forma a facilitar a compreensão e o uso do JDBC, incluindo tratamento
de erros e carregamento de configurações a partir de um arquivo de propriedades. O exemplo inclui a inserção de dados em uma
tabela de vendedores e a recuperação do ID gerado.

## Estrutura do Projeto


```
src/
├── application/
│   └── Program.java     # Classe principal da aplicação
└── db/
    ├── DB.java          # Classe responsável pela conexão com o banco de dados
    └── DbException.java # Classe de exceção personalizada
database.sql             # Script para criar o banco de dados e a tabela de departamentos
```

## Pré-requisitos

- Java Development Kit (JDK) 8 ou superior.
- MySQL Server instalado e em execução.
- Dependência do MySQL Connector/J no projeto (se estiver usando Maven, conforme indicado abaixo).

## Configuração

1. **Banco de Dados**: Certifique-se de que o servidor MySQL esteja em execução e que o banco de dados `coursejdbc` exista.
Você pode criar o banco de dados usando o seguinte comando SQL:

    ```sql
    CREATE DATABASE coursejdbc;
    ```

2. **Tabela de Vendedores e Departamentos**: Execute o script `database.sql`, que se encontra na raiz do seu diretório do projeto.
Este script cria as tabelas `seller` e `department` necessárias para o funcionamento do exemplo.

3. **Arquivo de Propriedades**: Crie um arquivo chamado `db.properties` na raiz do seu diretório do projeto com o seguinte conteúdo:

    ```properties
    user=root
    password=123456
    dburl=jdbc:mysql://localhost:3306/coursejdbc
    useSSL=false
    ```

   **Obs.**: Substitua os valores de `user` e `password` pelas credenciais do seu banco de dados, se necessário.

4. **Dependências**: Se você estiver usando Maven, adicione a seguinte dependência ao seu arquivo `pom.xml`:

    ```xml
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.23</version> <!-- Verifique se há uma versão mais recente -->
    </dependency>
    ```

## Como Executar

1. Compile o projeto. Se estiver usando um IDE como IntelliJ IDEA ou Eclipse, você pode usar o recurso de compilação integrado.

2. Execute a classe `Program.java`:

    ```bash
    java application.Program
    ```

3. O programa tentará estabelecer uma conexão com o banco de dados, inserir dados na tabela `seller` e exibir o ID do vendedor inserido.

## Estrutura do Código

- **DbException.java**: Classe de exceção personalizada que estende `RuntimeException` para lidar com erros relacionados
ao banco de dados.
- **DB.java**: Classe que contém métodos para obter e fechar a conexão com o banco de dados. Ela carrega as propriedades
de configuração a partir do arquivo `db.properties`.
- **Program.java**: Classe principal que contém o método `main`, onde a conexão com o banco de dados é estabelecida e os
dados são inseridos e exibidos.

## Instruções de Erro

Durante a execução do projeto, você pode encontrar alguns erros comuns. Abaixo estão algumas soluções para problemas frequentemente relatados:

1. **Erro de conexão com o banco de dados**:
    - **Mensagem de erro**: `Communications link failure`
    - **Solução**: Verifique se o servidor MySQL está em execução. Certifique-se de que as credenciais (usuário e senha)
   e a URL do banco de dados no arquivo `db.properties` estão corretas.

2. **Banco de dados não encontrado**:
    - **Mensagem de erro**: `Unknown database 'coursejdbc'`
    - **Solução**: Verifique se o banco de dados `coursejdbc` foi criado. Use o comando SQL fornecido na seção de configuração para criá-lo.

3. **Tabela não encontrada**:
    - **Mensagem de erro**: `Table 'coursejdbc.seller' doesn't exist`
    - **Solução**: Certifique-se de que o script `database.sql` foi executado corretamente e que a tabela `seller` foi criada.

4. **Problemas de dependência**:
    - **Mensagem de erro**: `ClassNotFoundException: com.mysql.cj.jdbc.Driver`
    - **Solução**: Certifique-se de que a dependência do MySQL Connector/J está corretamente adicionada ao seu arquivo 
   `pom.xml` (se estiver usando Maven) e que o projeto foi compilado corretamente.

5. **Erro de configuração SSL**:
    - **Mensagem de erro**: `SSL connection error`
    - **Solução**: Se você não deseja usar SSL, verifique se o parâmetro `useSSL` no arquivo `db.properties` está definido
   como `false`. Se precisar usar SSL, certifique-se de que o MySQL está configurado para suportá-lo.

6. **Outros erros**:
    - **Solução**: Consulte a documentação do JDBC ou do MySQL para obter mais informações sobre a mensagem de erro específica
   que você está enfrentando. Você também pode verificar os logs do MySQL para mais detalhes.

Caso você enfrente um erro que não está listado aqui, sinta-se à vontade para buscar ajuda em fóruns online ou consultar a documentação oficial do JDBC e do MySQL.

## Considerações Finais

Este exemplo fornece uma estrutura básica para conectar-se a um banco de dados MySQL usando JDBC em Java. Ele demonstra 
como inserir dados em uma tabela e pode ser expandido para incluir operações de banco de dados mais complexas, como atualizações e exclusões.


#### Melhorias:

* Substituir chamadas diretas para `printStackTrace()` por uma abordagem de logging mais robusta é uma prática recomendada
para aplicações que precisam de um gerenciamento eficaz de erros. Usar uma biblioteca de logging permite que você registre
mensagens em diferentes níveis (como DEBUG, INFO, WARN, ERROR) e também envie essas mensagens para diferentes destinos 
(como arquivos, consoles ou sistemas de monitoramento).

**Usando a Biblioteca de Logging SLF4J com Logback**
- Uma das combinações populares para logging em Java é o SLF4J (Simple Logging Facade for Java) junto com Logback como a
implementação de logging. Aqui está como você pode implementar isso:

1. **Adicione as Dependências ao seu `pom.xml`**:
   Se você estiver usando Maven, adicione as seguintes dependências ao seu `pom.xml`:
    
```xml
<dependencies>
    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-api</artifactId>
        <version>1.7.30</version> <!-- Verifique se há uma versão mais recente -->
    </dependency>
    <dependency>
        <groupId>ch.qos.logback</groupId>
        <artifactId>logback-classic</artifactId>
        <version>1.2.3</version> <!-- Verifique se há uma versão mais recente -->
    </dependency>
</dependencies>
```
   2. **Crie um Arquivo de Configuração para Logback**:
      Crie um arquivo chamado `logback.xml `no diretório `src/main/resources `para configurar o Logback. Um exemplo simples de configuração pode ser:

```xml
       <configuration>
            <appender name="console" class="ch.qos.logback.core.ConsoleAppender">
                <encoder>
                    <pattern>%d{yyyy-MM-dd HH:mm:ss} %-5level %logger{36} - %msg%n</pattern>
                </encoder>
            </appender>

            <root level="debug">
                <appender-ref ref="console"/>
            </root>
        </configuration>
```
3. **Modifique a Classe `Program` para Usar o Logger:**
   Aqui está como você pode modificar a classe `Program` para usar SLF4J em vez de `printStackTrace()`:

```java
package application;

import db.DB;
import org.slf4j.Logger; // Importa a classe Logger
import org.slf4j.LoggerFactory; // Importa a classe LoggerFactory

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program {

    private static final Logger logger = LoggerFactory.getLogger(Program.class); // Cria um logger para a classe

    public static void main(String[] args) {
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = DB.getConnection();

            // EXEMPLO 1: Inserção de um vendedor
            st = conn.prepareStatement(
                    "INSERT INTO seller "
                            + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
                            + "VALUES "
                            + "(?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1, "Carl Purple");
            st.setString(2, "carl@gmail.com");
            st.setDate(3, new java.sql.Date(sdf.parse("22/04/1985").getTime()));
            st.setDouble(4, 3000.0);
            st.setInt(5, 4);

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    System.out.println("Done! Id: " + id);
                }
            } else {
                System.out.println("No rows affected!");
            }
        } catch (SQLException | ParseException e) {
            // Substitui printStackTrace por um log de erro
            logger.error("Error while inserting seller", e); // Registra o erro com a stack trace
        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }
}
```
**Benefícios do Logging**

- **Níveis de Log**: Você pode categorizar mensagens como DEBUG, INFO, WARN, ERROR, etc., o que ajuda a diferenciar a severidade dos eventos.
- **Configuração Flexível**: Você pode direcionar logs para diferentes saídas (console, arquivos, servidores de logs) e configurar diferentes formatos.
- **Desempenho**: Bibliotecas de logging podem ser configuradas para registrar apenas mensagens de um certo nível em ambientes de produção, reduzindo a sobrecarga.
- **Manutenção**: Facilita a manutenção e a análise de problemas em produção, pois você pode ter um histórico de logs.

Usar uma biblioteca de logging é uma prática recomendada que melhora a qualidade e a manutenção do código.

##### Observação:

É possível implementar um sistema de logging mais robusto em Java sem depender de bibliotecas externas como SLF4J ou Logback.
Você pode usar a classe `java.util.logging`, que já faz parte da biblioteca padrão do Java, para registrar mensagens de log.
Essa classe oferece funcionalidades básicas de logging e pode ser configurada de várias maneiras.

Aqui está um exemplo de como você pode usar a API de logging padrão do Java:

**Usando `java.util.logging`**:

1. **Importe as Classes Necessárias:**
   No seu código, você deve importar as classes de logging necessárias:

```java
import java.util.logging.Level; // Importa o nível de log
import java.util.logging.Logger; // Importa a classe Logger
import java.util.logging.SimpleFormatter; // Importa o formatador simples para logs
import java.util.logging.FileHandler; // Importa o manipulador de arquivos para logs
```

2. **Configurar o Logger**:
   Você pode configurar o logger para registrar mensagens em um arquivo ou no console. Aqui está um exemplo que registra mensagens em um arquivo de log:

```java
import java.util.logging.FileHandler; // Importa o manipulador de arquivos para logs
import java.util.logging.Logger; // Importa a classe Logger
import java.util.logging.SimpleFormatter; // Importa o formatador simples para logs
import java.util.logging.Level; // Importa o nível de log
import java.sql.*; // Importa classes para manipulação de SQL
import java.text.SimpleDateFormat; // Importa a classe para formatação de datas
import java.text.ParseException; // Importa a classe para tratamento de exceções de formato

public class Program {

    private static final Logger logger = Logger.getLogger(Program.class.getName()); // Cria um logger para a classe

    public static void main(String[] args) {
        
        // Configuração do logger
        try {
            FileHandler fileHandler = new FileHandler("application.log", true); // Cria um manipulador de arquivos para logs
            SimpleFormatter formatter = new SimpleFormatter(); // Cria um formatador simples
            fileHandler.setFormatter(formatter); // Define o formatador para o manipulador de arquivos
            logger.addHandler(fileHandler); // Adiciona o manipulador de arquivos ao logger
        } catch (Exception e) {
            e.printStackTrace(); // Imprime a stack trace caso ocorra um erro na configuração do logger
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Connection conn = null; // Inicializa a conexão como nula
        PreparedStatement st = null; // Inicializa o PreparedStatement como nulo
        
        try {
            // Obtém a conexão com o banco de dados
            conn = DB.getConnection();

            // EXEMPLO 1: Inserção de um vendedor
            st = conn.prepareStatement(
                    "INSERT INTO seller "
                            + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
                            + "VALUES "
                            + "(?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS); // Solicita a geração de chaves

            // Define os valores a serem inseridos
            st.setString(1, "Carl Purple");
            st.setString(2, "carl@gmail.com");
            st.setDate(3, new java.sql.Date(sdf.parse("22/04/1985").getTime())); // Converte a data para o formato SQL
            st.setDouble(4, 3000.0);
            st.setInt(5, 4);

            // Executa a atualização (inserção)
            int rowsAffected = st.executeUpdate();

            // Verifica se alguma linha foi afetada pela inserção
            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys(); // Obtém as chaves geradas
                while (rs.next()) {
                    int id = rs.getInt(1); // Recupera o ID gerado
                    System.out.println("Done! Id: " + id); // Exibe o ID
                }
            } else {
                logger.warning("No rows affected!"); // Log de aviso se nenhuma linha for afetada
            }
        } catch (SQLException | ParseException e) {
            // Substitui printStackTrace por um log de erro
            logger.log(Level.SEVERE, "Error while inserting seller", e); // Registra o erro com a stack trace
        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }
}
```

**Explicação do Código***
- **Logger**: Criamos um logger associado à classe Program usando Logger.getLogger().
- **FileHandler**: Usamos FileHandler para gravar logs em um arquivo chamado application.log.
- **Formatter**: Usamos SimpleFormatter para formatar as mensagens de log de uma maneira simples.
- **Níveis de Log**: Utilizamos logger.warning() e logger.log(Level.SEVERE, ...) para registrar avisos e erros, respectivamente.

**Vantagens**
- **Sem Dependências Externas**: Utiliza apenas a biblioteca padrão do Java.
- **Configuração Flexível**: Você pode facilmente ajustar o formato e o destino dos logs.
- **Níveis de Log**: Permite diferentes níveis de severidade para os logs (INFO, WARNING, SEVERE, etc.).