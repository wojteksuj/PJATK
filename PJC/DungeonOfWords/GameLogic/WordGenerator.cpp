#include <random>
#include <iostream>
#include <fstream>
#include "WordGenerator.h"

//link: https://stackoverflow.com/questions/13445688/how-to-generate-a-random-number-in-c
class NumberGenerator {
    std::mt19937 engine; // Mersenne Twister pseudo-random generator engine
public:
    // Constructor
    NumberGenerator() : engine(std::random_device{}()) {}

    auto generate(int min, int max) -> int {
        std::uniform_int_distribution<int> distribution(min, max);
        return distribution(engine);
    }
};

auto generateWordIndex() -> int {



    //generating index
    auto engine = NumberGenerator();

    int min = 0; //minimal index
    int max = 499; //maximal index
    int a = engine.generate(min, max);

    return a;

}

auto generatePositionY(int t) -> float {
    auto engine = NumberGenerator();

    int y = engine.generate(0, t);
    return y;
}