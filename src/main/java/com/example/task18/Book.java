package com.example.task18;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Entity
public class Book {
    private Long ID;
    @Getter
    private String BookName;
    @Getter
    private String Publishing;
    @Getter
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date DateOfIssue;
    @Getter
    private String StudentName;
    @Getter
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date DateOfReturn;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getID() {
        return ID;
    }
}
