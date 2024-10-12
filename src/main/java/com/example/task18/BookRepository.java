package com.example.task18;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT p FROM Book p WHERE CONCAT(p.bookName, '', p.publishing, '', p.dateOfIssue, '', p.studentName, '', p.dateOfReturn) LIKE %?1%")
    List<Book> searchBook(String keyword);
}
