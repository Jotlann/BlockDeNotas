package com.example.blockdenotas;

public class Notes {

    private String tittle;
    private String Content;

    public Notes(String tittle, String content) {
        this.tittle = tittle;
        Content = content;
    }

    public Notes(){}

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
