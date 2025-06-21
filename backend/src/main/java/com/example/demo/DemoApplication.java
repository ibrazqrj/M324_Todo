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
    @GetMapping("/")
    public List<Task> getTasks() {
        return tasks;
    }

    @CrossOrigin
    @PostMapping("/tasks")
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
    @PostMapping("/delete")
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
}
