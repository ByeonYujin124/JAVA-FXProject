package org.dimigo.gui.project;

import javafx.beans.property.SimpleStringProperty;

public class Encyc {
    private SimpleStringProperty title;
    private SimpleStringProperty description;
    private SimpleStringProperty thumbnail;
    private SimpleStringProperty link;

    public Encyc(String title, String description,
                 String thumbnail, String link) {
        this.title = new SimpleStringProperty(title);
        this.description = new SimpleStringProperty(description);
        this.thumbnail = new SimpleStringProperty(thumbnail);
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

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getThumbnail() {
        return thumbnail.get();
    }

    public SimpleStringProperty thumbnailProperty() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail.set(thumbnail);
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
        return "Encyc{" +
                "title=" + title +
                ", description=" + description +
                ", thumbnail=" + thumbnail +
                ", link=" + link +
                '}';
    }
}

