/*
 * Alex Beaumont-Stidwill
 * 14abs6
 * 10176777
 * CMPE 320 Assignment 4: Jumble Puzzle
 */

#include "JumblePuzzle.h"

#include <iostream>

using namespace std;

JumblePuzzle::JumblePuzzle(const string& word, const string& diff){
	size = word.length();
	if (size < 3 || size > 10)
		throw BadJumbleException("Word must be between 3 and 10 characters in length");
	if (diff == "easy")
		size = size * 2;
	else if (diff == "medium")
		size = size * 3;
	else if (diff =="hard")
		size = size * 4;
	else
		throw BadJumbleException("Difficulty can only be 'easy', 'medium', or 'hard'");

	jumble = new char*[size];

	//Allocate array on heap
	for (int i = 0; i < size; i++)
		jumble[i] = new char[size];

	//Fill 2D array
	for (int i = 0; i < size; i++)
		for(int j = 0; j < size; j++)
			//generate random int in lower case ascii value range
			jumble[i][j] = (char)(rand() % 25 + 'a');

	// row and columns
	row = rand() % size;
	col = rand() % size;

	//length of string
	int wordLength = word.length();

	direction = rand() % 4;
	//direction 0 is bottom to top, 1 is left to right, 2 is top to bottom, 3 is right to left
	for (int i = 0; i < wordLength; i++){
		if(direction == 0){
			//ensure word can be placed
			if (wordLength > (row + 1))
				direction++;
			else
				jumble[row - i][col] = word.at(i);
		}
		if (direction == 1){
			if (wordLength > (size - col))
				direction++;
			else
				jumble[row][col + i] = word.at(i);
		}
		if (direction == 2){
			if (wordLength > (size - row))
				direction++;
			else
				jumble[row + i][col] = word.at(i);
		}
		if (direction == 3) {
			if (wordLength > (col + 1))
				direction = (direction + 1) % 4;
			else
				jumble[row][col - i] = word.at(i);
		}
	}
}

charArrayPtr* JumblePuzzle::getJumble() const {
	charArrayPtr* newJumble = new char*[size];
	for (int i = 0; i < size; i++)
		newJumble[i] = new char[size];
	for (int i = 0; i < size; i++){
		for (int j = 0; j < size; j++)
			newJumble[i][j] = jumble[i][j];
	}
	return newJumble;
}

JumblePuzzle::JumblePuzzle(const JumblePuzzle& other){
	row = other.getRowPos();
	col = other.getColPos();
	direction = other.getDirection();
	size = other.getSize();
	jumble = new char*[size];

	//individual array on heap
	for (int i = 0; i < size; i++)
		jumble[i] = new char[size];
	charArrayPtr* otherJumble = other.getJumble();
	for (int i = 0; i < size; i++){
		for (int j = 0; j < size; j++)
			jumble[i][j] = otherJumble[i][j];
	}
}

//destructor
JumblePuzzle::~JumblePuzzle(){
	for (int i = 0; i < size; i++)
		delete jumble[i];
	delete[] jumble;
}

//getters
char JumblePuzzle::getDirection() const{
	return direction;
}

int JumblePuzzle::getColPos() const{
	return col;
}

int JumblePuzzle::getRowPos() const{
	return row;
}

int JumblePuzzle::getSize() const{
	return size;
}

BadJumbleException::BadJumbleException(const string&message) : message(message) {}
string &BadJumbleException::what(){
	return message;
}
