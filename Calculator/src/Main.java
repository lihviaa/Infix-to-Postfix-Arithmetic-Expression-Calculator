// Imports packages used
import java.util.*;
import java.lang.Math;

public class Main{
    public static void main(String[] args) throws InterruptedException {
        double result;
        char[] variable_vet = new char[0];
        boolean flag = true, exit = false;
        String user_input;
        int user_input_x;
        String expression = "";
        Pilha<Character> stack_1 = new Pilha<>();
        Scanner input = new Scanner(System.in);
        Scanner scan = new Scanner(System.in);
        Scanner scan_2 = new Scanner(System.in);
        Map<Character, Double> dictionary = new HashMap<>();
        Map<Character, Integer> priorities = new HashMap<>();
        priorities.put('^', 3);
        priorities.put('*', 2);
        priorities.put('/', 2);
        priorities.put('+', 1);
        priorities.put('-', 1);

        while (flag) {
            // Printing start menu
            System.out.print("\nWelcome to RPNSolver!\n01. Set Infix Arithmetic Expression " +
                    "\n02. Set variables values\n03. Infix to Postfix Conversion\n04. Expression Evaluation"+
                    "\n05. Exit\n\nSelect an option: ");

            // Reading user input
            try{
                user_input = input.nextLine();
                user_input_x = Integer.parseInt(user_input);
            }
            catch(Exception IllegalArgumentException){
                user_input = "0";
                user_input_x = Integer.parseInt(user_input);

            }

            switch (user_input_x) {
                case 1:
                    dictionary = new HashMap<>(); // The hashmap is recreated everytime the user submits a new expression
                    System.out.print("Type in the expression: ");
                    expression = scan.nextLine();
                    expression = expression.replaceAll("\\s", "");

                    if(option1(expression)){
                        System.out.println("Expression was successfully loaded!");
                        variable_vet = variableLoader(expression);
                    }
                    else{
                        System.out.println("Invalid expression submitted, please submit a valid expression!");
                        expression = "";
                        variable_vet = new char[0];

                    }
                    break;
                case 2:
                    if(variable_vet.length == 0){ // Checks whether an expression has been submitted using option 1
                        System.out.println("Please submit an expression using option 1!");
                    }
                    else{
                        dictionary = option2(variable_vet, scan_2);
                        System.out.println("Variables were successfully assigned!");
                    }
                    break;

                case 3:
                    if(variable_vet.length == 0){ // Checks whether an expression has been submitted using option 1
                        System.out.println("Please submit an expression using option 1!");
                        break;
                    }
                    if(dictionary.isEmpty()){ // Checks whether the operands' numerical values were submitted using option 2
                        System.out.println("Please submit the variable values using option 2!");
                        break;
                    }
                    stack_1 = infix_to_posfix(expression, priorities);
                    printPostfixExpression(stack_1);
                    break;

                case 4:
                    if(!stack_1.isEmpty()) { // Checks whether an expression has been converted to its postfix format
                        result = expression_assessment(dictionary, stack_1);
                        System.out.println("\nInfix expression: " + expression);
                        for(Map.Entry<Character, Double> entry: dictionary.entrySet()){
                            System.out.println(entry.getKey() + ": " + entry.getValue());
                        }
                        System.out.println("Result for the expression and values submitted: >>>> " + result);
                    }
                    else{
                        System.out.println("Please, make sure all the above options were correctly set up!");
                    }
                    break;

                case 5:
                    exit = true;
                    System.out.print("\nThank you for using RPN Solver!\n\nShutting down in ");
                    for (int i = 3; i > 0; i--) {
                        System.out.print(i + "... ");
                        Thread.sleep(1000);
                    }
                    break;

                default:
                    System.out.println("Please, insert a valid option!");
            }

            if(exit) {
                flag = false;
            }
        }
        input.close();
        scan.close();
        scan_2.close();
    }

    // Option 1
    public static boolean option1(String expression){   // Checks whether the expression contains only characters in the range of a-z, A-Z, parentheses and operators with the .matches method
        return expression.matches("[a-zA-Z*+-/^(){}\\[\\]]+") && verify(expression) && correctSequence(expression);
    }
    // Checks whether the expression's operators and operands are intercalated
    public static boolean correctSequence(String expression){
        int op_aux = 1, var_aux = 0;
        for(int i = 0; i < expression.length()-1; i++){
            if(expression.charAt(i) == '(' && expression.charAt(i+1) == ')'|| expression.charAt(i) == '{' && expression.charAt(i+1) == '}'|| expression.charAt(i) == '[' && expression.charAt(i+1) == ']'){
                return false;
            }
        }

        expression = expression.replaceAll("[(){}\\[\\]]", "");
        // Checks whether the submitted expression starts with an operand
        for(int i = 0; i < expression.length(); i++){
            switch(expression.charAt(i)){
                case('+'):
                case('-'):
                case('*'):
                case('/'):
                case('^'):
                    if(i == expression.length()-1){  // Checks whether the expression ends with an operator (parentheses not included)
                        return false;
                    }
                    op_aux++;
                    var_aux--;
                    break;
                default:
                    var_aux++;
                    op_aux--;
                    break;
            }

            if(op_aux < 0 || var_aux < 0){
                return false;
            }

        }
        return true;
    }

