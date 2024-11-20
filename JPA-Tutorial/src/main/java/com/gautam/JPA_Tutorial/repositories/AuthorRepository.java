package com.gautam.JPA_Tutorial.repositories;

import com.gautam.JPA_Tutorial.models.Author;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author , Integer> {
    // select * from author where first_name = ''
    List<Author> findAllByFirstName(String fn);

    List<Author> findAllByFirstNameIgnoreCase(String fn);

    List<Author> findAllByFirstNameContainingIgnoreCase(String fn);

    List<Author> findAllByFirstNameStartsWithIgnoreCase(String fn);

    List<Author> findAllByFirstNameEndsWithIgnoreCase(String fn);

    List<Author> findAllByFirstNameInIgnoreCase(List<String> firstNames);

    // update Author a set a.age = 22 where a.id=1
    @Modifying
    @Transactional
    @Query("update Author a set a.age = :age where a.id = :id")
    int updateAuthor(int age , int id);

    @Modifying
    @Transactional
    @Query("update Author a set a.age = :age")
    void updateAllAuthorsAges(int age);

    List<Author> findByNamedQuery(@Param("age") int age);

    @Modifying
    @Transactional
    void updateByNamedQuery(@Param("age") int age);


}

/*
In Spring Boot JPA, the annotations `@Query`, `@NamedQuery`, `@Modifying`, and `@Transactional` are used for different purposes in database interaction, particularly when dealing with custom queries and managing transactional behavior. Here's a detailed explanation of each annotation:

---

### 1. **`@Query`**

The `@Query` annotation in Spring Data JPA is used to define custom queries for repository methods. It can be applied to methods in your `@Repository` interface, allowing you to execute JPQL (Java Persistence Query Language) or SQL queries directly. This is particularly useful when the default repository methods like `findById`, `findAll`, or `save` are not sufficient for your use case.

#### Key Features:
- **Custom JPQL or SQL Queries**: You can specify the query in the `@Query` annotation as either JPQL or native SQL. JPQL works with entities, while native SQL works with database tables.
- **Named Parameters**: You can use named parameters (e.g., `:paramName`) within the query and bind method parameters to these named parameters.

#### Example Usage (JPQL):

```java
public interface AuthorRepository extends JpaRepository<Author, Long> {

    // Custom query using JPQL
    @Query("SELECT a FROM Author a WHERE a.name = :name")
    List<Author> findByName(@Param("name") String name);
}
```

#### Example Usage (Native SQL):

```java
public interface AuthorRepository extends JpaRepository<Author, Long> {

    // Custom query using native SQL
    @Query(value = "SELECT * FROM authors WHERE name = :name", nativeQuery = true)
    List<Author> findByName(@Param("name") String name);
}
```

In the above examples:
- `@Param("name")` binds the method parameter to the query's named parameter `:name`.

---

### 2. **`@NamedQuery`**

`@NamedQuery` is an annotation used for defining predefined queries at the entity level. It is typically used to define reusable JPQL queries that can be referenced by name in your repository.

This annotation is placed at the class level in an entity and is often used to avoid repeating query definitions throughout the application. You can then reference the named query using the `@Query` annotation or directly with `EntityManager`.

#### Example Usage:

```java
@Entity
@NamedQuery(
    name = "Author.findByName",
    query = "SELECT a FROM Author a WHERE a.name = :name"
)
public class Author {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    // other fields and methods
}
```

Then, in the repository, you can reference the `@NamedQuery` by its name:

```java
public interface AuthorRepository extends JpaRepository<Author, Long> {

    // Using the named query defined in the entity
    List<Author> findByName(@Param("name") String name);
}
```

#### Key Points:
- **Reusability**: `@NamedQuery` allows queries to be reused across the application without needing to define them in each repository.
- **Separation of concerns**: The query logic is separated from the business logic, which can improve maintainability.

---

### 3. **`@Modifying`**

The `@Modifying` annotation is used to mark a query method that performs a modification operation (such as `INSERT`, `UPDATE`, or `DELETE`) on the database. By default, Spring Data JPA methods are read-only, meaning they are used to retrieve data. When performing write operations (i.e., modifying data), you need to explicitly mark the query method as modifying using `@Modifying`.

#### Key Features:
- **Modifying Queries**: It's used for queries that change the state of the database (e.g., `UPDATE`, `DELETE`).
- **Requires `@Transactional`**: The `@Modifying` annotation is usually used with the `@Transactional` annotation because modifying queries require a transactional context.

#### Example Usage:

```java
public interface AuthorRepository extends JpaRepository<Author, Long> {

    // Update the author's age
    @Modifying
    @Transactional
    @Query("UPDATE Author a SET a.age = :age WHERE a.id = :id")
    int updateAuthorAge(@Param("age") int age, @Param("id") Long id);
}
```

In the above example:
- `@Modifying` tells Spring Data JPA that this is a modification query (not a select query).
- `@Transactional` ensures the update happens within a transaction.

---

### 4. **`@Transactional`**

The `@Transactional` annotation is used to manage the transactional behavior of methods in Spring. It is typically used in service layer methods, and it ensures that a set of operations (such as database interactions) are treated as a single unit of work. If any operation fails within a transaction, the entire transaction is rolled back.

#### Key Features:
- **Transaction Management**: It wraps method execution in a transaction context, so either all changes are committed or none at all if there is an error.
- **Rollback on Exception**: By default, if an exception occurs during the transaction, the changes made within the transaction are rolled back (you can customize which exceptions trigger a rollback).
- **Transactional Propagation**: You can control how transactions propagate between methods (e.g., `REQUIRED`, `REQUIRES_NEW`).

#### Example Usage:

```java
@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Transactional
    public void updateAuthorAge(Long authorId, int newAge) {
        authorRepository.updateAuthorAge(newAge, authorId);
        // additional logic if necessary
    }
}
```

In the example:
- The `@Transactional` annotation ensures that the method `updateAuthorAge` is executed in a transaction.
- If any exception occurs within this method (or any called methods), the changes will be rolled back, meaning no changes will persist in the database.

#### Key Points:
- **Declarative Transaction Management**: You can apply `@Transactional` at the method or class level to define how transactions should behave.
- **Rollback Control**: You can specify which exceptions should trigger a rollback with the `rollbackFor` attribute, for example: `@Transactional(rollbackFor = Exception.class)`.

---

### Summary:

- **`@Query`**: Used to define custom queries (JPQL or SQL) in repository methods.
- **`@NamedQuery`**: Defines reusable queries at the entity level, referenced by name in repository methods.
- **`@Modifying`**: Marks a query as a modification query (e.g., `UPDATE`, `DELETE`).
- **`@Transactional`**: Ensures that the method is executed within a transaction, supporting automatic rollback in case of exceptions.

These annotations are fundamental for working with custom queries and transactions in Spring Boot JPA, enabling more fine-grained control over your database operations.
 */

