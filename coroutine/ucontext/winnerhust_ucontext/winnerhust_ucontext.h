/**
 * schedule_t
 *    |
 *    |-> vector<uthread_t> Thread_vector
 *                  |       
 *                  |-> {ucontext_t ctx; Fun fuc; void *arg...}
 */

// Define a coroutine struct

#ifndef MY_UTHREAD_H
#define MY_UTHREAD_H
#include <ucontext.h>
#include <vector>

#define DEFAULT_STACK_SIZE (1024*128)
#define MAX_UTHREAD_SIZE   1024

enum ThreadState{FREE,RUNNABLE,RUNNING,SUSPEND};

typedef void (*Fun)(void *arg);

typedef struct uthread_t
{
    ucontext_t ctx; // coroutine context
    Fun func; // coroutine running func
    void *arg; // func arguments
    enum ThreadState state; // FREE,RUNNABLE,RUNNING,SUSPEND
    char stack[DEFAULT_STACK_SIZE];
} uthread_t;

// Define scheduler struct

typedef std::vector<uthread_t> Thread_vector;
typedef struct schedule_t
{
    ucontext_t main; // main, in many projects, it is called eventloop
    int running_threading; // the running thread No.
    Thread_vector threads; 
    int max_index; // current max index

    schedule_t():running_threading(-1), max_index(0){
        threads = new thread_vector[MAX_UTHREAD_SIZE];       
        for(int i = 0; i < max_index; i++) {
            threads[i].state = FREE; 
        }
    } // struct constructor func: this.running_threading = -1

    ~schedule_t() {
        delete [] threads; 
    }

} schedule_t;

// keep running in schedule.Is it meaningful?
static void uthread_body(schedule_t *ps);

/**
 * to implement a conroutine lib
 * we should implement this four Primitive below at least:
 * - uthread_create  -> create a coroutine
 * - uthread_yield   -> coroutine yield and return back to main thread
 * - uthread_resume  -> resume specifical coroutine
 * - schedule_finish -> main to determine if all coroutines run out
 */
int  uthread_create(schedule_t &schedule,Fun func,void *arg);

void uthread_yield(schedule_t &schedule);

void uthread_resume(schedule_t &schedule,int id);

int  schedule_finished(const schedule_t &schedule);

#endif
