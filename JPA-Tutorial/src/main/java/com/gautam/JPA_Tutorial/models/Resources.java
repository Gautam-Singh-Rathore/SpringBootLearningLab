package com.gautam.JPA_Tutorial.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@DiscriminatorColumn(name = "resource_type") // For single table strategy
public class Resources {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private int size;

    private String url;

    @OneToOne
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

}
/*
The error message you're encountering suggests that there is an issue with the combination of the `@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)` strategy and the use of `GenerationType.IDENTITY` for the primary key generation.

### Root Cause:
The `GenerationType.IDENTITY` strategy relies on the underlying database generating the primary key value automatically, typically using an auto-increment column. However, when using the `@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)` strategy, Hibernate generates a separate table for each subclass of the `Resources` entity. This can lead to issues with key generation, as the identity column approach may not be compatible with this inheritance strategy.

### Solution:
You have a couple of options to resolve this issue:

1. **Change the Inheritance Strategy:**
   If you don't specifically require the `TABLE_PER_CLASS` strategy, consider changing the inheritance strategy to `SINGLE_TABLE` or `JOINED`, which are generally more compatible with `GenerationType.IDENTITY`.

   - **SINGLE_TABLE**: All entities in the inheritance hierarchy are mapped to a single table.
   - **JOINED**: Each subclass has its own table, but the base class and subclass tables are joined on the primary key.

   Example of `SINGLE_TABLE` strategy:
   ```java
   @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
   @DiscriminatorColumn(name = "resource_type")
   ```

   Example of `JOINED` strategy:
   ```java
   @Inheritance(strategy = InheritanceType.JOINED)
   ```

2. **Use `GenerationType.SEQUENCE` or `GenerationType.AUTO`:**
   If you must keep the `TABLE_PER_CLASS` strategy, consider using `GenerationType.SEQUENCE` (if your database supports sequences) or `GenerationType.AUTO`, which allows Hibernate to determine the best strategy for generating IDs, depending on the underlying database.

   Example of using `GenerationType.SEQUENCE`:
   ```java
   @GeneratedValue(strategy = GenerationType.SEQUENCE)
   private Integer id;
   ```

   Example of using `GenerationType.AUTO`:
   ```java
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Integer id;
   ```

3. **Check the Database Schema:**
   If you're sticking with `GenerationType.IDENTITY`, ensure that your database schema supports auto-increment behavior in the way Hibernate expects, and verify that the database tables are set up correctly to handle identity columns.

### Updated Code Example:
Here’s an updated example using `SINGLE_TABLE` strategy and `GenerationType.AUTO`:

```java
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "resource_type")  // Optional, only needed if you want to differentiate types in the single table
public class Resources {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private int size;
    private String url;

    @OneToOne
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;
}
```

This should help resolve the issue, assuming the change in inheritance strategy aligns with your requirements. If you need to stick with `TABLE_PER_CLASS` for a specific reason, consider switching the ID generation strategy to `SEQUENCE` or `AUTO`.
 */
