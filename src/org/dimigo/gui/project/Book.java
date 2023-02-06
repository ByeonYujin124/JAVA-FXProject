package org.dimigo.gui.project;

import javafx.beans.property.SimpleStringProperty;

public class Book {

    private SimpleStringProperty title;
    private SimpleStringProperty author;
    private SimpleStringProperty price;
    private SimpleStringProperty link;

    public Book(String title, String author, String price, String link) {
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author);
        this.price = new SimpleStringProperty(price);
        this.link = new SimpleStringProperty(link);
    }

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getAuthor() {
        return author.get();
    }

    public SimpleStringProperty authorProperty() {
        return author;
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }

    public String getPrice() {
        return price.get();
    }

    public SimpleStringProperty priceProperty() {
        return price;
    }

    public void setPrice(String price) {
        this.price.set(price);
    }

    public String getLink() {
        return link.get();
    }

    public SimpleStringProperty linkProperty() {
        return link;
    }

    public void setLink(String link) {
        this.link.set(link);
    }

    @Override
    public String toString() {
        return "Book{" + "title=" + title + ", author=" + author +
                ", price=" + price + ", link=" + link + '}';
    }
}
