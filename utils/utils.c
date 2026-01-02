#include "utils.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void run_file(char* path) {
    FILE* file = fopen(path, "r");
    if (file == NULL) {
        fprintf(stderr, "error: file does not exist");
        return;
    }

    char* buffer = malloc(100 * sizeof(char));
    char c;
    int i = 0;
    while (fread(&c, sizeof(char), 1, file)) {
        buffer[i++] = c;
    }

    fclose(file);
    printf("String is: %s\n", buffer);
    return;
}

void run_prompt() {
    printf("run prompt...");
}
