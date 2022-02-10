package com.bootcamp.blog.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name ="comment")
@NoArgsConstructor
@Setter
@Getter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(unique = true , nullable = false)
    private int id;

    @Column(name = "date")
    private Date date;

    @Column(name = "name")
    private String name;

    @Column(name = "comment")
    private String comment;

    @Column(name = "status")
    private String status;

    @ManyToOne()
    @JoinColumn(name = "post_id", nullable = false)
    @JsonIgnoreProperties(value={"comments","posts", "blogs"})
    private Post post;
}
