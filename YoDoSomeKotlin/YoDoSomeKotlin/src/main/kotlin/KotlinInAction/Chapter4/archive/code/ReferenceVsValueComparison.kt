package com.vav.KotlinInAction.Chapter4.archive.code

class ReferenceVsValueComparison {

    /*
            EQUALS ==
     */
    /*
        In kotlin == works same as .equals() and compares the object value
        The behavior of == changes as we override .equals() on an object.

        In java we use .equals() to compare references. But in kotlin
        its used to compare values. In kotlin to compare references we
        use === and !==

        so if
        val a = Car("Mazda")
        val b = a

        then a === b will compare their references
        a == b will compare values.
     */

    /*
        HASHCODE

        We must always override hashCode whenever we override equals.
        If we dont override hashcode then two objects although equal may not map to
        the same key.
        So if we only implement equals but not hashcode then objects will
        map to different keys and we may get confuse why they are mapped to different
        keys because they are equal.

        So always override hashcode with equals.
     */
}