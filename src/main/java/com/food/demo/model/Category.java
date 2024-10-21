package com.food.demo.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "categories")
public class Category {
   @Id
   @Column
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Getter @Setter private Long id;

   @Column
   @Getter @Setter private String name;

   @Column
   @Getter @Setter private String description;

   @OneToMany(mappedBy = "category", orphanRemoval = true)
   @LazyCollection(LazyCollectionOption.FALSE)
   @Getter @Setter private List<Recipe> recipeList;


}
