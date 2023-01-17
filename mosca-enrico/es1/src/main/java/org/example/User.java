package org.example;

public class User{
    private String name;
    private Integer annoNascita;

    public User(String name, Integer annoNascita) {
        this.name = name;
        this.annoNascita = annoNascita;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getAnnoNascita() {
        return annoNascita;
    }
    public void setAnnoNascita(Integer annoNascita) {
        this.annoNascita = annoNascita;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", annoNascita=" + annoNascita +
                '}';
    }
}