package com.vav.KotlinInAction.Chapter15

import kotlinx.coroutines.*

/*
    *   Usually when you create a Job or create a CoroutineScope with a dispatcher, All the child coroutines
        created using launch{} and async{} have their own child Jobs.
    *   If a child coroutine fails due to an exception then parent coroutine gets canceled and all the other child coroutines
        also get canceled.
    *   SupervisorJob is a special Job where if a child coroutine fails then
    *   Supervisor Job can also be created using supervisorScope{} builder
 */

val mySupervisedScope = CoroutineScope(Dispatchers.Default + SupervisorJob() + CoroutineName("MySupervisedScope"))


//Its important to call join() otherwise runblocking will execute the try block much before the two coroutines will complete
fun main() = runBlocking {
    try {
        val job1 = mySupervisedScope.launch {
            println("Child 1 starting")
            delay(100)
            throw RuntimeException("Child 1 failed")
            println("child 1 this wont print")
        }

        val job2 = mySupervisedScope.launch {
            try {
                println("Child 2 starting")
                delay(500)
                println("Child 2 still running")
            } finally {
                println("Child 2 finally")
            }
        }
        job1.join()
        job2.join()
    }catch (e: RuntimeException){
        println(e.localizedMessage)
    }

    println("mySupervisedScope still running")
    mySupervisedScope.cancel()
    println("mySupervisedScope finished")
}

//Child 1 starting
//Child 2 starting


//Exception in thread "DefaultDispatcher-worker-1" java.lang.RuntimeException: Exception while trying to handle coroutine exception
//	at kotlinx.coroutines.CoroutineExceptionHandlerKt.handlerException(CoroutineExceptionHandler.kt:33)
//	at kotlinx.coroutines.internal.CoroutineExceptionHandlerImpl_commonKt.handleUncaughtCoroutineException(CoroutineExceptionHandlerImpl.common.kt:38)
//	at kotlinx.coroutines.CoroutineExceptionHandlerKt.handleCoroutineException(CoroutineExceptionHandler.kt:28)
//	at kotlinx.coroutines.StandaloneCoroutine.handleJobException(Builders.common.kt:190)
//	at kotlinx.coroutines.JobSupport.finalizeFinishingState(JobSupport.kt:228)
//	at kotlinx.coroutines.JobSupport.tryMakeCompletingSlowPath(JobSupport.kt:907)
//	at kotlinx.coroutines.JobSupport.tryMakeCompleting(JobSupport.kt:864)
//	at kotlinx.coroutines.JobSupport.makeCompletingOnce$kotlinx_coroutines_core(JobSupport.kt:829)
//	at kotlinx.coroutines.AbstractCoroutine.resumeWith(AbstractCoroutine.kt:97)
//	at kotlin.coroutines.jvm.internal.BaseContinuationImpl.resumeWith(ContinuationImpl.kt:46)
//	at kotlinx.coroutines.DispatchedTask.run(DispatchedTask.kt:104)
//	at kotlinx.coroutines.scheduling.CoroutineScheduler.runSafely(CoroutineScheduler.kt:585)
//	at kotlinx.coroutines.scheduling.CoroutineScheduler$Worker.executeTask(CoroutineScheduler.kt:802)
//	at kotlinx.coroutines.scheduling.CoroutineScheduler$Worker.runWorker(CoroutineScheduler.kt:706)
//	at kotlinx.coroutines.scheduling.CoroutineScheduler$Worker.run(CoroutineScheduler.kt:693)
//	Suppressed: java.lang.RuntimeException: Child 1 failed
//		at com.vav.KotlinInAction.Chapter15._05_SupervisorJobKt$main$1$job1$1.invokeSuspend(05_SupervisorJob.kt:21)
//		at kotlin.coroutines.jvm.internal.BaseContinuationImpl.resumeWith(ContinuationImpl.kt:33)
//		... 5 more
//Caused by: java.lang.NoClassDefFoundError: android/os/Build$VERSION
//	at kotlinx.coroutines.android.AndroidExceptionPreHandler.handleException(AndroidExceptionPreHandler.kt:43)
//	at kotlinx.coroutines.internal.CoroutineExceptionHandlerImpl_commonKt.handleUncaughtCoroutineException(CoroutineExceptionHandlerImpl.common.kt:34)
//	... 13 more
//Caused by: java.lang.ClassNotFoundException: android.os.Build$VERSION
//	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(BuiltinClassLoader.java:641)
//	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(ClassLoaders.java:188)
//	at java.base/java.lang.ClassLoader.loadClass(ClassLoader.java:525)
//	... 15 more
//Exception in thread "DefaultDispatcher-worker-1" java.lang.RuntimeException: Child 1 failed
//	at com.vav.KotlinInAction.Chapter15._05_SupervisorJobKt$main$1$job1$1.invokeSuspend(05_SupervisorJob.kt:21)
//	at kotlin.coroutines.jvm.internal.BaseContinuationImpl.resumeWith(ContinuationImpl.kt:33)
//	at kotlinx.coroutines.DispatchedTask.run(DispatchedTask.kt:104)
//	at kotlinx.coroutines.scheduling.CoroutineScheduler.runSafely(CoroutineScheduler.kt:585)
//	at kotlinx.coroutines.scheduling.CoroutineScheduler$Worker.executeTask(CoroutineScheduler.kt:802)
//	at kotlinx.coroutines.scheduling.CoroutineScheduler$Worker.runWorker(CoroutineScheduler.kt:706)
//	at kotlinx.coroutines.scheduling.CoroutineScheduler$Worker.run(CoroutineScheduler.kt:693)
//	Suppressed: kotlinx.coroutines.internal.DiagnosticCoroutineContextException: [CoroutineName(MySupervisedScope), StandaloneCoroutine{Cancelling}@1234ae4, Dispatchers.Default]


//Child 2 still running
//Child 2 finally
//mySupervisedScope still running
//mySupervisedScope Finished