/*
 * Alex Beaumont-Stidwill
 * 14abs6
 * 10176777
 * CMPE 320 Assignment 1: A Shakespearian Insult Generator
 */

#include <iostream>
#include <string>
#include <vector>
#include <fstream>
#include <algorithm>

#include "insultgenerator_14abs6.h"

using namespace std;

//initialize the error exceptions with an error message & display the message

FileException::FileException(const string& message) : message(message) {}
string& FileException::what() {
	return message;
}

NumInsultsOutOfBounds::NumInsultsOutOfBounds(const string& message) : message(message) {}
string& NumInsultsOutOfBounds::what() {
	return message;
}

//initialize insult generator with possible words
void InsultGenerator::initialize(){
	ifstream file("InsultsSource.txt");
	int wordCount = 1;
	string word;

	if(file.fail())
		throw FileException("Something's wrong with the file");
	else{
		//add words to the vectors
		while(file >> word){
			switch(wordCount) {
			case 1:
				first.push_back(word);
				break;
			case 2:
				second.push_back(word);
				break;
			case 3:
				third.push_back(word);
				break;
			default:
				break;
			}

			//for the first insult
			 if (wordCount == 3)
				 wordCount = 1;
			 else
				 wordCount++;
		}

		//sort in alphabetic order
		sort(first.begin(), first.end());
		sort(second.begin(), second.end());
		sort(third.begin(), third.end());
	}
}
//generate single insult
const string InsultGenerator::talkToMe(){
			return "Thou " + first.at(rand() % 50) + " " + second.at(rand() % 50) + " " + third.at(rand() % 50) + "!";
		}

//generate specified number of insults
const vector<string> InsultGenerator::generate(int const numOfInsults){
	int min = 1;
	int max = 10000;
	int count = 0;
	string current;
	vector<string> uniqueInsults;

	//check limits
	if(numOfInsults < min || numOfInsults > max){
		throw NumInsultsOutOfBounds("You can only request between 1 and 10 000 insults");
	} else {
		for(int i = 0; i < numOfInsults; i++) {
			count = 0;
			//create insult
			current = talkToMe();

			//check uniqueness
			if(uniqueInsults.size() > 0){
				//loop through however many elements held in vector
				while((unsigned) count < (unsigned) uniqueInsults.size()) {
					//found duplicate, generate new insult
					if(current == uniqueInsults.at(count)) {
						current = talkToMe();
						count = -1;
					}

					count ++;
				}
			}

			uniqueInsults.push_back(current);
		}

		//Alphabetizes
		sort(uniqueInsults.begin(), uniqueInsults.end());
	}

	return uniqueInsults;

}

//generate specified number of insults and save it in file
void InsultGenerator::generateAndSave(const string filename, const int numOfInsults){
	int min = -1;
	int max = 10000;

	//check limits
	if(numOfInsults < min || numOfInsults > max){
		throw NumInsultsOutOfBounds("You can only request between 1 and 10000 insults");
	} else {
		//create/open new file
		ofstream insultFile;
		insultFile.open(filename);

		if(insultFile.fail())
			throw FileException("Something's wrong with the file");
		else {
			//generate the insults
			vector<string> uniqueInsults;
			uniqueInsults = generate(numOfInsults);
			int i = 0;

			//write all insults into file
			while(i != numOfInsults){
				insultFile << uniqueInsults.at(i) << endl;
				i++;
			}

		}

		insultFile.close();

	}
}
