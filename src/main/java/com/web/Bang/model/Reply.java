package com.web.Bang.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reply implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "reviewId")
    @JsonIgnoreProperties({"replies"})
    private Review reviewId;

    @Lob
    private String content;

    @CreationTimestamp
    private Timestamp creationDate;

    @Override
    public String toString() {
        return "Reply [id=" + id + ", content=" + content + "]";
    }

}
