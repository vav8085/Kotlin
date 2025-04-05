package com.vav.KotlinInAction.Chapter4

/*
    When we inherit from a class and override its methods our code
    becomes dependent on this class and these methods.

    When new methods are added to the base class or existing methods
    and changed then our code can break or perform different.

    In kotlin thats why all classes are final by default. you have
    to explicitly mention classes and open

    So you can extend from open classes and if any change is made to
    the base class then you have to make sure child classes are updated
    to avoid any issues in the child classes.

    **********
    But sometimes you want to add methods to the classes which are not open.
    How do we do this?
    One way to achieve this is through composition instead of inheritance.
    An example of this is decorator pattern.

    The way to achieve this is by implementing the same interface that the
    final class that we want to add methods to is implementing in another class.
    So interface I is implemented by final class F and another class B

    Now B will keep an instance of final class F and in all the methods that dont need
    a change B will simply call methods of this final class F.
 */
class ClassesDelegation {

}