# card_game
Create a Card Game Framework! ★★
Template Method—Build a Card Game Framework!

Requirements
You need to implement two simple card games, and your task is to minimize code duplication as much as possible.

Game 1: Simple Poker Showdown
This game supports four players: players can be either real players (Human Player) or computer players (AI Player).

Player Implementation:
Human Player: Uses a Command Line Interface (CLI) to make selections.
AI Player: Makes selections randomly.
Game Flow:
Players are denoted as P1, P2, P3, and P4 in order.

The game uses a Deck containing 52 cards at the beginning.

Each Card has a Rank and Suit.

The game follows these steps:

a. Game Start:

Each player (P1~P4) names themselves.
The deck is shuffled.
b. Drawing Phase:

Starting from P1, each player draws from the deck in turns until everyone has 13 cards in their Hand.
c. Rounds (13 Rounds Total):

P1~P4 each play a card in turns (without knowing each other’s cards).
Reveal all played cards.
Determine the round winner based on card strength, awarding them 1 point.
d. Game End:

After 13 rounds, the player with the most points is the winner, and their name is displayed.
Card Strength Rules:
Compare Ranks first: The higher rank wins.
If ranks are equal, compare Suits: The higher suit wins.
Rank order (ascending):
2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K, A
Suit order (ascending):
Clubs (♣) < Diamonds (♦) < Hearts (♥) < Spades (♠)
Game 2: Simple UNO
This game also supports four players, with the same player types as in Poker Showdown.

Game Flow:
Players are denoted as P1, P2, P3, and P4 in order.

The game uses a Deck containing 40 cards at the beginning.

There are 4 colors: BLUE, RED, YELLOW, GREEN.
Each color has 10 numbers (0~9).
i. Game Start:

Each player (P1~P4) names themselves.
The deck is shuffled.
ii. Drawing Phase:

Starting from P1, each player draws from the deck in turns until everyone has 5 cards in their Hand.
iii. Gameplay:

The first card from the deck is placed on the table as the starting card.
Players take turns in the order P1 → P2 → P3 → P4 → P1, and so on.
A player must play a card that matches either the color or the number of the last played card.
The played card becomes the new top card on the table.
The first player to play all their cards wins the game.
If a player has no playable card, they must draw one card from the deck.
If the deck is empty, the discarded cards (except the last played card) are shuffled back into the deck.