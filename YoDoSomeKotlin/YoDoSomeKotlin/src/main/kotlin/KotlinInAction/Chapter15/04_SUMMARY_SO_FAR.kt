package com.vav.KotlinInAction.Chapter15

/*
    So far we learned about following things:
        1.  CoroutineScope - takes a CoroutineContext to create a new scope
        2.  coroutineScope - A suspend function that creates a new CoroutineScope and waits until all
                             child coroutines complete
        3.  CoroutineContext - Contains dispatcher, job and coroutine name. Every scope has its own context
        4.  Dispatcher - A part of context that determines where coroutine will run.
        5.  Job - Every time we create a scope instance, a context with Job is created along with it. Every time
                  we create launch{} or async{} inside a scope they are assigned their own child job ids.
        6.  launch{} - A coroutine builder that creates its own job in parents context. fire and forget
        7.  async{} -  A coroutine builder same as launch in terms of job and context but returns value back
        8.  withContext{} - is a suspend function and used to switch context and suspend execution of parent
                            until its block is complete.
 */
