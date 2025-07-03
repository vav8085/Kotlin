package com.vav.KotlinInAction.Chapter2.archive.code.SmartCast


/*
*  This can be very helpful during object oriented design
*
*   Expr has 2 implementations, Num and Sum. So if Expr is a Num then we can return its numeric
*   value. and if its an operation like Sum then we can calculate the left and right expressions
*   recursively and return their final operation which could be Sum, Product...
* */
interface Expr