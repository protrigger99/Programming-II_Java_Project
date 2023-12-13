#include <bits/stdc++.h>
#include <fstream>
#include <iostream>
#include "DemoPortal.h"
#include "Dummy.h"
#include "Compare.h"

using namespace std;

DemoPortal::DemoPortal()
{
    this->portals++;
    this->portalID = 50; // this->portals; // unique id
    this->requests = 0;
};

void DemoPortal::sort_prev_list(vector<Dummy *> dummies, string para)
{
    // function to sort list of dummy products
    Compare *cmp = new Compare(para);
    sort(dummies.begin(), dummies.end(), *cmp);
    for (int i = 0; i < dummies.size(); i++)
        cout << dummies[i]->get_name() << " " << dummies[i]->get_uname() << " "
             << dummies[i]->get_price() << " " << dummies[i]->get_quantity() << endl;
}

void DemoPortal::processUserCommand(string command)
{
    ofstream fout;
    this->requests++;
    fout.open("PortalToPlatform.txt", ios::app);
    // Different if else conditions for different commands
    if (command == "List")
    {
        // Taking the inputs and writing it to PortaltoPlatform file
        string cat, order;
        cin >> cat >> order;
        list_orders[this->requests] = order;
        fout << this->portalID << " " << this->requests << " " << command << " " << cat << endl;
    }
    else if (command == "Buy")
    {
        // Taking the inputs and writing it to PortaltoPlatform file
        int numI;
        string pID;
        cin >> pID >> numI;
        fout << this->portalID << " " << this->requests << " " << command << " " << pID << " " << numI << endl;
    }
    else if (command == "Start")
        fout << this->portalID << " " << this->requests << " " << command << endl;
    else if (command == "Check")
    {
        // Calling the checkResponse method when Check is typed in
        this->requests--;
        this->checkResponse();
    }
    else
        cout << "Invalid command";
    commands[this->requests] = command;
    fout.close();
};

void DemoPortal::checkResponse()
{
    // Creating a vector containing pointers to dummy objects
    vector<Dummy *> dummies;
    // flag_list will be true if the previous command was "List"
    bool flag_list = false;
    // prev_id will store the request id of latest "List"
    int prev_id = 0;
    ifstream fin;
    string line;
    vector<string> otherPortals;
    fin.open("PlatformToPortal.txt");
    while (getline(fin, line))
    {
        // Splitting the line
        vector<string> vec = splitstring(line);
        if (stoi(vec[0]) != this->portalID)
        {
            otherPortals.push_back(line);
            continue;
        }
        if (commands[stoi(vec[1])] == "List")
        {
            // this if condition handles the part where 2 if conditions occur continuously
            // This prints the result for 1st list
            if (flag_list && prev_id != stoi(vec[1]))
            {
                sort_prev_list(dummies, list_orders[stoi(vec[1])]);
                dummies.clear();
            }
            // Creating ojects and adding the respective pointers to the vector
            Dummy *dummy = new Dummy(stof(vec[4]), vec[2], stoi(vec[5]), vec[3]);
            dummies.push_back(dummy);
            // Updating flag_list and prev_id
            flag_list = true;
            prev_id = stoi(vec[1]);
        }
        else if (commands[stoi(vec[1])] == "Buy" || commands[stoi(vec[1])] == "Start")
        {
            // This if condition gets executed if the previous command was "List"
            if (flag_list)
            {
                sort_prev_list(dummies, list_orders[stoi(vec[1])]);
                dummies.clear();
            }
            // Printing the line from the file for the current command (Buy/List)
            for (int i = 2; i < vec.size(); i++)
                cout << vec[i] << " ";
            cout << endl;
            // Updating flag_list
            flag_list = false;
        }
    }
    // This if condition will be executed if the last command is "List"
    if (flag_list)
    {
        sort_prev_list(dummies, list_orders[this->requests]);
        dummies.clear();
    }
    fin.close();
    ofstream fout;
    fout.open("PlatformToPortal.txt");
    for (auto line : otherPortals)
        fout << line << endl;
    fout.close();
};

vector<string> DemoPortal::splitstring(string line)
{
    // Function to split each line which is read from the file
    string temp = "";
    vector<string> vec;
    // split using space
    for (int i = 0; i < line.size(); i++)
    {
        if (line[i] == ' ' || i == line.size() - 1)
        {
            vec.push_back(temp);
            temp = "";
        }
        else
            temp += line[i];
    }
    return vec;
};
