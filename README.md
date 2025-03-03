# Furfeux

Furfeux is a Java-based text adventure game where players navigate through a mysterious mansion while managing their health, avoiding flames, and unlocking doors. The game utilizes a two-dimensional grid representing various types of rooms and obstacles within the mansion.

## Structures 
```
/furfeux
│── README.md
│── run.sh
├── src
│   ├── Case.java
│   ├── CaseTraversable.java
│   ├── Direction.java
│   ├── FenetreJeu.java
│   ├── furfeux.jar
│   ├── Furfeux.java  <-- (main file)
│   ├── Hall.java
│   ├── Images/  <-- (directory for assets)
│   ├── Joueur.java
│   ├── manoir.txt
│   ├── Mur.java
│   ├── Porte.java
│   ├── Sortie.java
│   ├── Terrain.java
```

## Features

- **Dynamic Environment:** The mansion consists of walls, halls of varying heat intensities, exits, and doors that can be opened or closed.
- **Player Interaction:** Players can freely move through the mansion, taking damage based on the heat intensity of the room they're in.
- **Fire Spread:** Flames propagate through the mansion each turn, posing a constant threat to the player.
- **Keys and Doors:** Players can collect keys scattered throughout the mansion's halls to unlock doors, granting access to new areas.
- **Visibility:** Players have limited visibility, only able to see a certain radius around their current location.
- **End Conditions:** The game ends if the player's health reaches zero or if they successfully reach the exit, with the remaining health serving as the final score.

## Instructions

1. **Setup:** Clone the repository (https://github.com/lafilledepondy/furfeux.git) and ensure you have Java installed on your system.
2. **Compilation/Execution:** Enter this command in your terminal `./run.sh` and `chmod +x run.sh`.
3. **Gameplay:** Follow on-screen prompts to navigate through the mansion, collect keys, unlock doors, and avoid flames.
4. **Win/Lose:** The game ends when the player either reaches the exit or runs out of health.

## Configuration

Furfeux has been configured with IntelliJ version 20.2. There may be unexpected changes if the program is executed in a different version.

## Author

Furfeux was developed by **Gayathiri RAVENDIRANE** and **Line HAJJAR** as a project for "Introduction à la programmation objet" at **Université Paris-Saclay**.
