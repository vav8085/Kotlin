package com.vav.KotlinInAction.Chapter14

/*
    Concurrency vs Parallelism

    Concurrent operations appear to happen at the same time, but they may not be happening at the same time
    internally. An example of this is single CPU core executing multiple operations by switching between them
    back and forth.
    draw -> download -> draw -> download --->

    Meanwhile, Parallelism is tasks getting executed on multiple CPU cores at the same time.
    draw -> draw -> draw -> draw            CORE1
    download -> download -> download        CORE2

    With Coroutines, we can do both Concurrent operations and Parallel Processing.
 */