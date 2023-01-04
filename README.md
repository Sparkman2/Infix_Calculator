# Infix_Calculator

You can run the project through URCalculator.java

This is an infix calculator that reads through a txt file with infix notation and created a calculator to return the evaluations of the infix. 

First I read the txt file and focused on one line at a time. Then it goes to the infixtopostfix where I wanted to make a string that can separates operators and operands by a space. I also check if there were parentheses and be check to know the difference between a minus and negative sign.

In the postfixcaluclator,we take the string we just made and take it to a queue. When something doesn’t have a space we read it until we find another space. I read 2 terms since there are usually 2 operands before an operator and kept reading until we hit an operator. Then we push them on a stack and pop the operands when we get an operator and evaluate the result.

When the code finishes, it creates an file with the answers to the txt file
