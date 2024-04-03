package com.example.bookingplatform.book;

import com.example.bookingplatform.book.dto.BookCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    @PostMapping
    public String createBook(@ModelAttribute BookCreateDto createDto) {
        bookService.create(createDto);
        return "redirect:/";
    }
}
