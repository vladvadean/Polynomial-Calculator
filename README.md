


# Polynomial Calculator


## Table of Contents  
1. [Assignment Objective](#assignment-objective) 
2. [Problem Analysis, Modeling, Scenarios, Use Cases](#problem-analysis-modeling-scenarios-use-cases) 
3. [Design](#design)
4. [Implementation](#implementation) 
5. [Results](#results) 
6. [Conclusions](#conclusions) 
7. [Bibliography](#bibliography)

## Assignment Objective

&nbsp;&nbsp;&nbsp;&nbsp;The main objective of the assignment is the implementation of a polynomial calculator with the following operations: add, subtract, multiply, divide, derivate and integrate. For this task we need to extract the two polynomials or only one depending on the operation and to convert it into a polynomial type of variable. Meanwhile, all these actions will be shown with a graphic interface built in the MVC architecture.

## Problem Analysis, Modeling, Scenarios, Use Cases

&nbsp;&nbsp;&nbsp;&nbsp;The type of polynomial used in this project is made of a map of multiple monomials, having a coefficient and an exponent. This requirement is to convert the input string from the graphic interface and to check for any input mistakes, the user could have introduced. To solve this task, we use a Regex function and first split the polynomial into monomials, and splitting again with Regex, every monomial into coefficient and exponent and creating new instances. If any invalid character is entered or the input does not comply with the correct form for the input, an input exception will be thrown in the result label, letting the user know he must enter a correct form.

&nbsp;&nbsp;&nbsp;&nbsp;After these steps if everything is in order, the user can choose from any of the six operations presented above, mathematical errors such as dividing by 0, not being a critical exception that will break the process, will show the proper result. Every time a new operation is chosen the text of the result label will refresh and will print the proper result.

## Design

&nbsp;&nbsp;&nbsp;&nbsp;The data structures used in this assignment are a TreeMap <Integer, Monomial> used to define the polynomial structure and a List<String> to collect the groups resulting from the Regex operation on executed on every monomial. Both of the Polynomial and Monomial classes are found in the DataModels package, along with the InputException, thrown every time the user inserts wrong data format.

&nbsp;&nbsp;&nbsp;&nbsp;The operation class defines and implements all the methods needed, having two polynomials, and is found in the BussinessLogic package.

&nbsp;&nbsp;&nbsp;&nbsp;The GUI is built in the MVC style, the GUI package contains the controller: PolynomialCalculatorController, the view: PolynomialCalculatorView and the model: PolynomialCalculator.
![UML Class Diagram](https://github.com/vladvadean/Polynomial-Calculator/assets/126804850/0b5b161f-92cf-4dda-b913-dd586a229530)


![Graphical User Interface](https://github.com/vladvadean/Polynomial-Calculator/assets/126804850/91cef4a2-2e5f-4cbc-be7c-7724b7319d8a)

Use Case: Add two polynomials

Primary Actor: User

Success Scenarios Steps:

1.  The user inserts one or two polynomials
2.  The calculator checks if the syntax of the input is right
3.  The client uses the add button
4.  The calculator returns the correct value

Alternative Sequences:  
&nbsp;&nbsp;&nbsp;&nbsp;Incorrect data inserted

 - the result label text prints an error message
 - the scenario returns to step 1

## Implementation

&nbsp;&nbsp;&nbsp;&nbsp;The operation class has two attributes: the first polynomial and the second polynomial and it implements all the operations required for the program to work properly. Every operation is defined for both polynomial and monomial data. The polynomial methods that generate the output call the monomial methods implemented in the operation class to deliver an easier understanding and better organization of code. Such a method is the divide operation:
```java
private Monomial divide(Monomial m1, Monomial m2) {  
	// m/this  
	//m->m1  
	//this->m2  
	Monomial aux;  
	if (m1 == null) return null;  
		aux = new Monomial(m1.getCoefficient() / m2.getCoefficient(),m1.getExponent() - m2.getExponent());  
	return aux;  
}  
  
public Array List<Polynomial> divide() {  
	//polinomial1->dividend  
	//polinomial2->divisor  
	ArrayList<Polynomial> rez = new ArrayList<>();  
	Polynomial retainP1 = polynomial1;  
	TreeMap<Integer, Monomial> quotient = new TreeMap<>();  
	if (polynomial1.getStructure().lastKey() < polynomial2.getStructure().lastKey()) {  
		rez.add(null);  
		rez.add(polynomial1);  
	}  
	while (polynomial1.getStructure().lastKey() >= polynomial2.getStructure().lastKey()) {  
		Monomial maux;  
		Polynomial paux;  
		TreeMap<Integer, Monomial> listAux = new TreeMap<>();  
		maux = this.divide(polynomial1.getStructure().get(polynomial1.getStructure().lastKey()), polynomial2.getStructure().get(polynomial2.getStructure().lastKey()));  
		listAux.put(maux.getExponent(), maux);  
		paux = new Polynomial(listAux);  
		polynomial1 = this.subtraction(polynomial1, this.multiply(polynomial2, paux));  
		quotinent.put(maux.getExponent(), maux);  
		if (Objects._equals_(polynomial1.getStructure().lastKey(), polynomial1.getStructure().firstKey())) 
			break;  
		polynomial1.getStructure().remove(polynomial1.getStructure().lastKey());  
		listAux.remove(maux.getExponent());  
	}  
	Polynomial pQuotinent = new Polynomial(quotinent);  
	Polynomial pRest = polynomial1;  
	polynomial1 = retainP1;  
	rez.add(pQuotinent);  
	rez.add(pRest);  
	return rez;  
}
```
&nbsp;&nbsp;&nbsp;&nbsp;As shown above, this is a more complex method that uses more monomial methods. The returned type is a List<Polynomial> that contains the quotient as the first element and the rest as the second element.

&nbsp;&nbsp;&nbsp;&nbsp;Of course, the program needs to print all the data acquired so an overridden method toString is required to print the resulting polynomial:
```java
  @Override public String toString() { 
	  int k = 0; 
	  String s = ""; 
	  Monomial first, last; 
	  first = this.structure.get(this.structure.lastKey()); 
	  last = this.structure.get(this.structure.firstKey()); NavigableMap<Integer, Monomial> aux;  
	  if (first.getExponent() > last.getExponent())  
		  aux = structure.descendingMap();  
	  else 
		  aux = structure;  
	  for (Map.Entry<Integer, Monomial> entry : aux.entrySet()) { 
		  if (k != 0) { 
			  if (entry.getValue().getCoefficient() > 0)  
				  s += '+'; 
		  } 
		  if (entry.getValue().getCoefficient() != 0)  
			  s += entry.getValue().toString(); 
		  k++; 
	   } 
	  if (s.isEmpty())  
		  s += '0';  
	  return s; 
   }
```
&nbsp;&nbsp;&nbsp;&nbsp;The View Class has attributes corresponding to all the buttons, labels and text field used.

&nbsp;&nbsp;&nbsp;&nbsp;An example is the textbox for the first polynomial, in which we specify the background, text color of the text field, and its size:
 ```java
	pol1 = new TextField(); 
	pol1.setStyle("-fx-background-color: #292929;-fx-text-inner-color: #FFFFFF;"); pol1.setLayoutX(210.0); 
	pol1.setLayoutY(150.0); pol1.setPrefWidth(400);
```

&nbsp;&nbsp;&nbsp;&nbsp;The Controller Class defines all the actions event handlings for every button. No matter the button the polynomials are always read and checked in a try and catch block. If everything is in order a new instance of the operation class is created using the two polynomials just read and the proper method is called, setting the result’s label text as necessary:
```java
 public static EventHandler<ActionEvent>
	 _subtraction_ = actionEvent -> { Polynomial p1 = null; 
	 try { 
		 p1 = new Polynomial(_calculatorView_.getPol1().getText()); 
	 } 
	 catch (InputException e) {
		 _calculatorView_.setResult(e.getMessage()); 
	 } Polynomial p2 = null; 
	 try { 
		 p2 = new Polynomial(_calculatorView_.getPol2().getText());
	 } catch (InputException e) {
		 _calculatorView_.setResult(e.getMessage()); 
	 } 
	 Operation op = new Operation(p1, p2); _calculatorView_.setResult(op.subtraction(p1, p2).toString()); 
 };
```

## Results

&nbsp;&nbsp;&nbsp;&nbsp;The results of all six operations are checked with two sets of tests implemented in the OperationTest class using JUnit, found in the test package: one that should output the correct result and one that should output the wrong result:

![Results](https://github.com/vladvadean/Polynomial-Calculator/assets/126804850/0779f5c9-1d2b-4d03-b0c5-dfbb24a775d8)

## Conclusions

&nbsp;&nbsp;&nbsp;&nbsp;This assignment is a good exercise for implementing a complex GUI and to teach you how to make effective use of your own code. Although the algorithms used for the operations were not too complicated, they had to cover any exceptions especially for extracting the input and validating it. The requirements help the programmer maintain an organized code, that is easy to read and to maintain in case of any future developments or any optimizations.

## Bibliography

1.  JavaFX documentation: [https://fxdocs.github.io/docs/html5/](https://fxdocs.github.io/docs/html5/)
2.  Regex documentation: [https://regexr.com/](https://regexr.com/)
3.  Collections documentation: [https://www.javatpoint.com/collections-in-java](https://www.javatpoint.com/collections-in-java)
