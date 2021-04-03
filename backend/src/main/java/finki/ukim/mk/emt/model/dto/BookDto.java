package finki.ukim.mk.emt.model.dto;

import finki.ukim.mk.emt.model.Category;
import lombok.Data;

@Data
public class BookDto {

    private String name;
    private Category category;
    private Long author;
    private Integer availableCopies;

    public BookDto(){

    }

    public BookDto(String name, Category category, Long author, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }
}
