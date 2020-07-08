/**
 * Wed Jul  8 23:16:24 CST 2020
 *
 * Example from https://blog.csdn.net/qq910894904/article/details/41911175
 *
 * Compile: gcc make_and_swap_context.c -o make_and_swap_context
 * Run: ./make_and_swap_context
 */

#include <ucontext.h>
#include <stdio.h>

void foo() {
    puts("during foo func");
}

void context_test() {
    char stack[1024*128];
    ucontext_t child,main;

    getcontext(&child);
    child.uc_stack.ss_sp = stack; //set stack
    child.uc_stack.ss_size = sizeof(stack);
    child.uc_stack.ss_flags = 0;
    
    /*
     * set uc_link to &main
     * output: 
     *      during foo func
     *      main
     */

    // child.uc_link = &main; 
    
    /*
     * set uc_link to NULL
     * output:
     *      during foo func
     *
     * User's child foo func task run end and then exit, 
     * not return back to main 
     */
    child.uc_link = NULL; 

    makecontext(&child, (void (*)(void))foo, 0);
    
    swapcontext(&main, &child);

    puts("main");
}

int main() {
    context_test();

    return 0;
}
