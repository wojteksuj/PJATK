#ifndef UNTITLED_WORD_H
#define UNTITLED_WORD_H


struct Word {

    sf::Text text;
    sf::Vector2f wordPosition;
    sf::Vector2f wordVelocity;
    float lastPosition;

    Word(std::string const& text, sf::Font &font, int charSize, float x, float y,
         float& vx);


    auto move(float deltaTime) -> void;


    auto getLenght() -> float;

    auto getHeight() -> float;

    auto correctHighlight(std::string input) -> bool;

};

#endif
