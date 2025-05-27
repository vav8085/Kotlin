package com.vav.KotlinInAction.Chapter17

/*
    *   Just like collections have operators flows also have operators that can transform them.
    *   Flows are similar to sequences where we have intermediate operators and terminal operators.

    *   When an intermediate operator is applied on a flow it divides a flow into 2 parts:
        1.  Upstream flow - This is the flow which is operated on, untl the intermediate operator
        2.  Downstream Flow - This is the flow as a result of the operator

    *   A downstream flow can be an upstream flow for next operator.
    *   Most intermediate operators like map, filter and onEach which work on collections and streams
        are available on flows as well
    *   There are also some operators which are specific to flows.
 */