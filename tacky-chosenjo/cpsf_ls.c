#include <stdio.h>
#include <stdlib.h>
#include <dirent.h>
#include <string.h>

int main(int argc, char** argv) {
    char pwd[256];
    DIR* dir;
    struct dirent* dp;

    strcpy(pwd, getenv("PWD"));

    dir = opendir(pwd);
    if (dir == NULL) {
        printf("error\n");
        return -1;
    }

    while (NULL != (dp = readdir(dir))) {
        printf("%s\n", dp->d_name);
    }
    closedir(dir);
    return 0;
}
