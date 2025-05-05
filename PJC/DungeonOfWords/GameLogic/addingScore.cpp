#include "Game.h"
#include "fstream"

auto Game::addingScore(const std::string &filename, const std::string &line) -> void {
    std::ofstream file(filename, std::ios_base::app);

    file << line << std::endl;

    file.close();
}