// Read about transaction here -
/*
A **transactional context** refers to the environment or scope in which a database transaction is managed. In the context of Spring and JPA (Java Persistence API), it ensures that a series of database operations (such as inserts, updates, deletes, or selects) are executed as part of a single unit of work, known as a **transaction**. The transactional context helps manage the consistency, atomicity, and isolation of these operations.

Here’s a detailed breakdown of what a transactional context involves:

---

### Key Concepts of Transactional Context

1. **Transaction Boundaries**:
   A transaction starts when it is initiated (e.g., by calling a method annotated with `@Transactional`) and ends when it is committed or rolled back. The transactional context defines these boundaries.
   - **Start of Transaction**: When a method annotated with `@Transactional` is invoked, a transaction begins.
   - **Commit**: If the method executes successfully without exceptions, the transaction is committed, and all changes made to the database within that transaction are persisted.
   - **Rollback**: If any exception occurs within the transactional method, the transaction is rolled back, and all changes made during the transaction are discarded.

2. **Atomicity**:
   A transaction ensures atomicity, meaning that either all operations within the transaction are completed successfully (commit) or none of them are (rollback). In the context of Spring's transactional behavior, this is often referred to as the **all-or-nothing** principle.

3. **Consistency**:
   A transaction ensures that the database transitions from one valid state to another valid state. It maintains the integrity of the database by ensuring that any changes made by a transaction respect the database constraints (such as foreign key relationships, unique constraints, etc.).

4. **Isolation**:
   Transactions within a transactional context are isolated from one another. This means that the changes made by one transaction are not visible to other transactions until they are committed. The level of isolation can be configured (e.g., **READ_COMMITTED**, **REPEATABLE_READ**, etc.) depending on the needs of the application.

5. **Durability**:
   Once a transaction is committed, the changes are permanent, even in the event of a system failure. Durability ensures that once the transaction is committed, its effects are saved and will not be lost.

---

### How the Transactional Context Works in Spring

In Spring, the transactional context is primarily managed by the **`@Transactional`** annotation and the Spring **transaction manager**. Here’s how it works:

1. **@Transactional Annotation**:
   The `@Transactional` annotation is applied at the method or class level to indicate that the method or all methods within the class should be executed within a transaction. Spring automatically manages the start, commit, and rollback of the transaction based on the execution of the annotated method.

   - **Method-Level Transactions**: If `@Transactional` is applied at the method level, only that method will run within a transaction.
   - **Class-Level Transactions**: If `@Transactional` is applied at the class level, all public methods of the class will run within a transaction.

2. **Propagation**:
   Spring provides several **transaction propagation** options that control how transactions are handled when multiple methods are involved. For example:
   - **REQUIRED** (default): If there's an existing transaction, it will be used; otherwise, a new one will be created.
   - **REQUIRES_NEW**: Always starts a new transaction, suspending any existing transaction.
   - **NESTED**: Starts a nested transaction, where changes can be committed or rolled back independently within the same transaction.

3. **Rollback Rules**:
   By default, Spring rolls back a transaction if a **runtime exception** (`RuntimeException` or its subclasses) or **Error** is thrown. You can customize which exceptions trigger a rollback by using the `rollbackFor` attribute of `@Transactional`.

   ```java
   @Transactional(rollbackFor = Exception.class)
   public void someMethod() {
       // If an Exception is thrown, the transaction will be rolled back.
   }
   ```

4. **Transaction Manager**:
   The transaction manager is responsible for coordinating the transaction. Spring can work with different types of transaction managers depending on the underlying technology (e.g., **DataSourceTransactionManager** for JDBC, **JpaTransactionManager** for JPA/Hibernate). It is responsible for:
   - Beginning and committing transactions
   - Handling rollback
   - Ensuring proper isolation and concurrency control

---

### Example of Using `@Transactional`

```java
@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    // This method will be executed within a transactional context
    @Transactional
    public void updateAuthorName(Long authorId, String newName) {
        Author author = authorRepository.findById(authorId)
                                       .orElseThrow(() -> new RuntimeException("Author not found"));
        author.setName(newName);
        authorRepository.save(author);
    }
}
```

In this example:
- When `updateAuthorName()` is called, Spring begins a transaction.
- If everything in the method executes successfully (i.e., no exception occurs), the transaction is committed, and the changes (updating the author's name) are persisted.
- If an exception occurs (e.g., `RuntimeException`), the transaction will be rolled back, and no changes will be made to the database.

### Key Points:
- **Transactional Context** refers to the environment in which a set of operations are grouped together as a transaction.
- **Atomicity, Consistency, Isolation, and Durability** (ACID) properties ensure the integrity and reliability of the database during a transaction.
- Spring uses **`@Transactional`** to define the boundaries of a transaction.
- Spring manages the transaction lifecycle automatically (begin, commit, rollback), and you can customize behaviors like isolation levels and rollback rules.

---

### Why is the Transactional Context Important?

1. **Consistency in Data**: It ensures that the data remains in a consistent state even in case of failures (e.g., if an exception occurs, the data will not be partially updated).
2. **Concurrency Management**: It helps manage concurrent access to the database, ensuring that transactions are isolated from each other.
3. **Error Handling**: By using the transactional context, you can ensure that all changes are rolled back if something goes wrong, preventing the database from being left in an inconsistent or invalid state.

In summary, a transactional context in Spring ensures that multiple database operations are executed in a controlled, consistent, and reliable manner, safeguarding the integrity of the data and managing exceptions efficiently.

 */
