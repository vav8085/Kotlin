package com.vav.KotlinDesignPatterns.CreationalPatterns.FactoryPattern

/*
    The core idea of Factory pattern is creation of objects that implement same class / interface
    Factory method
    -   takes an input
    -   processes it
    -   returns a object based on processing
    -   This is where polymorphism kicks in and we can return all objects of that specific type.

    *   Factory pattern is usually used when parsing a file for example a json or xml. If input stream
    *   is read as JsonObject then an object of JsonObject type is returned and so on.
 */