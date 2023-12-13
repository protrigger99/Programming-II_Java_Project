#include <bits/stdc++.h>
using namespace std;
#include"demo/DemoPortal.h"
#include"demo/Dummy.h"

int DemoPortal::portals = 0;

int main()
{
    DemoPortal thisPortal;
    string cmd;
    while (true)
    {
        cin >> cmd;
        thisPortal.processUserCommand(cmd);
    }
}