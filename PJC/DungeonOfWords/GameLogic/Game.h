#include "SFML/Graphics.hpp"
#include "Word.h"
#include "WordGenerator.h"
#include <deque>
#include "GameState.h"



#ifndef PROJECTPJC_GAME_H
#define PROJECTPJC_GAME_H

struct Game {

    //constructor
    Game();

    //methods
    auto run(GameState &state) -> void;

    auto handleEvents(GameState &state) -> void;

    auto render(GameState &state) -> void;

    auto update(GameState &state) -> void;

    auto animatingButtons(sf::Vector2f mousePos) -> void;

    auto settexts() -> void;

    auto getBestScorers() -> void;

    auto addingScore(const std::string& filename, const std::string& line) -> void;

    //fields
    sf::RenderWindow window;

    sf::String input;
    sf::Text textHighlight = sf::Text("", fontWord, 30);


    std::deque<Word> vecWords;

    sf::Font fontWord;
    sf::Font mainFont;

    sf::Clock vecClock;
    sf::Clock spawnClock;

    sf::Text textPause = sf::Text("PAUSE", mainFont, 150);
    sf::Text textResume = sf::Text("Resume", mainFont, 0);
    sf::Text textQuit = sf::Text("Quit", mainFont, 0);


    int score = 0;
    sf::Text textScore = sf::Text(std::to_string(score), mainFont, 50);

    //Game over
    sf::Text textGameOver = sf::Text("Game Over!", mainFont, 120);
    sf::Text textReturn = sf::Text("Return", mainFont, 45);
    sf::Text textSave = sf::Text("Save the game", mainFont, 45);

    sf::Texture wallTexture;
    sf::Sprite wall;


    sf::Font fontTitle;
    sf::Font fontButtons;

    GameState state;

    //Difficulty
    sf::Clock gameClock;

    int counter = 0;

    float spawn = 2;
    float wordVelocity = 90;


    //Menu page
    sf::Text textStart = sf::Text("START", fontButtons, 0);
    sf::Text textQuitMenu = sf::Text("QUIT", fontButtons, 0);
    sf::Text textOptions = sf::Text("OPTIONS", fontButtons, 0);
    sf::Text textScoreboard = sf::Text("SCOREBOARD", fontButtons, 0);
    sf::Text textTitle = sf::Text("Dungeon of\n    Words", fontTitle, 140);


    //Option page
    sf::Text textReturnMenu = sf::Text("RETURN", fontButtons, 0);
    sf::Text textWordsize = sf::Text("Word size :", fontButtons, 70);
    sf::Text textFont = sf::Text("Choose font:", fontButtons, 70);

    //Scoreboards page
    sf::Text textReturnMenuScoreboard = sf::Text("RETURN", fontButtons, 0);

    //sliders
    bool isDraggingDifficulty;
    sf::Vector2f dragOffsetDifficulty;

    sf::Texture sliderDifficultyTexture;
    sf::Sprite sliderDifficulty;

    sf::Texture cursorSizeTexture;
    sf::Sprite cursorSize;

    bool isDraggingFont;
    sf::Vector2f dragOffsetFont;

    sf::Texture sliderFontTexture;
    sf::Sprite sliderFont;

    sf::Texture cursorFontTexture;
    sf::Sprite cursorFont;


    //adjustable

    float wordSize = 39;

    sf::Font fontPick1;
    sf::Font fontPick2;
    sf::Font fontPick3;


    sf::Text textPreview = sf::Text("Aa", fontWord, wordSize);

    //health
    sf::Texture fullHeart;
    sf::Texture empthyHeart;

    sf::Sprite heart1;
    sf::Sprite heart2;
    sf::Sprite heart3;

    int heartCount = 3;

    //Scoreboard
    sf::Text textScoreboard2 = sf::Text("SCOREBOARD:", mainFont, 100);
    sf::Text textRank = sf::Text("1.\n\n2.\n\n3.\n\n4.\n\n5.", mainFont, 60);

    sf::Text textScore1 = sf::Text("", mainFont, 60);
    sf::Text textScore2 = sf::Text("", mainFont, 60);
    sf::Text textScore3 = sf::Text("", mainFont, 60);
    sf::Text textScore4 = sf::Text("", mainFont, 60);
    sf::Text textScore5 = sf::Text("", mainFont, 60);

    sf::String inputName;
    sf::Text textName = sf::Text("", mainFont, 60);

    sf::Text textTypeName = sf::Text("Type your name:", mainFont, 90);

    sf::Text textSave2 = sf::Text("Save!", mainFont, 60);

    std::vector<std::string> words = std::vector<std::string>();

    int counterScorers = 0;
    int counterDifficulty = 0;

};


#endif
