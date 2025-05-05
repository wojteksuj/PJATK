#include "Game.h"

auto Game::animatingButtons(sf::Vector2f mousePos) -> void {

    if (textStart.getGlobalBounds().contains(mousePos)) {
        textStart.setCharacterSize(100);
        textStart.setFillColor(sf::Color::White);
        textStart.setOutlineColor(sf::Color::Black);
        textStart.setPosition(sf::Vector2f(
                (window.getSize().x - textStart.getGlobalBounds().width) / 2, 400));
    } else {
        textStart.setCharacterSize(80);
        textStart.setFillColor(sf::Color::Black);
        textStart.setOutlineColor(sf::Color::White);
        textStart.setPosition(sf::Vector2f(
                (window.getSize().x - textStart.getGlobalBounds().width) / 2, 400));
    }

    if (textQuitMenu.getGlobalBounds().contains(mousePos)) {
        textQuitMenu.setCharacterSize(100);
        textQuitMenu.setFillColor(sf::Color::White);
        textQuitMenu.setOutlineColor(sf::Color::Black);
        textQuitMenu.setPosition(sf::Vector2f(
                (window.getSize().x - textQuitMenu.getGlobalBounds().width) / 2, 600));
    } else {
        textQuitMenu.setCharacterSize(80);
        textQuitMenu.setFillColor(sf::Color::Black);
        textQuitMenu.setOutlineColor(sf::Color::White);
        textQuitMenu.setPosition(sf::Vector2f(
                (window.getSize().x - textQuitMenu.getGlobalBounds().width) / 2, 600));
    }

    if (textOptions.getGlobalBounds().contains(mousePos)) {
        textOptions.setCharacterSize(100);
        textOptions.setFillColor(sf::Color::White);
        textOptions.setOutlineColor(sf::Color::Black);
        textOptions.setPosition(sf::Vector2f(
                (window.getSize().x - textOptions.getGlobalBounds().width) / 2, 500));
    } else {
        textOptions.setCharacterSize(80);
        textOptions.setFillColor(sf::Color::Black);
        textOptions.setOutlineColor(sf::Color::White);
        textOptions.setPosition(sf::Vector2f(
                (window.getSize().x - textOptions.getGlobalBounds().width) / 2, 500));
    }

    if (textScoreboard.getGlobalBounds().contains(mousePos)) {
        textScoreboard.setCharacterSize(50);
        textScoreboard.setFillColor(sf::Color::White);
        textScoreboard.setOutlineColor(sf::Color::Black);
        textScoreboard.setPosition(sf::Vector2f(920, 645));
    } else {
        textScoreboard.setCharacterSize(40);
        textScoreboard.setFillColor(sf::Color::Black);
        textScoreboard.setOutlineColor(sf::Color::White);
        textScoreboard.setPosition(sf::Vector2f(930, 645));
    }


    if (textReturnMenu.getGlobalBounds().contains(mousePos)) {
        textReturnMenu.setCharacterSize(60);
        textReturnMenu.setFillColor(sf::Color::White);
        textReturnMenu.setOutlineColor(sf::Color::Black);
        textReturnMenu.setPosition(sf::Vector2f(15, 15));
    } else {
        textReturnMenu.setCharacterSize(50);
        textReturnMenu.setFillColor(sf::Color::Black);
        textReturnMenu.setOutlineColor(sf::Color::White);
        textReturnMenu.setPosition(sf::Vector2f(15, 15));
    }

    if (textReturnMenuScoreboard.getGlobalBounds().contains(mousePos)) {
        textReturnMenuScoreboard.setCharacterSize(60);
        textReturnMenuScoreboard.setFillColor(sf::Color::White);
        textReturnMenuScoreboard.setOutlineColor(sf::Color::Black);
        textReturnMenuScoreboard.setPosition(sf::Vector2f(15, 15));
    } else {
        textReturnMenuScoreboard.setCharacterSize(50);
        textReturnMenuScoreboard.setFillColor(sf::Color::Black);
        textReturnMenuScoreboard.setOutlineColor(sf::Color::White);
        textReturnMenuScoreboard.setPosition(sf::Vector2f(15, 15));
    }


    if (textResume.getGlobalBounds().contains(mousePos)) {
        textResume.setCharacterSize(80);
        textResume.setFillColor(sf::Color::White);
        textResume.setOutlineColor(sf::Color::Black);
        textResume.setPosition(
                sf::Vector2f((window.getSize().x - textResume.getGlobalBounds().width) / 2, 350));
    } else {
        textResume.setCharacterSize(70);
        textResume.setFillColor(sf::Color::Black);
        textResume.setOutlineColor(sf::Color::White);
        textResume.setPosition(sf::Vector2f(
                (window.getSize().x - textResume.getGlobalBounds().width) / 2, 350));
    }

    if (textQuit.getGlobalBounds().contains(mousePos)) {
        textQuit.setCharacterSize(80);
        textQuit.setFillColor(sf::Color::White);
        textQuit.setOutlineColor(sf::Color::Black);
        textQuit.setPosition(
                sf::Vector2f((window.getSize().x - textQuit.getGlobalBounds().width) / 2, 430));
    } else {
        textQuit.setCharacterSize(70);
        textQuit.setFillColor(sf::Color::Black);
        textQuit.setOutlineColor(sf::Color::White);
        textQuit.setPosition(sf::Vector2f(
                (window.getSize().x - textQuit.getGlobalBounds().width) / 2, 430));
    }

    if (textReturn.getGlobalBounds().contains(mousePos)) {
        textReturn.setCharacterSize(80);
        textReturn.setFillColor(sf::Color::Black);
        textReturn.setOutlineColor(sf::Color::White);
        textReturn.setPosition(
                sf::Vector2f((window.getSize().x - textReturn.getGlobalBounds().width) / 2, 490));
    } else {
        textReturn.setCharacterSize(70);
        textReturn.setFillColor(sf::Color::White);
        textReturn.setOutlineColor(sf::Color::Black);
        textReturn.setPosition(sf::Vector2f(
                (window.getSize().x - textReturn.getGlobalBounds().width) / 2, 490));
    }

    if (textSave.getGlobalBounds().contains(mousePos)) {
        textSave.setCharacterSize(80);
        textSave.setFillColor(sf::Color::Black);
        textSave.setOutlineColor(sf::Color::White);
        textSave.setPosition(
                sf::Vector2f((window.getSize().x - textSave.getGlobalBounds().width) / 2, 390));
    } else {
        textSave.setCharacterSize(70);
        textSave.setFillColor(sf::Color::White);
        textSave.setOutlineColor(sf::Color::Black);
        textSave.setPosition(sf::Vector2f(
                (window.getSize().x - textSave.getGlobalBounds().width) / 2, 390));
    }

    if (textSave2.getGlobalBounds().contains(mousePos)) {
        textSave2.setCharacterSize(70);
        textSave2.setFillColor(sf::Color::White);
        textSave2.setOutlineColor(sf::Color::Black);
        textSave2.setPosition(
                sf::Vector2f((window.getSize().x - textSave2.getGlobalBounds().width) / 2, 480));
    } else {
        textSave2.setCharacterSize(60);
        textSave2.setFillColor(sf::Color::Black);
        textSave2.setOutlineColor(sf::Color::White);
        textSave2.setPosition(sf::Vector2f(
                (window.getSize().x - textSave2.getGlobalBounds().width) / 2, 480));


    }
}
