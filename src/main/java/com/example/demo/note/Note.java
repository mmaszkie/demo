package com.example.demo.note;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("notes")
public class Note {

    @Id
    private String id;

    private String text;
    private String author;

    protected Note() {
    }

    public Note(String text, String author) {
        this.text = text;
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getAuthor() {
        return author;
    }
}
