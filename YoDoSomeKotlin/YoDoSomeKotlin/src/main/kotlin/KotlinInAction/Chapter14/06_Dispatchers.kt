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

/*
    *   A coroutine inherits dispatcher from its parent
    *   This means its not required to create a new dispatcher every time when starting a new coroutine.
    *
 */

/*
    *   Default Dispatcher - Used for CPU intensive tasks like json parsing, running cpu intensive calculation like
                             creating some video or image processing.
                           - Dispatchers.Default is bound to CPU thread pools.
                           - Should not be used for blocking operations like file download
                           - Uses multiple threads to run our operation

    *   Main Dispatcher - Used for interacting with main thread.
                        - For operation such as rendering UI
                        - Android scopes such as viewModelScope and lifecycleScope default to Main dispatcher.
                        - Should not be used for blocking operations.

    *   IO Dispatcher - Used for long-running background blocking operations.
                      - Network calls
                      * Filesystem operations


    *   There are other dispatchers like
           - Dispatchers.Unconfined
           - Dispatchers.limitedParallelism
 */
