#include "Game.h"


auto Game::handleEvents(GameState &state) -> void {

    auto event = sf::Event();

    sf::Vector2f mousePos = window.mapPixelToCoords(sf::Mouse::getPosition(window));

    while (window.pollEvent(event)) {

        //closing the window
        if (event.type == sf::Event::Closed) {
            state = GameState::ExitState;
            window.close();
        }

        if (event.type == sf::Event::MouseButtonPressed) {

            if (state == GameState::MenuState) {
                if (textStart.getGlobalBounds().contains(mousePos)) {
                    state = GameState::GameState;
                }

                if (textQuitMenu.getGlobalBounds().contains(mousePos)) {
                    state = GameState::ExitState;
                    window.close();
                }

                if (textOptions.getGlobalBounds().contains(mousePos)) {
                    state = GameState::OptionState;
                }

                if (textScoreboard.getGlobalBounds().contains(mousePos)) {
                    state = GameState::ScoreboardState;
                }
            }

            if (state == GameState::PauseState) {
                if (textQuit.getGlobalBounds().contains(mousePos)) {
                    score = 0;
                    vecWords.clear();
                    state = GameState::MenuState;
                }

                if (textResume.getGlobalBounds().contains(mousePos)) {
                    state = GameState::GameState;
                }
            }


            if (state == GameState::OptionState) {
                if (textReturnMenu.getGlobalBounds().contains(mousePos)) {
                    state = GameState::MenuState;
                }
            }

            if (state == GameState::OverState) {
                if (textReturn.getGlobalBounds().contains(mousePos)) {
                    score = 0;
                    vecWords.clear();
                    state = GameState::MenuState;

                }
                if (textSave.getGlobalBounds().contains(mousePos)) {
                    vecWords.clear();
                    state = GameState::SavingState;

                }
            }

            if (state == GameState::ScoreboardState) {
                if (textReturnMenuScoreboard.getGlobalBounds().contains(mousePos)) {
                    state = GameState::MenuState;
                }
            }

            if (state == GameState::SavingState){

                if(textSave2.getGlobalBounds().contains(mousePos)){

                    inputName = inputName + " " + std::to_string(score);
                    addingScore("scores.txt", inputName);

                    inputName = "";
                    score = 0;
                    vecWords.clear();
                    state = GameState::MenuState;
                }
            }

        }


        if (state == GameState::OptionState) {

            //sliders
            if (event.type == sf::Event::MouseButtonPressed) {
                if (event.mouseButton.button == sf::Mouse::Left) {

                    if (cursorSize.getGlobalBounds().contains(mousePos)) {
                        isDraggingDifficulty = true;
                        dragOffsetDifficulty = cursorSize.getPosition() - mousePos;
                    }

                    if (cursorFont.getGlobalBounds().contains(mousePos)) {
                        isDraggingFont = true;
                        dragOffsetFont = cursorFont.getPosition() - mousePos;
                    }

                }
            }
            if (event.type == sf::Event::MouseButtonReleased) {
                if (event.mouseButton.button == sf::Mouse::Left) {
                    isDraggingDifficulty = false;
                    isDraggingFont = false;
                }
            }
        }

        //shortcuts
        if (state == GameState::MenuState) {
            if (sf::Keyboard::isKeyPressed(sf::Keyboard::LShift) && sf::Keyboard::isKeyPressed(sf::Keyboard::P)) {
                state = GameState::GameState;
            }

            if (sf::Keyboard::isKeyPressed(sf::Keyboard::LShift) && sf::Keyboard::isKeyPressed(sf::Keyboard::O)) {
                state = GameState::OptionState;
            }

            if (sf::Keyboard::isKeyPressed(sf::Keyboard::LShift) && sf::Keyboard::isKeyPressed(sf::Keyboard::Q)) {
                state = GameState::ExitState;
                window.close();
            }
        }
        if (state == GameState::OptionState) {
            if (sf::Keyboard::isKeyPressed(sf::Keyboard::Escape)) {
                state = GameState::MenuState;
            }
        }

        if (state == GameState::PauseState) {
            if (sf::Keyboard::isKeyPressed(sf::Keyboard::Enter)) {
                state = GameState::GameState;
            }
        }


        if (state == GameState::GameState) {

            //pausing
            if (sf::Keyboard::isKeyPressed(sf::Keyboard::Escape)) {
                state = GameState::PauseState;
            }


            //typing input
            if (event.type == sf::Event::TextEntered) {
                if (sf::Keyboard::isKeyPressed(sf::Keyboard::BackSpace) and input == "")
                    continue; //avoiding weird unicode things

                input += event.text.unicode;
            }
        }
        if(state == GameState::SavingState){
            if (sf::Keyboard::isKeyPressed(sf::Keyboard::Enter)) {
                inputName = inputName + " " + std::to_string(score);
                addingScore("scores.txt", inputName);

                inputName = "";
                score = 0;
                vecWords.clear();
                state = GameState::MenuState;
            }


            if(event.type == sf::Event::TextEntered){
                if (sf::Keyboard::isKeyPressed(sf::Keyboard::BackSpace) and inputName == "")
                    continue;
                if (sf::Keyboard::isKeyPressed(sf::Keyboard::BackSpace)){
                    auto temp = std::string("");
                    for(int i = 0; i< inputName.getSize() - 1; i++){
                        temp+=inputName[i];
                    }
                    inputName = temp;
                } else{
                    inputName += event.text.unicode;

                }

            }

        }

    }

}









