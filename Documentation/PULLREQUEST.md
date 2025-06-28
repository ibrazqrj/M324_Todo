## 1. Funktionsweise von Pull Requests

Ein **Pull Request (PR)** ist ein Vorschlag, Änderungen in einem Git-Repository zu übernehmen. Entwickler arbeiten in einem separaten Branch (z. B. `feature/watchlist`) und eröffnen anschließend einen PR, um die Änderungen in den `main`-Branch zu mergen.

Pull Requests ermöglichen:

- Code-Review durch andere Teammitglieder
- Automatische Tests vor dem Merge
- Nachvollziehbare Dokumentation der Änderungen

---

## 2. Umsetzung im Projekt (GitHub)

Wir nutzen GitHub für Versionskontrolle. So wurde der PR umgesetzt:

1. Branch `feature/watchlist` erstellt und gepusht
2. Auf GitHub automatisch Button „Compare & pull request“ angezeigt
3. PR erstellt mit Titel und Beschreibung
4. Änderungen überprüft und anschließend gemergt (Merge-Button)

---

## 3. Durchführung im Projekt

Im Branch `feature/watchlist` wurde folgendes umgesetzt:

- Neue UI-Logik mit ⭐-Button zum Markieren von Aufgaben
- Erweiterung des `Task`-Objekts mit `watched: boolean`
- Neuer Spring-Endpunkt `/watch`

**Pull Request:** wurde erstellt und gemerged in `main` nach Review.

---

## 4. Notwendige Schritte

Damit der PR erfolgreich durchgeführt werden konnte:

#### Feature Branch erstellen und pushen
![Step 1](https://i.imgur.com/O66Rrwc.png)

#### Pull Request in der Repo prüfen
![Step 2](https://i.imgur.com/KqZ2OgR.png)

#### Pull Request Details angeben und überprüfen
![Step 3](https://i.imgur.com/G0awkff.png)

#### Auf die Tests warten und mergen
![Step 4](https://i.imgur.com/jTIEOVa.png)

---

## 5. Diskussion: Nutzen von Pull Requests

**Vorteile:**

- Codequalität steigt durch Review
- Fehler werden früh erkannt
- Zusammenarbeit im Team wird strukturiert

**Herausforderungen:**

- Kann Aufwand bedeuten (besonders bei kleinen Teams)
- Verzögerung möglich, wenn niemand reviewed

---

## Eigene Meinung

> Pull Requests helfen uns, disziplinierter zu arbeiten und jeden Schritt dokumentiert zu halten. Auch als Einzelperson ist es praktisch, Änderungen klar zu strukturieren und nachvollziehbar zu versionieren. Die Möglichkeit, Feedback einzuholen, ist besonders im Team hilfreich.

---