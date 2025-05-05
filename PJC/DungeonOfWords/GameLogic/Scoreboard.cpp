#include "Game.h"
#include <iostream>
#include <string>
#include <sstream>
#include <fstream>

auto Game::getBestScorers() -> void {


    auto temp = std::pair<std::string, int>();
    auto vecScore = std::vector<std::pair<std::string, int>>();

    std::string line;
    std::ifstream file;


    std::string name;
    std::string score;



    file.open("scores.txt");


    if (file.is_open()) {

        while (std::getline(file, line)) {
            std::istringstream iss(line);
            std::istringstream lineStream(line);

            if (lineStream >> name >> score) {
                temp.first = name;
                temp.second = std::atoi(score.c_str());
                vecScore.push_back(temp);
                counterScorers++;
            }
        }
    }

    file.close();

    std::sort(vecScore.begin(), vecScore.end(),
              [](std::pair<std::string, int> a, std::pair<std::string, int> b) -> bool {
                  return a.second > b.second;
              });

    if(counterScorers >= 5) counterScorers = 5;


    for (int i = 0; i <= counterScorers-1; i++) {

        auto tempPair = vecScore[i];
        auto text = std::string(tempPair.first +" ("+std::to_string(tempPair.second)+")");

        if(i == 0 ) textScore1.setString(text);
        if(i == 1 ) textScore2.setString(text);
        if(i == 2 ) textScore3.setString(text);
        if(i == 3 ) textScore4.setString(text);
        if(i == 4 ) textScore5.setString(text);

    }



    //setting the texts
    textScoreboard2.setPosition(sf::Vector2f((window.getSize().x - textScoreboard2.getGlobalBounds().width) / 2, 20));
    textScoreboard2.setFillColor(sf::Color::Black);
    textScoreboard2.setOutlineColor(sf::Color::White);
    textScoreboard2.setOutlineThickness(3);


    textRank.setPosition(sf::Vector2f(320, 180));
    textRank.setFillColor(sf::Color::Black);
    textRank.setOutlineColor(sf::Color::White);
    textRank.setOutlineThickness(3);

    //render
    window.draw(textScoreboard2);
    window.draw(textRank);

    window.draw(textScore1);
    window.draw(textScore2);
    window.draw(textScore3);
    window.draw(textScore4);
    window.draw(textScore5);


}