package com.gautam.JPA.models;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.GenerationType.*;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity
public class Author extends BaseEntity {

    private String firstName;

    private String lastName;

    @Column(
            unique = true ,
            nullable = false
    )
    private String email;

    private int age;

    @ManyToMany(mappedBy = "authors")
    private List<Course> courses ;


}
