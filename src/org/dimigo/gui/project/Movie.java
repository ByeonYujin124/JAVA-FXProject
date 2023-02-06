package org.dimigo.gui.project;

import javafx.beans.property.SimpleStringProperty;

public class Movie {

    private SimpleStringProperty title;
    private SimpleStringProperty director;
    private SimpleStringProperty pubDate;
    private SimpleStringProperty link;

    public Movie(String title, String director, String pubDate, String link) {
        this.title = new SimpleStringProperty(title);
        this.director = new SimpleStringProperty(director);
        this.pubDate = new SimpleStringProperty(pubDate);
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

    public String getDirector() {
        return director.get();
    }

    public SimpleStringProperty directorProperty() {
        return director;
    }

    public void setDirector(String director) {
        this.director.set(director);
    }

    public String getPubDate() {
        return pubDate.get();
    }

    public SimpleStringProperty pubDateProperty() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate.set(pubDate);
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
        return "Movie{" + "title=" + title + ", director=" + director +
                ", pubDate=" + pubDate + ", link=" + link + '}';
    }
}