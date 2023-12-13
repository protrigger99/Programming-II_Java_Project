#include <bits/stdc++.h>
#include "Compare.h"
using namespace std;
// Constructor
Compare::Compare(string parameter)
{
    this->parameter = parameter;
}
// Operator function to implement sorting
bool Compare::operator()(Dummy *d1, Dummy *d2)
{
    if (parameter == "Price")
        return (d1->get_price() < d2->get_price());
    else if (parameter == "Name")
        return (d1->get_name() < d2->get_name());
    else
        return false;
}