#include <bits/stdc++.h>
#include "Dummy.h"
using namespace std;

// dummy product for sorting on Portal side
Dummy::Dummy(float price, string name, int quantity, string uname)
{
    this->price = price;
    this->name = name;
    this->quantity = quantity;
    this->uname = uname;
}

// getters
string Dummy::get_name() { return this->name; }
string Dummy::get_uname() { return this->uname; }
float Dummy::get_price() { return this->price; }
int Dummy::get_quantity() { return this->quantity; }