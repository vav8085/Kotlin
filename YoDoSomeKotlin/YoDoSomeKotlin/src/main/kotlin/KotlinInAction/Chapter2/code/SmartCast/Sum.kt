package com.vav.KotlinInAction.Chapter2.code.SmartCast

/*
    Sum class can be initialized as:
    Sum(left: Num, right: Num)

    But that will limit it to evaluate only single expression like:
        sum
      2     3

    (2 + 3)
    But notice that Num class also inherits from Expr so now we have
    Sum(left: Expr, right: Expr)

    The advantage of this is we can create complex expressions like:
        sum
      sum   2
     4   5

    (4 + 5) + 2
 */
class Sum(val left: Expr, val right: Expr): Expr