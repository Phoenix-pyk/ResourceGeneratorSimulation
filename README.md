Game Setup
Number of Factions: 
At the beginning, the user is prompted to enter the number of factions participating in the game.

Victory Condition: 
The user is also prompted to specify the number of resource generators a faction must acquire to win.

Turn Structure:
Each turn begins with a dice roll, and the outcome determines the actions taken by the factions:

Dice Roll: The current faction rolls two six-sided dice, resulting in a sum between 2 and 12.

Actions Based on Dice Roll:
If the roll is not 7:
Every faction generates resources based on the rolled number.
Resources are generated only if the faction has a ResourceGenerator assigned to the rolled number.
If the roll equals 7:
Lose Overflow: Each faction loses half of its resources as overflow.
Block a Number: A random number is selected by rolling the dice again, and that number becomes blocked, meaning no resources can be generated for it until it is unblocked.
Resource Steal: The current faction steals a resource from a randomly selected faction (other than itself).

Constructing Generators:
After resource generation or overflow management, the current faction attempts to construct new generators using its accumulated resources.
A faction constructs new generators by using a set of resources, increasing its generator count.
The updated resource and generator counts are displayed after each construction attempt.

Victory Check:
After each turn, the game checks if the current faction has reached the required number of generators.
If the faction meets the victory condition, it is declared the winner, and the game ends.
Winning the Game
The first faction to reach the specified number of resource generators wins the game. A message is displayed announcing the victorious faction, and the game concludes.
