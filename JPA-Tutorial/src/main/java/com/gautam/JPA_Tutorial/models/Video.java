package com.gautam.JPA_Tutorial.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
//@PrimaryKeyJoinColumn(name="video_id") // Only for the joined strategy
//@DiscriminatorValue("V") // For single table strategy
@Polymorphism(type = PolymorphismType.EXPLICIT) // Used to solve the polymorphic query issue;
public class Video extends Resources {
    private int length;
}