    // Checks whether the parentheses order is correct
    public static boolean verify(String texto) {
        Pilha<Character> pilha_1 = new Pilha<>(texto.length());
        for (int i = 0; i < texto.length(); i++) {
            try{
                switch (texto.charAt(i)) {
                    case '(':
                    case '[':
                    case '{':
                        pilha_1.push(texto.charAt(i));
                        break;
                    case ')':
                        if(pilha_1.pop() != '('){
                            return false;
                        }
                        else{
                            break;
                        }
                    case ']':
                        if(pilha_1.pop() != '['){
                            return false;
                        }
                        else{
                            break;
                        }
                    case '}':
                        if(pilha_1.pop() != '{'){
                            return false;
                        }
                        else{
                            break;
                        }
                }
            }
            catch(Exception IllegalArgumentException){
                return false;
            }
        }

        return pilha_1.isEmpty();
    }

    // Loads variables used in the expression into an array and removes duplicates
    public static char[] variableLoader(String expression){
        expression = expression.replaceAll("[^a-zA-Z]", "");
        char[] variable_vet = new char[expression.length()];
        for(int k = 0; k < expression.length(); k++){
            variable_vet[k] = expression.charAt(k);
        }
    // Removes duplicates
        for(int i = 0; i < variable_vet.length; i++){
            int j;
            for(j = i+1; j < variable_vet.length; j++){
                if(variable_vet[i] == variable_vet[j]){
                    variable_vet[j] = '\0';
                }
            }
        }

    // Counts the amount of empty spaces in the above char array
        int count = 0;
        for(int i = 0; i < variable_vet.length; i++){
            if(variable_vet[i] == '\0'){
                count++;
            }
        }

    // Creates a new char array with no null operators
        char[] variable_vet_unique = new char[variable_vet.length-count];
        for(int i = 0; i < variable_vet_unique.length; i++){
            if(variable_vet[i] != '\0'){
                variable_vet_unique[i] = variable_vet[i];
            }
        }
        return variable_vet_unique;
    }

    // Option 2
    public static Map<Character, Double> option2(char[] vet, Scanner scan_2){
        Map<Character, Double> dictionary = new HashMap<>();

        for (int i=0;i < vet.length;i++){
            String valor;

            System.out.print(vet[i] + ": ");
            valor = scan_2.nextLine();
            try{
                double aux = Double.parseDouble(valor);
                dictionary.put(vet[i], aux);
            }
            catch(Exception E){
                System.out.println("\nPlease type a double precision float number!\n");
                i--;
            }
        }
        return dictionary;
    }
    // Option 3
    public static Pilha<Character> infix_to_posfix(String str, Map<Character, Integer> priorities){
        Pilha<Character> stack_1 = new Pilha<>(str.length());
        Pilha<Character> stack_aux = new Pilha<>(str.length());

        for (int i = 0; i < str.length(); i++) {
            switch (str.charAt(i)) {
                case ('+'):
                case ('-'):
                case ('*'):
                case ('/'):
                case ('^'):
                    while (!stack_1.isEmpty() && priorities.getOrDefault(stack_1.top(), 0) >= priorities.getOrDefault(str.charAt(i), 0)) {
                        stack_aux.push(stack_1.pop());
                    }
                    stack_1.push(str.charAt(i));
                    break;
                case ('('):
                case ('['):
                case ('{'):
                    stack_1.push(str.charAt(i));
                    break;

                case (')'):
                    while (stack_1.top() != '(') {
                        stack_aux.push(stack_1.pop());
                    }
                    stack_1.pop();
                    break;

                case (']'):
                    while (stack_1.top() != '[') {
                        stack_aux.push(stack_1.pop());
                    }
                    stack_1.pop();
                    break;

                case ('}'):
                    while (stack_1.top() != '{') {
                        stack_aux.push(stack_1.pop());
                    }
                    stack_1.pop();
                    break;

                default:
                    stack_aux.push(str.charAt(i));
                    break;
            }
        }

        while (!stack_aux.isEmpty()) {
            stack_1.push(stack_aux.pop());
        }

        return stack_1;
    }

    // Prints the postfix expression
    public static void printPostfixExpression(Pilha<Character> stack_1){
        char aux;
        Pilha<Character> stack_aux = new Pilha<>(stack_1.count());
        System.out.println("\nPostfix expression: ");
        while(!stack_1.isEmpty()){
            aux = stack_1.pop();
            System.out.print(aux);
            stack_aux.push(aux);
        }
        System.out.print("\n");

        while(!stack_aux.isEmpty()){
            stack_1.push(stack_aux.pop());
        }
    }

    // Option 4

    public static double expression_assessment(Map<Character, Double> valores, Pilha<Character> stack_1){
        Pilha<Double> stack_4 = new Pilha<>(stack_1.count());
        double aux, aux_1, aux_2;

        while(!stack_1.isEmpty()){
            char next = stack_1.top();
            switch(next){
                case('+'):
                    stack_4.push(stack_4.pop() + stack_4.pop());
                    break;
                case('-'):
                    aux_1 = stack_4.pop();
                    aux_2 = stack_4.pop();
                    stack_4.push(aux_2 - aux_1);
                    break;
                case('*'):
                    stack_4.push(stack_4.pop() * stack_4.pop());
                    break;
                case('^'):
                    aux_1 = stack_4.pop();
                    aux_2 = stack_4.pop();
                    stack_4.push(Math.pow(aux_2, aux_1));
                    break;
                case('/'):
                    aux_1 = stack_4.pop();
                    aux_2 = stack_4.pop();
                    stack_4.push(aux_2 / aux_1);
                    break;
                default:
                    aux = valores.get(next);
                    stack_4.push(aux);
                    break;
            }
            stack_1.pop();
        }
        return stack_4.top();
    }
}
