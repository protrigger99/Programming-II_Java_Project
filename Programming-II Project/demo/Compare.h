#ifndef COMPARE_H
#define COMPARE_H

#include <bits/stdc++.h>
#include "Dummy.h"

using namespace std;

// Compare class required for sorting of Dummy objects

class Compare
{
private:
    string parameter;

public:
    Compare(string);
    bool operator()(Dummy *, Dummy *);
};

#endif