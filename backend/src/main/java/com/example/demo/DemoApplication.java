package com.example.demo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Demo REST-API für einfache ToDo-Tasks ohne Datenbank.
 * Endpunkte:
 * - GET /: Liefert alle Aufgaben
 * - POST /tasks: Fügt eine neue Aufgabe hinzu
 * - POST /delete: Löscht eine Aufgabe anhand der Beschreibung
 */
@RestController
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    private final List<Task> tasks = new ArrayList<>();
    private final ObjectMapper mapper = new ObjectMapper();

    @CrossOrigin
    @GetMapping("/api/v1/")
    public List<Task> getTasks() {
        return tasks;
    }

    @CrossOrigin
    @PostMapping("/api/v1/tasks")
    public String addTask(@RequestBody String taskJson) {
        try {
            Task task = mapper.readValue(taskJson, Task.class);

            // NEU: Leere Beschreibung verhindern
            if (task.getTaskdescription() == null || task.getTaskdescription().isBlank()) {
                return "redirect:/";
            }

            // Duplikate vermeiden
            if (tasks.stream().anyMatch(t -> t.getTaskdescription().equals(task.getTaskdescription()))) {
                return "redirect:/";
            }

            tasks.add(task);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "redirect:/";
    }

    @CrossOrigin
    @PostMapping("/api/v1/delete")
    public String deleteTask(@RequestBody String taskJson) {
        try {
            Task task = mapper.readValue(taskJson, Task.class);

            // Aufgabe mit gleicher Beschreibung finden und entfernen
            Iterator<Task> iterator = tasks.iterator();
            while (iterator.hasNext()) {
                if (iterator.next().getTaskdescription().equals(task.getTaskdescription())) {
                    iterator.remove();
                    break;
                }
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "redirect:/";
    }

    @CrossOrigin
    @PostMapping("/api/v2/delete")
    public String deleteTaskV2(@RequestBody String taskJson) {
        try {
            Task task = mapper.readValue(taskJson, Task.class);

            boolean removed = tasks.removeIf(t -> t.getTaskdescription().equals(task.getTaskdescription()));

            // NEU: Deutliche Rückmeldung in v2 (z. B. für Frontend-Toast oder Logging)
            if (removed) {
                return "Task erfolgreich gelöscht (v2)";
            } else {
                return "Task nicht gefunden (v2)";
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "Fehler beim Verarbeiten der Anfrage (v2)";
        }
    }

    @CrossOrigin
    @PostMapping("/api/v1/watch")
    public String toggleWatch(@RequestBody String taskJson) {
        try {
            Task incoming = mapper.readValue(taskJson, Task.class);
            for (Task task : tasks) {
                if (task.getTaskdescription().equals(incoming.getTaskdescription())) {
                    task.setWatched(incoming.isWatched());
                    break;
                }
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }
}
