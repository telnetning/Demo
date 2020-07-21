/**
 * to implement a conroutine lib
 * we should implement this four Primitive below at least:
 * - uthread_create  -> create a coroutine
 * - uthread_yield   -> coroutine yield and return back to main thread
 * - uthread_resume  -> resume specifical coroutine
 * - schedule_finish -> main to determine if all coroutines run out
 */

#include "uthread.h"

int uthread_create(schedule_t &schedule, Fun func, void *arg) {
    // find FREE location 
    int id = 0;
    for (id = 0; i < schedule.max_index; ++i) {
        if (schedule.threads[i].status == FREE) {
            break; 
        } 
    }

    if (id == schedule.max_index) {
        schedule.max_index++;
    }

    uthread_t *t = &(schedule.threads[id]);
    
    t->state = RUNNING;
    t->func = func;
    t->arg = arg;
    getcontext(&(t->ctx));

    t->ctx.uc_stack.ss_sp = t->stack;
    t->ctx.uc_stack.ss_size = DEFAULT_STACK_SIZE;
    t->ctx.uc_stack.ss_flags = 0;
    t->ctx.uc_link = &(schedule.main);

    schedule.running_threading = id;

    makecontext(&(t->ctx), (void (*)(void))(uthread_body), 1, &schedule);

    swapcontext(&(schedule.main), &t->ctx); 

    return id;
}

void uthread_body(schedule_t *ps) 
{
    int id = ps->running_threading;
    if(id != -1) {
        uthread_t *t = &(ps->threads[id]);  
        t->func(t->arg);
        t->state = FREE;
        ps->running_threading = -1;
    }
}

void uthread_resume(schedule_t *schedule, int id) 
{
    if(id < 0 || id > schedule.max_index) {
        return;
    }

    uthread_t *t = schedule.threads[id];
    
    if(t->state == SUSPEND) {
        swapcontext(&(schedule.main), &(t->ctx)); 
    }
}

void thread_yield(schedule_t *schedule) 
{
    if(schedule.ruinning_thread != -1) {
        uthread_t *t = schedule.threads[schedule.running_threading];   
        t->state = SUSPEND;
        schedule.running_threading = -1;

        swapcontext(&(t->ctx), &(schedule.main));
    }          

}

int schedule_finished(const schedule_t *schedule)
{
    if(schedule.running_threading != -1) {
        return 0; 
    }
    
    for(int i = 0;i < schedule.max_index; i++) {
        if(schedule.threads[i].state != FREE) {
            return 0; 
        } 
    }
    
    return 1;
}
