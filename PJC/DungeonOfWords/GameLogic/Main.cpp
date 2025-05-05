#include "Game.h"
#include "GameState.h"


auto main() -> int {

    GameState state = GameState::MenuState;
    Game game;

    while (state != GameState::ExitState) {

        game.run(state);
    }


}

