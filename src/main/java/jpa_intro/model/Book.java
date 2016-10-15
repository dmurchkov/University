package jpa_intro.model;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book extends IdEntity{
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BookType type;

    @Column
    private long price;

    public Book() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BookType getType() {
        return type;
    }

    public void setType(BookType type) {
        this.type = type;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public enum BookType {
        IT, NOVEL, PSYCHOLOGY;
    }

}


