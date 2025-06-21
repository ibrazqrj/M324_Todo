package com.example.demo;

/**
 * Repräsentiert eine einfache Aufgabe mit Beschreibung und Erfassungsdatum.
 * Muss exakt mit den JSON-Eigenschaften übereinstimmen!
 */
public class Task {

    // Beschreibung der Aufgabe – wird vom Client (React) übergeben
    private String taskdescription;

    // Erfassungsdatum als String
    private String createdAt;

    public Task() {
        // Leerer Konstruktor für Jackson
    }

    public String getTaskdescription() {
        return taskdescription;
    }

    public void setTaskdescription(String taskdescription) {
        this.taskdescription = taskdescription;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
