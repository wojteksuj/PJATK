#include "Game.h"


auto Game::run(GameState &state) -> void {
    while (window.isOpen()) {

        update(state);
        handleEvents(state);
        render(state);
    }
}
