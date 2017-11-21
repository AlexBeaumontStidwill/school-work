/*
 * Alex Beaumont-Stidwill
 * 14abs6
 * 10176777
 * CMPE 320 Assignment 2: The Fraction Class
 * Header File
 */

//include header file only a single time
#pragma once

#include <iostream>
#include <string>

using namespace std;

class Fraction{
private:
	// integers to hold the numerator and denominator
	int top, bottom;

public:
	Fraction(); //default constructor
	Fraction(int); // Constructor, Conversion constructor
	Fraction(int, int); // constructor
	//accessors
	int numerator() const;
	int denominator() const;

	//functions for the gcd algorithm
	void normalize(int &, int &);
	int gcd(int, int);

	Fraction &operator-(); //negation
	Fraction &operator++(); // pre-increment
	Fraction operator++(int); //post-increment

	Fraction &operator+=(const Fraction &right); // addition and assignment

// friend functions for binary operators
friend Fraction operator+(const Fraction &left, const Fraction &right);
friend Fraction operator-(const Fraction &left, const Fraction &right);
friend Fraction operator*(const Fraction &left, const Fraction &right);
friend Fraction operator/(const Fraction &left, const Fraction &right);

friend bool operator!=(const Fraction &left, const Fraction &right);
friend bool operator==(const Fraction &left, const Fraction &right);
friend bool operator<(const Fraction &left, const Fraction &right);
friend bool operator>(const Fraction &left, const Fraction &right);
friend bool operator<=(const Fraction &left, const Fraction &right);
friend bool operator>=(const Fraction &left, const Fraction &right);

//get a bit ahead of ourselves so its easy to display a Fraction object
friend ostream &operator<<(ostream &out, const Fraction &value); //used with cout
friend istream &operator>>(istream &in, Fraction &value); //used with cin
};

//throw excpetions at fraction related errors
class FractionException{
//error message stored in 'message'
private:
	string message;

//exception with error message
public:
	FractionException(const string& message);
	string& what(void);
};
