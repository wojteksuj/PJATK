#include "Game.h"


auto Game::render(GameState &state) -> void {

    window.clear();
    window.draw(wall);


    if (state == GameState::MenuState) {
        window.draw(textTitle);
        window.draw(textStart);
        window.draw(textQuitMenu);
        window.draw(textOptions);
        window.draw(textScoreboard);
    }

    if (state == GameState::OptionState) {
        window.draw(textReturnMenu);
        window.draw(textWordsize);
        window.draw(textFont);

        window.draw(sliderDifficulty);
        window.draw(cursorSize);



        window.draw(sliderFont);
        window.draw(cursorFont);

        window.draw(textPreview);
    }

    if(state == GameState::ScoreboardState){
        window.draw(textReturnMenuScoreboard);
    }

    float deltaTime = spawnClock.restart().asSeconds();

    if (state == GameState::GameState) {

        window.draw(textScore);
        window.draw(heart1);
        window.draw(heart2);
        window.draw(heart3);

        if (!vecWords.empty()) {

            //spawning words
            for (auto &w: vecWords) {
                w.lastPosition += w.wordVelocity.x * deltaTime;
                w.move(deltaTime);
                window.draw(w.text);
            }

            //Highlight the first word
            Word &firstWord = vecWords[0];
            textHighlight.setPosition(firstWord.text.getPosition());
            window.draw(textHighlight);
        }
    }


    if (state == GameState::PauseState) {

        for (auto &w: vecWords) {
            window.draw(w.text);
            window.draw(textHighlight);
        }

        window.draw(heart1);
        window.draw(heart2);
        window.draw(heart3);

        window.draw(textScore);

        window.draw(textPause);
        window.draw(textQuit);
        window.draw(textResume);
    }

    if (state == GameState::OverState) {
        for (auto &w: vecWords) {
            window.draw(w.text);
            window.draw(textHighlight);
        }

        window.draw(heart1);
        window.draw(heart2);
        window.draw(heart3);

        window.draw(textScore);

        window.draw(textGameOver);
        window.draw(textReturn);
        window.draw(textSave);
    }

    if(state == GameState::ScoreboardState){
        getBestScorers();
    }

    if(state == GameState::SavingState){
        window.draw(textName);
        window.draw(textTypeName);
        window.draw(textSave2);
    }

    window.display();
}
