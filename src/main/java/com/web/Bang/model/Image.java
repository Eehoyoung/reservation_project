package com.web.Bang.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String imageUrl;

    private String originFileName;

    @CreationTimestamp
    private Timestamp creationDate;

    public Image(Object[] obj) {
        this.id = ((Integer) obj[0]).intValue();
        this.imageUrl = (String) obj[1];
        this.originFileName = (String) obj[2];
        this.creationDate = (Timestamp) obj[3];
    }

}
