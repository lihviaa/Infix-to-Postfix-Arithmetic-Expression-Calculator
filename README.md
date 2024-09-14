# RPN Solver

This Java program implements a Reverse Polish Notation (RPN) sovler for evaluating arithmetic expressions. It supports operations such as setting expressions, assigning variables, converting infix to postfix notation and evaluating expressions with assigned variables.

## Group Members

- Diogo Lourenzon Hatz
- Leila Akina Ino
- Livia Alabarse dos Santos
- Pedro Henrique Araujo Farias
  
## Features

- **Set Infix Arithmetic Expression:** Allows setting an infix arithmetic expression containing operators `+`, `-`, `*`, `/`, `^` and variables (letters a-z, A-Z).
- **Set Variable Values:** Assigns numerical values to variables present in the expression.
- **Infix to Postfix Conversion:** Converts the entered infix expression to postfix notation using a stack-based algorithm.
- **Expression Evaluation:** Evaluates the postfix expression using the assigned variable values and displays the result.
- **Shutdown:** Provides an option to exit.

## Usage
1. **Setting Infix Arithmetic Expression:**
   - Enter the infix expression when prompted. It should only contain valid characters and operators.

2. **Setting Variable Values:**
   - After entering the infix expression, assign numerical values to variables prompted.

3. **Converting to Postfix:**
   - Once an expression and variable values are set, convert the infix expression to postfix.

4. **Evaluating Expression:**
   - Evaluate the postfix expression using the assigned variables and display the result.

5. **Exiting:**
   - Choose option 5 to exit the program. It provides a countdown before shutdown.

## Implementation Details

The project uses Java with the following components:
- `Main.java`: Contains the main program logic including menu-driven user interactions and function calls.
- `Pilha.java`: Implements a generic stack data structure used for infix to postfix conversion and expression evaluation.

## Requirements

- Java Development Kit (JDK) version 8 or above.

## Getting Started

To run the program:
1. Compile `Main.java` and `Pilha.java` using `javac`:
   ```
   javac Main.java Pilha.java
    ```

2. Execute the compiled `Main` class:
    ```
    java Main
    ```
## Example
Here is an example of how to use the Calculator:

<img src="https://github.com/lihviaa/Infix-to-Postfix-Arithmetic-Expression-Calculator/blob/main/assets/execucao_setInfix.png" alt="primeiro método">
<img src="https://github.com/lihviaa/Infix-to-Postfix-Arithmetic-Expression-Calculator/blob/main/assets/execucao_setVars.png" alt="segundo método">
<img src="https://github.com/lihviaa/Infix-to-Postfix-Arithmetic-Expression-Calculator/blob/main/assets/execucao_InfixtoPost.png" alt="terceiro método">
<img src="https://github.com/lihviaa/Infix-to-Postfix-Arithmetic-Expression-Calculator/blob/main/assets/execucao_ExpressEval.png" alt="quarto método">
<img src="https://github.com/lihviaa/Infix-to-Postfix-Arithmetic-Expression-Calculator/blob/main/assets/execucao_exit.png" alt="quinto método">



## References
- https://www.geeksforgeeks.org/generics-in-java/
- https://www.baeldung.com/java-generic-array
- https://www.tutorialspoint.com/java/java_string_matches.htm
- https://www.digitalocean.com/community/tutorials/java-convert-string-to-double
- https://www.guj.com.br/t/exception-in-thread-main-java-util-nosuchelementexception-no-line-found/343767
- https://mkyong.com/java/java-how-to-get-keys-and-values-from-map/
