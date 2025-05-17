package com.vav.KotlinInAction.Chapter14

/*
    *   Dispatchers determine where a coroutine will run.
    *   They dispatch a coroutine to a thread.
    *   It can run on background thread.
    *   It can also run on a thread pool.
    *   Coroutines are usually not bound to a specific thread.
    *   There come dispatchers into the picture.
    *   A coroutine can suspend in one thread and resume in another.
 */