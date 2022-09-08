# Microservice Analyzer Tool Idee
 
Übersicht des Projektes:
Das Projekt begutachtet und implementiert eine Handvoll Werkzeuge, die Entwicklern irgendwann bei der Umsetzung von Microservices helfen könnten, diese besser zu überwachen und Schwachstellen zu erkennen.

## Kafka - Kommunikation zwischen den Anwendungen
![Übersichtsbild wie Kafka zwischen Spring und Neo4j/Zipkin steht](https://github.com/dsimonow/microanalyzerclient/blob/master/microanalyzeroverview.png?raw=true)

Kafka wird mit den produzierten Daten von Spring gefüttert und bietet diese dann für neo4j
und zipkin an.

## Spring - Custom Quelle
Das Projekt simuliert eine übliche Microservice Architektur in Spring, durch rudimentäre Multi Anwendungs Schnittstellen. Tracing Daten werden von Sleuth generiert und ans zipkin Topic gesendet. Die Schnittstellen generieren eigene Zustände für das API Topic in Kafka.
![Übersichtsbild: 5 Api's, die verschachtelt sich aufrufen um tracing Daten zu generieren](https://github.com/dsimonow/microanalyzerclient/blob/master/microanalyzerspringoverview.png?raw=true)

## Zipkin - distributed tracing system. 
Sammelt und stellt die Daten von Spring Sleuth dar.
## neo4j - Graphdatenbank
Für die Visualisierung und Graphtraversierung von komplexen
beziehungs basierten Anfragen.
