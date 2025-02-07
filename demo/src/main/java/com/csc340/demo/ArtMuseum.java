package com.csc340.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;


public class ArtMuseum {
    public String title;
    public String id;
    public String api_model;
    public String date_display;

    public ArtMuseum(String title, String id, String api_model, String date_display) {
        this.title = title;
        this.id = id;
        this.api_model = api_model;
        this.date_display = date_display;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getApi_model(){
        return api_model;
    }
    public void setApi_model(String api_model){
        this.api_model = api_model;
    }
    public String getDate_display(){
        return date_display;
    }
    public void setDate_display(String date_display){
        this.date_display = date_display;
    }
}
