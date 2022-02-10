package com.bootcamp.blog.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name ="post")
@NoArgsConstructor
@Setter
@Getter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(unique = true , nullable = false)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "date")
    private Date date;

    @Column(name = "status")
    private String status;

    @Column(name = "content")
    private String content;

    @ManyToOne()
    @JoinColumn(name = "blog_id", nullable = false)
    @JsonIgnoreProperties(value="posts")
    private Blog blog;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post", orphanRemoval=true)
    @JsonIgnoreProperties(value={"post"})
    private List<Comment> comments;
}
