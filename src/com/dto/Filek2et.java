package com.dto;

public class Filek2et {
    private int id;
    private String name;
    private String text;
    private String created_at;
    private String updated_at;

    public Filek2et() {
    }

    public Filek2et(int id, String name, String text, String created_at, String updated_at) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Filek2et(String name, String text, String created_at, String updated_at) {
        this.name = name;
        this.text = text;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "File{" + "id=" + id + ", name=" + name + ", text=" + text + ", created_at=" + created_at + ", updated_at=" + updated_at + '}';
    }

}