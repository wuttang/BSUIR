// variant 4

#include <stdio.h>
#include <stdlib.h>
 
int main()
{
    char str[256] = "1110000\0";
 
    printf("%s\n",str);
 
    for (int i = 0; str[i] != '\0'; i++)
    {
        int k = i;
        while (str[i] == str[i+1]) i++;
 
        if ((abs(k-i) % 2))
        {
            for (int t = k; t <= i; t++)
                printf("%c", str[t]);
 
            printf("\n");
        }
    }
 
    return 0;
}