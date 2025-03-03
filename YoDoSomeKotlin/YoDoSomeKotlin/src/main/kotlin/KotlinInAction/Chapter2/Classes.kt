package com.vav.KotlinInAction.Chapter2

class Classes {
    fun createPlane(){
        val mig = Plane("Mig")
        mig.isFighterPlane = true
    }
}



//Equivalent of java class with no setter and only getter
class Person(val name: String)

//Equivalent of java class with a setter
class Car(var name: String)

//Classes can have properties which are not initialized in constructor
//for example we have a variable isFighterPlane which is false by default but
//can be assigned a value.
//the variable "field" is the backing value of "isFighterPlane"
//a backing value is private and actual value of the variable isFighterPlane
//the value is the parameter. This can be assigned to backing value
//its simply equivilant to java setter
//public void setFighterPlane(boolean value){ isFighterPlane = value; }
class Plane(val name: String){
    var isFighterPlane = false
        set(value) {
            field = value
        }
}

//the setter can also have conditional statements and business logic
//which can determine what should happen before assigning the value
//the to variable
class Plane1(val name: String){
    var canShoot = true
    var isFighterPlane = false
        set(value) {
            if(canShoot && value){
                field = true
            }else{
                field = false
            }
        }
}

//Setter can also have some business logic
class Person1(val name: String){
    var age = 20
        set(value) {
            if(value > 150) throw IllegalArgumentException()
            else
            field = value
        }
}


//we can also have our own backing field
class Person2(val name: String){
    private var _age = 20
    var age: Int
        get() = _age
        set(value) {
            if(value > 150) throw IllegalArgumentException()
            else
                _age = value
        }
}

//WHY A BACKING FIELD?

//BACKING FIELDS ARE GOOD IF WE WANT TO SEPARATE THE BUSINESS LOGIC FROM ACTUAL ASSIGNMENT
//TO THE VARIABLE. WE CAN DO THOUSANDS OF THINGS AND THEN ASSIGN IT TO BACKING FIELD

//OTHER REASON IS TO AVOID INFINITE LOOPS. Doing below will create an infinite loop
//var age: Int
//    set(value) {
//        age = value
//    }


//We can also have getter implementations where we do something before returning the value

//We can also have custom accessors



//********************
//CUSTOM ACCESSOR VS METHODS

//IN KOTLIN WE CAN HAVE CUSTOM ACCESSORS WHICH CAN BE A FIELD INSTEAD OF A METHOD LIKE IN JAVA
//E.G.
//in below example we have a field called isSquare, This could be a method in java
//but in kotlin this can be a field with a custom accessor

class Rectangle(private val width: Int, private val height: Int){
    val isSquare
        get() = width == height
}

/*
    This is another way to make a field with a private setter without using a backing field.
 */
class Triangle(private val side1: Int, private val side2: Int, private val side3: Int){
    var isoSceles = false
        get() = side1 == side2 || side2 == side3 || side3 ==side1
        private set
}



