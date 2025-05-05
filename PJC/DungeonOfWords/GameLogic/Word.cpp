#include "SFML/Graphics.hpp"
#include <string>
#include "Word.h"


Word::Word(std::string const& text, sf::Font &font, int charSize, float x, float y, float& vx)
        : text(text,font,charSize), wordPosition(sf::Vector2f(x,y)), wordVelocity(sf::Vector2f(vx,0)), lastPosition(x) {};


auto Word::move(float deltaTime) -> void {
     text.move(wordVelocity * deltaTime);
};


auto Word::getLenght() -> float {
    return this->text.getLocalBounds().width;
};
auto Word::getHeight() -> float {
        return this->text.getLocalBounds().height;
};

auto Word::correctHighlight(std::string input) -> bool{
    auto itInput = input.begin();
    auto itWord = this->text.getString().begin();
    while (itInput != input.end()){
        if(*itInput == *itWord){
            itInput++;
            itWord++;
        } else return false;
    }
    return true;
};
