/*
 * Alex Beaumont-Stidwill
 * 14abs6
 * 10176777
 * CMPE 320 Assignment 1: A Shakespearian Insult Generator
 * Header File
 */

//include header file only a single time
#pragma once

#include <vector>
#include <string>
#include <fstream>
#include <iostream>

using namespace std;

//throw excpetions at file related errors
class FileException{
//error message stored in 'message'
private:
	string message;

//exception with error message
public:
	FileException(const string& message);
	string& what(void);
};

//throws exceptions when the specified number of generated insults is above or below the limits
class NumInsultsOutOfBounds{
	//same idea as FileException
private:
	string message;

public:
	NumInsultsOutOfBounds(const string& message);
	string& what(void);
};

class InsultGenerator{
//vectors to hold the components of each insult
private:
	vector<string> first, second, third;

public:
	//initialize insult generator with appropriate words to be used
	void initialize(void);
	//generate a single insult
	const string talkToMe(void);
	//generate a specific number of insults
	const vector<string> generate(const int);
	//generate a specific number of insults and write them to a file
	void generateAndSave(const string, const int);
};
