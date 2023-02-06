package org.dimigo.gui.project;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class naver_encyc_api {
    private static final String CLIENT_ID = "D9QwfHafn8_6mnbFIY9O";
    private static final String CLIENT_SECRET = "CrwYytVRGb";

    @SuppressWarnings("unchecked")
    public static List<Book> getBookList(String keyword) throws Exception {
        String response = search("book", keyword);

        // JOSN 데이터 파싱하여 List에 추가하기
        List<Book> result = new ArrayList<>();
        Map<String, Object> map = new ObjectMapper().readValue(response, Map.class);

        List<Map<String, String>> list = (List<Map<String, String>>) map.get("items");

        for (Map<String, String> book : list) {
            String title = book.get("title").replaceAll("\\<.*?>", "");
            String author = book.get("author");
            String price = book.get("price");
            String link = book.get("link");
            result.add(new Book(title, author, price, link));
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    public static List<Movie> getMovieList(String keyword) throws Exception {
        String response = search("movie", keyword);

        // JOSN 데이터 파싱하여 List에 추가하기
        List<Movie> result = new ArrayList<>();
        Map<String, Object> map = new ObjectMapper().readValue(response, Map.class);

        List<Map<String, String>> list = (List<Map<String, String>>) map.get("items");

        for (Map<String, String> movie : list) {
            String title = movie.get("title").replaceAll("\\<.*?>", "");
            String director = movie.get("director");
            String pubDate = movie.get("pubDate");
            String link = movie.get("link");
            result.add(new Movie(title, director, pubDate, link));
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    public static List<Encyc> getEncycList(String keyword) throws Exception {
        String response = search("encyc", keyword);

        // JOSN 데이터 파싱하여 List에 추가하기
        List<Encyc> result = new ArrayList<>();
        Map<String, Object> map = new ObjectMapper().readValue(response, Map.class);

        List<Map<String, String>> list = (List<Map<String, String>>) map.get("items");

        for (Map<String, String> encyc : list) {
            String title = encyc.get("title").replaceAll("\\<.*?>", "");
            String description = encyc.get("description");
            String thumbnail = encyc.get("thumbnail");
            String link = encyc.get("link");
            result.add(new Encyc(title, description, thumbnail, link));
        }

        return result;
    }

    private static String search(String type, String search) throws Exception {
        BufferedReader br = null;
        try {
            String text = URLEncoder.encode(search, "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/search/" + type + "?query=" + text;

            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", CLIENT_ID);
            con.setRequestProperty("X-Naver-Client-Secret", CLIENT_SECRET);

            int responseCode = con.getResponseCode();

            if (responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }

            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            return response.toString();
        } catch (Exception e) {
            throw e;
        } finally {
            br.close();
        }
    }
}
