#!/bin/bash
java -jar -Dspring.profiles.active=one target/microanalyzerclient-0.0.1-SNAPSHOT.jar &
java -jar -Dspring.profiles.active=two target/microanalyzerclient-0.0.1-SNAPSHOT.jar &
java -jar -Dspring.profiles.active=twoone target/microanalyzerclient-0.0.1-SNAPSHOT.jar &
java -jar -Dspring.profiles.active=three target/microanalyzerclient-0.0.1-SNAPSHOT.jar &
