# Java-Minesweeper-Maze
A very simple Java Swing minesweeper game.

# Introduction
This project was created in 2018 as a part of my programming 2 class. It started off as a simple command line lab, but I had never made a GUI application so I went back and added a simple Swing wrapping around the original command line program. 

When the program is ran, the player is presented with a default 10 by 10 board, with a randomly marked finish point labelled "F". The player starts in the upper left corner, and the goal is to get to the finish without hitting any mines. The player can move via the keys W, A, S, D. When the player reaches the finish, all mines are revealed. When either the player finishes or hits a mine, the game is over. The application must be restarted to play again.

# Installation
After cloning this repository locally, import it into your favorite Java IDE. The main class is "Minesweeper.java". The X and Y dimensions of the game along with the number of mines can be configured at the top of that file as well.
