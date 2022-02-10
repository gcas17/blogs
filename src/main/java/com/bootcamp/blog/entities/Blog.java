package com.bootcamp.blog.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name ="blog")
@NoArgsConstructor
@Setter
@Getter
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(unique = true , nullable = false)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "url")
    private String url;

    @Column(name = "status")
    private String status;

    @ManyToOne()
    @JsonIgnoreProperties(value={"blogs"})
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "blog", orphanRemoval=true)
    @JsonIgnoreProperties(value={"comments","blog"})
    private List<Post> posts;
}
