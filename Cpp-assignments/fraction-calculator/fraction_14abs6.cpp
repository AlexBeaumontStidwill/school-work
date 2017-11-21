/*
 * Alex Beaumont-Stidwill
 * 14abs6
 * 10176777
 * CMPE 320 Assignment 2: The Fraction Class
 * C++ File
 */

#include "fraction_14abs6.h"
using namespace std;

//initialize the error exception with an error message & display the message

FractionException::FractionException(const string&message) : message(message) {}
string &FractionException::what(){
	return message;
}

// set numerator and denominator to 0 & 1
Fraction::Fraction() : top(0), bottom(1){}

Fraction::Fraction(int t) : top(t), bottom(1) {}

//normalize the fractions
void Fraction::normalize(int &t, int &b){
	int g = gcd(t, b);
	t = t / g;
	b = b / g;
}

//gcd algorithm from assignment, returns gcd
int Fraction::gcd(int t, int b){
	if ((b <= t) && (t % b == 0))
		return b;
	if (t < b)
		gcd(b, t);
	else
		gcd(b, t % b);
}

Fraction::Fraction(int t, int b){
	//ensure denominator isn't 0
	if(b == 0)
		throw FractionException("Impossible to divide by 0");

	//if numerator is 0, answer will be 0
	if(t == 0){
		top = 0;
		bottom = 1;
	} else {
		top = t;
		bottom = b;
		//sign convention
		if(t < 0)
			t = t * -1;
		if(b < 0)
			b = b * -1;

		//ensure normalized state
		normalize(t, b);

		//if only one of either numerator or denominator is negative, make only the numerator negative
		if((top > 0) || (bottom > 0)){
		if((top < 0) || (bottom < 0))
			t = t * -1;
		}

		top = t;
		bottom = b;

	}

}

//getters
int Fraction::numerator() const {
	return top;
}

int Fraction::denominator() const {
	return bottom;
}

//addition and assignment
//returns reference to the object since the function is of &type
Fraction &Fraction::operator+=(const Fraction &right){
	int t = top * right.bottom + right.top * bottom;
	int b = bottom * right.bottom;
	normalize(t, b);
	top = t;
	bottom = b;
	return *this;
}

//negation
Fraction &Fraction::operator-(){
	top = top * -1;
	return *this;
}

//Pre-Increment
Fraction &Fraction::operator++(){
	top += bottom;
	return *this;
}

//Post-Increment
Fraction Fraction::operator++(int unused){
	Fraction f(top, bottom);
	top += bottom;
	return f;
}

//consider the numerator and denominator separately for the binary operators
Fraction operator+(const Fraction &left, const Fraction &right){
	int t = left.numerator() * right.denominator() + left.denominator() * right.numerator();
	int b = left.denominator() * right.denominator();
	return Fraction(t, b);
}

Fraction operator-(const Fraction &left, const Fraction &right){
	int t = left.numerator() * right.denominator() - left.denominator() * right.numerator();
	int b = left.denominator() * right.denominator();
	return Fraction(t, b);
}

Fraction operator*(const Fraction &left, const Fraction &right){
	int t = left.numerator() * right.numerator();
	int b = left.denominator() * right.denominator();
	return Fraction(t, b);
}

Fraction operator/(const Fraction &left, const Fraction &right){
	int t = left.numerator() * right.denominator();
	int b = left.denominator() * right.numerator();
	return Fraction(t, b);
}

bool operator!=(const Fraction &left, const Fraction &right) {
	if((left.numerator() != right.numerator()) || (left.denominator() != right.denominator())) 	return true;
	else	 return false;
}

bool operator==(const Fraction &left, const Fraction &right) {
	if((left.numerator() == right.numerator()) && (left.denominator() == right.denominator())) 	return true;
	else	 return false;
}

bool operator>(const Fraction &left, const Fraction &right){
	if((float)left.numerator()/left.denominator() > (float)right.numerator()/right.numerator()) 	return true;
	else 	return false;
}

bool operator<(const Fraction &left, const Fraction &right){
	if((float)left.numerator()/left.denominator() < (float)right.numerator()/right.numerator()) 	return true;
		else	 return false;
}

bool operator>=(const Fraction &left, const Fraction &right){
	if((left > right) || (left == right))	return true;
	else	 return false;
}

bool operator<=(const Fraction &left, const Fraction &right){
	if((left < right) || (left == right))	return true;
	else 	return false;
}

//used with cout
ostream &operator<<(ostream &out, const Fraction &value){
	out << value.top << "/" << value.bottom << endl;
	return out;
}

//used with cin
istream &operator>>(istream &in, Fraction &f){
	char c;
	int t, b;
	// set numerator
	in >> t;
	f.top = t;
	f.bottom = 1;

	//check for division
	c = in.peek();
	if(c == '/')	in >> c;
	else	return in;

	// check for 0
	c = in.peek();
	if(c == '0')	throw FractionException("Impossible to divide by 0");
	//find a fraction
	else if(isdigit(c)){
		in >> b;
		f.normalize(t, b);
		f.top = t;
		f.bottom = b;
	}

	return in;

}
