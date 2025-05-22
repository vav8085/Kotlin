package com.vav.KotlinInAction.Chapter7

/*
    *   Kotlin provides null safety at compile time. This avoids NPE which is most common in Java.
    *   This is done by having variables nullable and not nullable.
    *   A not nullable variable does not need null check because it cannot have a null value.
    *   Even for a nullable type Kotlin provides various ways to prevent NPE
    *   Values that can be null can be marked with a ?
    *   Its always a best practice to avoid as many nullable type as possible.
    *   You can call methods on nullable type only using either safe call ?. or not null asserted call !!
    *   You cant assign a value of a nullable type to a not nullable reference
    *   You can put a null check and after a null check compiler remembers it and you can use variable without safe call
        if(s != null) s.length else 0
    *   AT RUNTIME THE OBJECTS OF NULLABLE TYPE AND CORRESPONDING NOT NULLABLE TYPE ARE SAME.
    *   val a = "b"?.upperCase() ---if not null---> B
                                 |--if null-------> null
    *   ?: elvis operator can also be used to throw exception
        val other = otherPerson as? Person ?: throw ClassCastException("not a Person")
        val other = otherPerson as? Person ?: return false
    *   Elvis is not just to provide an alternate value. The expression on right of elvis is executed once expression on
        left evaluates to null. Since right side is an expression, it can throw exceptions and also return value.

    *   variable?.let{
                      //if case expression }
                      ?: run{
                        //else case expression
                      }
        val newVariable = variable?.let{
                      //if case expression / value }
                      ?: //else case value
    *   lateinit initializers are used when we want to initialize a not null property after its declaration.
        anything not lateinit should either be null or be initialized during declaration.


    *   Extensions on nullable types: Unlike normal functions that work on objects, extension functions can be called
        on nullable types without a safe call. We have functions like isBlank() and isNullOrBlank() which are both
        extension functions on String? and dont need a safe call.
        Null variables can be passed in the reciever and handled there

        fun String?.isNullOrBlank(): Boolean = this == null || this.isBlank()

    *   By default all generic types <T> in kotlin are nullable
 */