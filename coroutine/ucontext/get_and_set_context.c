/**
 * Wed Jul  8 22:50:01 CST 2020
 * Examples from https://en.wikipedia.org/wiki/Setcontext 
 * & https://blog.csdn.net/qq910894904/article/details/41911175
 *
 * Compile: gcc get_and_set_context.c -o get_and_set_context
 * Run: ./get_and_set_context
 */

#include <stdio.h>
#include <ucontext.h>
#include <unistd.h>

int main(int argc, const char *argv[]) {
	ucontext_t context;
	getcontext(&context);

	puts("Hello world");
	sleep(1);

	setcontext(&context);
	return 0;
}
