# Testfälle

## Manuelle Akzeptanztests

| Nr. | Aktion | Erwartetes Ergebnis               | Tatsächliches Ergebnis |
|---|---|-----------------------------------|---|
| 1 | `enter` mit gültigen Daten | User Story angelegt               | User Story angelegt
| 2 | `enter` mit leerem Titel | Fehlermeldung, nichts angelegt    | Fehler: Der Titel darf nicht leer sein!
| 3 | `enter` mit Risiko = 4 | Ungültig        | Fehler: null
| 4 | `dump` bei leerer Liste | Tabelle ohne Einträge | Leere Liste
| 5 | `dump` mit 3 Stories | Absteigend nach Prio sortiert     | 3 Stories absteigend nach Prio sortiert
| 6 | `store`, Neustart, `load`, `dump` | Stories sind wieder da            | Stories wieder da
| 7 | `load`, dann `enter` | Neue ID kollidiert nicht          | Story wird ohne Kolision angelegt
| 8 | `help` eingeben | alle Befehle werden angezeigt | Folgende Befehle stehen zur Verfuegung: help, dump, enter, store, load, exit
| 9 | `exit` eingeben | Programm wird beendet | Erfolgreich beendet