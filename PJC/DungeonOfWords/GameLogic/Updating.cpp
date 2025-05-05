#include "Game.h"


auto Game::update(GameState &state) -> void {

    sf::Vector2f mousePos = window.mapPixelToCoords(sf::Mouse::getPosition(window));

    settexts();
    animatingButtons(mousePos);

    if (state == GameState::MenuState) {
        score = 0;
        heartCount = 3;
        heart1.setTexture(fullHeart);
        heart2.setTexture(fullHeart);
        heart3.setTexture(fullHeart);

        spawn = 2;
        wordVelocity = 90;
        counterDifficulty = 0;

    }

    //sliders animation
    if (isDraggingDifficulty) {
        float newPositionX = mousePos.x + dragOffsetDifficulty.x;

        if (newPositionX < 330) newPositionX = 330;
        if (newPositionX > 330 && newPositionX < 392.5) newPositionX = 392;
        if (newPositionX > 392.5 && newPositionX < 455) newPositionX = 455;
        if (newPositionX > 455 && newPositionX < 517.5) newPositionX = 517;
        if (newPositionX > 517.5) newPositionX = 580;

        cursorSize.setPosition(newPositionX, cursorSize.getPosition().y);

    }

    if (isDraggingFont) {
        float newPositionX = mousePos.x + dragOffsetFont.x;

        if (newPositionX < 330) newPositionX = 330;
        if (newPositionX > 330 && newPositionX < 580) newPositionX = 455;
        if (newPositionX > 455) newPositionX = 580;

        cursorFont.setPosition(newPositionX, cursorFont.getPosition().y);
    }

    //adjusting the size of words
    switch (static_cast<int>(cursorSize.getPosition().x)) {

        case 330:
            wordSize = 27;
            break;
        case 392:
            wordSize = 29;
            break;
        case 455:
            wordSize = 31;
            break;
        case 517:
            wordSize = 32;
            break;
        case 580:
            wordSize = 34;
            break;
    }
    textPreview.setCharacterSize(wordSize);

    switch (static_cast<int>(cursorFont.getPosition().x)) {
        case 330:
            fontWord = fontPick1;
            break;
        case 455:
            fontWord = fontPick2;
            break;
        case 580:
            fontWord = fontPick3;
            break;

    }

    //adjusting difficulty
    if (state == GameState::GameState) {
        if (score == (3 + counterDifficulty)) {
            spawn -= 0.15;
            wordVelocity += 10;
            counterDifficulty += 3;

        }
    }

    //spawning words
    if (state == GameState::GameState) {

        //adding words to vector
        if (vecClock.getElapsedTime().asSeconds() > spawn) {
            auto temp = words[generateWordIndex()];
            auto tempWord = Word(temp, fontWord, wordSize, 0, generatePositionY(window.getSize().y-50), wordVelocity);

            tempWord.text.setPosition(tempWord.wordPosition);
            tempWord.text.setOutlineColor(sf::Color::Black);
            tempWord.text.setOutlineThickness(3);

            vecWords.push_back(tempWord);
            vecClock.restart();
        }

        //checking correct spawn
        for (int i = 0; i < vecWords.size(); ++i) {
            if (i >= 1) {
                auto &currentText = vecWords[i].text;
                auto &previousText = vecWords[i - 1].text;

                if (currentText.getPosition().y > previousText.getPosition().y
                    && currentText.getPosition().y <
                       (previousText.getPosition().y + previousText.getLocalBounds().height)) {
                    currentText.setPosition(sf::Vector2f(0, generatePositionY(window.getSize().y - 50)));

                }

                if (currentText.getPosition().y < previousText.getPosition().y
                    && (currentText.getPosition().y + currentText.getGlobalBounds().height) >
                       previousText.getPosition().y) {

                    currentText.setPosition(sf::Vector2f(0, generatePositionY(window.getSize().y - 50)));
                }
            }
        }


        if (!vecWords.empty()) {
            Word &firstWord = vecWords[0];

            //what at the end
            if (firstWord.lastPosition + firstWord.getLenght() > window.getSize().x) {

                if (heartCount == 1) {
                    heart3.setTexture(empthyHeart);
                    vecWords.clear();
                    state = GameState::OverState;
                }
                if (heartCount == 2) {
                    heart2.setTexture(empthyHeart);
                    vecWords.clear();
                    heartCount--;
                }
                if (heartCount == 3) {
                    heart1.setTexture(empthyHeart);
                    vecWords.clear();
                    heartCount--;
                }


            }

            //setting the highlighting
            textHighlight.setFont(*firstWord.text.getFont());
            textHighlight.setCharacterSize(firstWord.text.getCharacterSize());
            textHighlight.setFillColor(sf::Color::Blue);

            if (firstWord.correctHighlight(input)) {
                textHighlight.setString(input);
            } else {
                textHighlight.setString("");
                input = "";
            }

            if (input == firstWord.text.getString()) {
                vecWords.pop_front();
                score++;
                textScore.setString(std::to_string(score));
                input = "";
                textHighlight.setString("");
            }
        }
    }

    //updating score
    textScore.setString(std::to_string(score));

    //name input
    textName.setString(inputName);

    if(state == GameState::SavingState) {
        spawn = 2;
        wordVelocity = 90;
        counterDifficulty = 0;
    }

}










