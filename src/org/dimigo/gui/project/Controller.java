package org.dimigo.gui.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    ComboBox<SearchType> cbSearch;
    @FXML
    TextField txtSearch;
    @FXML
    TableView<Book> bookTableView;
    @FXML
    TableView<Movie> movieTableView;
    @FXML
    Label word;
    @FXML
    Label mean;
    @FXML
    WebView webView;
    @FXML
    Label error_msg;
//    @FXML
//    Button speech;


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // ComboBox 항목 추가
        ObservableList<SearchType> comboItems = FXCollections.observableArrayList();
        comboItems.add(new SearchType("도서", "book"));
        comboItems.add(new SearchType("영화", "movie"));
        comboItems.add(new SearchType("사전", "encyc"));
        cbSearch.setItems(comboItems);
    }

    @FXML
    public void handleSearchAction(ActionEvent event) {
        SearchType item = cbSearch.getSelectionModel().getSelectedItem();
        String type = item.getValue();
        String text = txtSearch.getText();
        System.out.printf("type: %s, text: %s\n", type, text);

        try {
            if ("book".equals(type)) {
                error_msg.setVisible(false);
                word.setVisible(false);
                mean.setVisible(false);
                movieTableView.setVisible(false);
                bookTableView.setVisible(true);
//                speech.setVisible(false);
                List<Book> bookList = naver_encyc_api.getBookList(text);
                System.out.println(bookList);

                ObservableList<Book> data = FXCollections.observableArrayList(bookList);
                bookTableView.setItems(data);
                bindBookTableColumn();
            } else if ("movie".equals(type)) {
                error_msg.setVisible(false);
                word.setVisible(false);
                mean.setVisible(false);
                bookTableView.setVisible(false);
                movieTableView.setVisible(true);
//                speech.setVisible(false);

                List<Movie> movieList = naver_encyc_api.getMovieList(text);
                System.out.println(movieList);

                ObservableList<Movie> data = FXCollections.observableArrayList(movieList);
                movieTableView.setItems(data);
                bindMovieTableColumn();
            } else if ("encyc".equals(type)) {
                error_msg.setVisible(false);
                word.setVisible(true);
                mean.setVisible(true);
                bookTableView.setVisible(false);
                movieTableView.setVisible(false);
//                speech.setVisible(true);

                List<Encyc> encycList = naver_encyc_api.getEncycList(text);

                System.out.println(encycList);

                word.setText(encycList.get(0).getTitle());
                mean.setText(encycList.get(0).getDescription());
                mean.setWrapText(true);

                WebEngine webEngine = webView.getEngine();
                webEngine.load(encycList.get(0).getLink());
            }
        } catch (Exception e) {
            error_msg.setVisible(true);
            error_msg.setText("※ 결과가 없습니다.");
            e.printStackTrace();
        }
    }

    private void bindBookTableColumn() {
        TableColumn title = bookTableView.getColumns().get(0);
        title.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));

        TableColumn author = bookTableView.getColumns().get(1);
        author.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));

        TableColumn price = bookTableView.getColumns().get(2);
        price.setCellValueFactory(new PropertyValueFactory<Book, String>("price"));

        TableColumn link = bookTableView.getColumns().get(3);
        link.setCellValueFactory(new PropertyValueFactory<Book, String>("link"));
    }

    private void bindMovieTableColumn() {
        TableColumn title = movieTableView.getColumns().get(0);
        title.setCellValueFactory(new PropertyValueFactory<Movie, String>("title"));

        TableColumn director = movieTableView.getColumns().get(1);
        director.setCellValueFactory(new PropertyValueFactory<Movie, String>("director"));

        TableColumn pubDate = movieTableView.getColumns().get(2);
        pubDate.setCellValueFactory(new PropertyValueFactory<Movie, String>("pubDate"));

        TableColumn link = movieTableView.getColumns().get(3);
        link.setCellValueFactory(new PropertyValueFactory<Movie, String>("link"));
    }

    @FXML
    public void handleBookAction(Event e) {
        Book book = bookTableView.getSelectionModel().getSelectedItem();
        System.out.println(book);

        WebEngine webEngine = webView.getEngine();
        webEngine.load(book.getLink());
    }

    @FXML
    public void handleMovieAction(Event e) {
        Movie movie = movieTableView.getSelectionModel().getSelectedItem();
        System.out.println(movie);

        WebEngine webEngine = webView.getEngine();
        webEngine.load(movie.getLink());
    }

//    @FXML
//    public void StartSpeech(){
//        String text = txtSearch.getText();
//        try {
//            System.out.println(text);
//            List<Encyc> encycList = naver_encyc_api.getEncycList(text);
//            System.out.println(apiTTS.speech(encycList.get(0).getDescription()));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
