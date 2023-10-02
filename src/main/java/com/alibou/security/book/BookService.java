package com.alibou.security.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;

    public void save(BookRequest request) {
        var book = Book.builder()
                .author(request.getAuthor())
                .isbn(request.getIsbn())
                .build();
        repository.save(book);
    }
}
