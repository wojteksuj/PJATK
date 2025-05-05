#include "Game.h"

auto Game::settexts() -> void {

    //Main page
    textStart.setOutlineThickness(3);
    textQuitMenu.setOutlineThickness(3);
    textOptions.setOutlineThickness(3);
    textScoreboard.setOutlineThickness(3);
    textReturnMenuScoreboard.setOutlineThickness(3);

    textTitle.setFillColor(sf::Color::Black);
    textTitle.setOutlineColor(sf::Color::White);
    textTitle.setOutlineThickness(3);

    textTitle.setPosition(sf::Vector2f(
            (window.getSize().x - textTitle.getGlobalBounds().width)/2 , 50));

    //Option page
    textReturnMenu.setOutlineThickness(3);

    textWordsize.setFillColor(sf::Color::Black);
    textWordsize.setOutlineColor(sf::Color::White);
    textWordsize.setOutlineThickness(3);

    textFont.setFillColor(sf::Color::Black);
    textFont.setOutlineColor(sf::Color::White);
    textFont.setOutlineThickness(3);

    textPreview.setFillColor(sf::Color::White);
    textPreview.setOutlineColor(sf::Color::Black);
    textPreview.setOutlineThickness(3);

    textPreview.setPosition(sf::Vector2f((window.getSize().x - textPreview.getGlobalBounds().width)/2,550));

    textWordsize.setPosition(sf::Vector2f((window.getSize().x - textWordsize.getGlobalBounds().width)/2,20));

    textFont.setPosition(sf::Vector2f((window.getSize().x - textFont.getGlobalBounds().width)/2,225));



    sliderDifficulty.setPosition(sf::Vector2f((window.getSize().x - sliderDifficulty.getGlobalBounds().width)/2, 35));

    sliderFont.setPosition(sf::Vector2f((window.getSize().x - sliderFont.getGlobalBounds().width)/2, 240));

    //pause state
    textResume.setOutlineThickness(3);
    textQuit.setOutlineThickness(3);


    textPause.setFillColor(sf::Color::Black);
    textPause.setOutlineColor(sf::Color::White);
    textPause.setOutlineThickness(3);
    textPause.setPosition(sf::Vector2f((window.getSize().x - textPause.getGlobalBounds().width) / 2, 150));


    //Game over state
    textGameOver.setPosition(sf::Vector2f((window.getSize().x - textGameOver.getGlobalBounds().width) / 2, 230));

    textGameOver.setFillColor(sf::Color::White);
    textReturn.setFillColor(sf::Color::White);
    textSave.setFillColor(sf::Color::White);

    textReturn.setOutlineColor(sf::Color::Red);
    textGameOver.setOutlineColor(sf::Color::Red);
    textSave.setOutlineColor(sf::Color::Red);

    textReturn.setOutlineThickness(3);
    textGameOver.setOutlineThickness(3);
    textSave.setOutlineThickness(3);

    //setting the score
    textScore.setPosition(sf::Vector2f(window.getSize().x - 270, -5));

    //setting the scoreboard

    textScore1.setFillColor(sf::Color::Black);
    textScore1.setOutlineColor(sf::Color::White);
    textScore1.setOutlineThickness(3);
    textScore1.setPosition(sf::Vector2f(400, 180));

    textScore2.setFillColor(sf::Color::Black);
    textScore2.setOutlineColor(sf::Color::White);
    textScore2.setOutlineThickness(3);
    textScore2.setPosition(sf::Vector2f(400, 290));

    textScore3.setFillColor(sf::Color::Black);
    textScore3.setOutlineColor(sf::Color::White);
    textScore3.setOutlineThickness(3);
    textScore3.setPosition(sf::Vector2f(400, 400));

    textScore4.setFillColor(sf::Color::Black);
    textScore4.setOutlineColor(sf::Color::White);
    textScore4.setOutlineThickness(3);
    textScore4.setPosition(sf::Vector2f(400, 510));

    textScore5.setFillColor(sf::Color::Black);
    textScore5.setOutlineColor(sf::Color::White);
    textScore5.setOutlineThickness(3);
    textScore5.setPosition(sf::Vector2f(400, 620));

    textName.setFillColor(sf::Color::White);
    textName.setOutlineColor(sf::Color::Black);
    textName.setOutlineThickness(3);
    textName.setPosition(sf::Vector2f((window.getSize().x - textName.getGlobalBounds().width) / 2, 280));

    textTypeName.setFillColor(sf::Color::Black);
    textTypeName.setOutlineColor(sf::Color::White);
    textTypeName.setOutlineThickness(3);
    textTypeName.setPosition(sf::Vector2f((window.getSize().x - textTypeName.getGlobalBounds().width) / 2, 150));

    textSave2.setFillColor(sf::Color::Black);
    textSave2.setOutlineColor(sf::Color::White);
    textSave2.setOutlineThickness(3);


}