package com.ahmeterdogan.data.entity;

public class Movie {
    private long id;
    private String name;
    private String year;
    private String director;

    public Movie(long id, String name, String year, String director) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.director = director;
    }

    public Movie() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }
}
