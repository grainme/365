#include <stdio.h>
#include <stdlib.h>
#include "utils/utils.h"

// the command was used incorrectly.
// more infos: https://man.freebsd.org/cgi/man.cgi?query=sysexits
const int EX_USAGE = 64;

int main(int argc, char** argv) {
    // in C, the program's name counts, unlike JAVA.
    if (argc > 2) {
        printf("Usage: 365 <script>");
        exit(EX_USAGE);
    } else if (argc == 2) {
        // argv[0] is the program's name
        run_file(argv[1]);
    } else {
        run_prompt();
    }
    return 0;
}
