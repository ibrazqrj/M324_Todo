## Einführung

Die Versionierung von REST-APIs ist entscheidend, um Änderungen an Schnittstellen vorzunehmen, ohne bestehende Clients zu beeinträchtigen. Besonders in größeren Projekten oder bei produktiven APIs muss sichergestellt werden, dass verschiedene Versionen parallel betrieben werden können.

---

## Übersicht der Versionierungsmethoden

|Methode|Beispiel|
|---|---|
|URI-Versionierung|`/api/v1/tasks`|
|Header-Versionierung|`Accept: application/vnd.myapp.v1+json`|
|Parameter-Versionierung|`/api/tasks?version=1`|
|Content-Negotiation|über MIME-Typen im `Accept` Header|

---

## Bewertung der Methoden

| Kriterium      | URI-Versionierung | Header-Versionierung    | Parameter-Versionierung |
| -------------- | ----------------- | ----------------------- | ----------------------- |
| Einfachheit    | sehr einfach      | erfordert mehr Code     | einfach                 |
| Lesbarkeit     | klar erkennbar    | nicht sichtbar          | schwer sichtbar         |
| Flexibilität   | begrenzt          | hoch                    | begrenzt                |
| Client-Support | universal         | teilweise problematisch | universal               |

---

## Begründung der Wahl

Wir haben uns für **URI-Versionierung** entschieden, weil sie:

- einfach zu implementieren ist,
- sofort erkennbar ist,
- keine speziellen Header oder Middleware benötigt,
- besonders für kleinere oder Schülerprojekte geeignet ist.

---

## Schritt-für-Schritt-Anleitung zur Implementierung (Einfache Beispiele)

### 1. Neuen Controller mit Versionspfad erstellen

```java
@RestController
@RequestMapping("/api/v1/tasks")
public class TaskControllerV1 {

    @GetMapping
    public List<Task> getAll() {
        return List.of(new Task("Test-V1"));
    }
}
```

### 2. Bestehenden Controller mit neuer Version versehen

```java
@RestController
@RequestMapping("/api/v2/tasks")
public class TaskControllerV2 {
    // neue Logik, z. B. mit Timestamp oder Sortierung
}
```

### 3. Frontend-Aufruf anpassen

```js
fetch("http://localhost:8080/api/v1/tasks")
```

---

## Zusammenfassung und Fazit

Die gewählte Methode (URI-Versionierung) bietet eine einfache, gut sichtbare und wartbare Möglichkeit zur Versionierung der API. Sie ist besonders für Entwicklungs- und Ausbildungsprojekte geeignet. Für komplexere Anforderungen wäre eine Header-basierte Versionierung zu empfehlen.