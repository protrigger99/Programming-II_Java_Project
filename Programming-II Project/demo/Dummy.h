#ifndef DUMMY_H
#define DUMMY_H

#include <bits/stdc++.h>
using namespace std;
// Dummy class
// Objects will be created only if the command is "List"
// So that we can sort the objects according to the parameter
class Dummy
{
private:
    float price;
    string name, uname;
    int quantity;

public:
    Dummy(float, string, int, string);
    string get_name();
    string get_uname();
    float get_price();
    int get_quantity();
};

#endif