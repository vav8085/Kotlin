package com.vav.KotlinInAction.Chapter4

/*
    In kotlin interfaces can have properties but cannot have a state. i.e. they cant have data
    within these properties. The classes that implement these interfaces can have state for these
    properties.
 */
interface User{
    val nickname: String
}

/*
    As soon as you override User you will get 2 options
    1. implement nickname as a constructor param
    2. implement nickname as a field.

    If we implement nickname as a constructor param its a val
    and gets initialized as soon as class gets instantiated
    its a val so it will also have a getter.
 */

class PrivateUser(override val nickname: String) : User


/*
    If we implement nickname as a field then we have to implement its getter
    Why only getter and not setter too?

    This is because a field in kotlin may not store a value all the time.
    These fields are more like some getter methods in java. they just do some calculation
    and return a value. They don't store anything.

    This is why this field nickname does not have a backing field
 */
class SubscriberUser(private val email: String): User{
    override val nickname: String
        get() = email.split("@")[0]
    }

/*
    Now in this case we are calculating nickname from a method and storing its results in the nick name
    Notice 2 different ways to write:
    1. override val nickname = getNameFromAccount(). this is storing the value in the nickname
    2. override val nickname: String
        get() = email.split("@")[0]
    }
    in case of 1 there is a backing field to store "nickname" while in 2 there is no backing field.
 */
class SocialUser(private val account: Int): User{
    override val nickname = getNameFromAccount()

    private fun getNameFromAccount(): String{
        return "Name$account"
    }
}

/*
    Below could be done if nickname was a var but not a good practice.
    vals dont have setters...
 */
class SocialUser1(private val account: Int): User{
    override var nickname = ""
        set(value) { field = getNameFromAccount()}

    private fun getNameFromAccount(): String{
        return "Name$account"
    }
}





