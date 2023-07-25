package com.ahmeterdogan.data.entity;

public class Movie {
    private long id;
    private String name;
    private String year;
    private Director director;

    public Movie(long id, String name, String year, Director director) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.director = director;
    }

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

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }
}
