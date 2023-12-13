#ifndef DEMOPORTAL_H
#define DEMOPORTAL_H

#include <bits/stdc++.h>
#include "Dummy.h"
using namespace std;

class DemoPortal
{
public:
    DemoPortal();

    void processUserCommand(string);

    void checkResponse();

    vector<string> splitstring(string);

    void sort_prev_list(vector<Dummy *>, string);

private:
    static int portals;
    int requests, portalID;
    unordered_map<int, string> list_orders;
    unordered_map<int, string> commands;
};

#endif