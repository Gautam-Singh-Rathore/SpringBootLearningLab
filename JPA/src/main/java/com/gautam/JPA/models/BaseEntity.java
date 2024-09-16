package com.gautam.JPA.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

//@EqualsAndHashCode(callSuper = false)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity  {

    @Id
    @GeneratedValue
    private Integer id;

    private LocalDateTime createdAt ;

    private LocalDateTime lastModifiedAt ;

    private String createdBy ;

    private String lastModifiedBy ;
}
