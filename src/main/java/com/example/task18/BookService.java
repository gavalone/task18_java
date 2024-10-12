package com.example.task18;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired; //Для сбора классов-бинсов
import org.springframework.stereotype.Service; //Для обнаружения зависимостей

@Service
public class BookService {

    @Autowired
    private BookRepository repo;

    public List<Book> ListAll(String keyword) {
        if (keyword != null) {
            return repo.searchBook(keyword);
        }
        return repo.findAll();
    }

    public void save(Book book) {
        repo.save(book);
    }

    public Book get(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
