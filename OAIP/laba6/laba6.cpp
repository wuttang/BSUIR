// variant 4

#include <iostream>  
#include <stdlib.h>

using namespace std;

int main()
{
    int j = 0,num = 0;
    char str[500]= "111100011001111100000";
    cout << "Строка: " << str << endl;

    for (int i=0; i<strlen(str); i++){
        if (str[i] == '1'){
            j++;
            num = j;
            if (str[i] == '0'){
                if(!(num%2)){
                    cout << num;
                } 
            }
        }
        /*else if (str[i] == '0'){
            if (!(num%2)){
                cout << num;
            }
        }*/
    }

    return 0;
}