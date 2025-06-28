## 1. Vergleich der Strategien

|Kriterium|Git Flow|GitHub Flow|Feature Branching|Trunk-based Development|
|---|---|---|---|---|
|**Hauptbranch**|`main` + `develop`|nur `main`|`main` + Feature-Branches|nur `main`|
|**Geeignet für**|komplexe Releases, große Teams|schnelle Deployments|klare Feature-Trennung|Continuous Integration/Delivery|
|**Release-Handling**|eigene `release`-Branches|über Pull Requests in `main`|per Merge in `main`|direkt in `main`, max. 1–2 Tage Branches|
|**Hotfix-Strategie**|`hotfix`-Branches|direkte Fixes in `main` via PR|Feature-Branches können genutzt werden|Fixes direkt im `main`|
|**CI/CD-Kompatibilität**|gut, aber komplex|sehr gut|gut|hervorragend|
|**Lernkurve**|hoch|niedrig|niedrig-mittel|mittel|

## 2. Meine Wahl: GitHub Flow

Ich habe mich für **GitHub Flow** entschieden, da das Projekt relativ überschaubar ist, schnell neue Funktionen bekommen soll und wir mit automatischen Tests und Pull Requests gut die Codequalität sichern können. Die Einfachheit passt gut zu einem kleinen Schulprojekt.

## 3. Umsetzung im Projekt

- **Hauptbranch**: `main`
    
- **Erstellte Feature-Branches:**
    - `feature/watchlist`: Neue Watchlist-Funktion

- **Vorgehen**:
    - Branch erstellt
    - Änderung lokal gemacht
    - Commit und Push
    - Pull Request geöffnet
    - Automatische Tests über GitHub Actions laufen lassen
    - Merge nur bei grünen Tests

## 4. Workflow-Regeln

- Branch wird über einen **Pull Request** in `main` gemerged
- Tests (Vitest oder Maven) müssen **erfolgreich durchlaufen**
- Kein Direktpush nach `main`
- Commit-Nachrichten klar und sinnvoll formuliert