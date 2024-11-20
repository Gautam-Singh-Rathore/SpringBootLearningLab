package com.gautam.JPA_Tutorial.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

//@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "author")
@NamedQuery(
        name = "Author.findByNamedQuery",
        query = "select a from Author a  where a.age >= :age "
)
@NamedQuery(
        name = "Author.updateByNamedQuery",
        query = "update Author a set a.age = :age"
)
public class Author extends BaseEntity {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    /*@SequenceGenerator(
            name = "author_generator",
            sequenceName = "author_sequence",
            allocationSize = 1
    )*/
    private Integer id;
    /* Here we use Integer wrapper class because the int
     value is by default 0 but the Integer value is by
     default null . This thing is super important.*/

    @Column(
            /*name = "f_name",
            length = 35*/
    )
    private String firstName;

    @Column
    private String lastName;

    @Column(
            unique = true,
            nullable = false
    )
    private String email;

    @Column
    private int age;

/*
    @Column(
            updatable = false
            //nullable = false
    )
    private LocalDateTime createdAt;

    @Column(
            insertable = false
            //nullable = false
    )
    private LocalDateTime lastModified;

 */

    @ManyToMany(mappedBy = "authors")
    private List<Course> courses ;

}
