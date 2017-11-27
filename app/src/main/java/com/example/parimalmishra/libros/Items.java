package com.example.parimalmishra.libros;

/**
 * Created by sharaddadhich on 27/11/17.
 */

public class Items {

    String extract_data,price,rating,url,from;

    public Items(){}

    public Items(String extract_data, String price, String rating, String url, String from) {
        this.extract_data = extract_data;
        this.price = price;
        this.rating = rating;
        this.url = url;
        this.from = from;
    }

    public String getExtract_data() {
        return extract_data;
    }

    public void setExtract_data(String extract_data) {
        this.extract_data = extract_data;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
