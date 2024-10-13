#include <iostream>
#include <stack>
#include <string>
#include <cctype>
#include <map>

using namespace std;

int getPriority(char op) {
    if (op == '+' || op == '-')
        return 1;
    else if (op == '*' || op == '/')
        return 2;
    else
        return 0;
}

bool isOperator(char ch) {
    return (ch == '+' || ch == '-' || ch == '*' || ch == '/');
}

string convertToRPN(const string& expression) {
    stack<char> operators;
    string rpn;

    for (size_t i = 0; i < expression.length(); i++) {
        char ch = expression[i];

        if (isalnum(ch)) {
            rpn += ch;
        } else if (isOperator(ch)) {
            while (!operators.empty() && isOperator(operators.top()) &&
                   getPriority(operators.top()) >= getPriority(ch)) {
                rpn += operators.top();
                operators.pop();
            }
            operators.push(ch);
        } else if (ch == '(') {
            operators.push(ch);
        } else if (ch == ')') {
            while (!operators.empty() && operators.top() != '(') {
                rpn += operators.top();
                operators.pop();
            }
            operators.pop();
        }
    }

    while (!operators.empty()) {
        rpn += operators.top();
        operators.pop();
    }

    return rpn;
}

double evaluateRPN(const string& rpn, const map<char, double>& variables) {
    stack<double> operands;

    for (size_t i = 0; i < rpn.length(); i++) {
        char ch = rpn[i];

        if (isalnum(ch)) {
            double value = variables.at(ch);
            operands.push(value);
        } else if (isOperator(ch)) {
            double operand2 = operands.top();
            operands.pop();
            double operand1 = operands.top();
            operands.pop();

            double result;
            switch (ch) {
                case '+':
                    result = operand1 + operand2;
                    break;
                case '-':
                    result = operand1 - operand2;
                    break;
                case '*':
                    result = operand1 * operand2;
                    break;
                case '/':
                    result = operand1 / operand2;
                    break;
            }

            operands.push(result);
        }
    }

    return operands.top();
}

int main() {
    string expression = "a/b-((c+d)*e)";

    cout << "Введите значение переменной a: ";
    double a;
    cin >> a;

    cout << "Введите значение переменной b: ";
    double b;
    cin >> b;

    cout << "Введите значение переменной c: ";
    double c;
    cin >> c;

    cout << "Введите значение переменной d: ";
    double d;
    cin >> d;

    cout << "Введите значение переменной e: ";
    double e;
    cin >> e;

    map<char, double> variables;
    variables['a'] = a;
    variables['b'] = b;
    variables['c'] = c;
    variables['d'] = d;
    variables['e'] = e;

    string rpn = convertToRPN(expression);
    cout << "Обратная польская запись: " << rpn << endl;

    double result = evaluateRPN(rpn, variables);
    cout << "Результат: " << result << endl;

    return 0;
}
