/*
 * Alex Beaumont-Stidwill
 * 14abs6
 * 10176777
 * CMPE 320 Assignment 4: Jumble Puzzle
 * Header File
 */

#pragma once

#include <iostream>

using namespace std;

typedef char* charArrayPtr;

class JumblePuzzle{
public:
	//Constructors
	JumblePuzzle(const string&, const string&);
	JumblePuzzle(const JumblePuzzle&);
	//Destructor
	~JumblePuzzle();
	//getters
	int getRowPos() const;
	int getColPos() const;
	char getDirection() const;
	charArrayPtr* getJumble() const;
	int getSize() const;

private:
	int row, col, direction, size;
	charArrayPtr* jumble;
};

class BadJumbleException{
	//error message stored in 'message'
	private:
		string message;

	//exception with error message
	public:
		BadJumbleException(const string& message);
		string& what(void);
};
