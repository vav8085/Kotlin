package com.vav.KotlinInAction.Chapter4

import java.net.URI

//two secondary constructors, no primary
open class Downloader {
    constructor(url: String){

    }

    constructor(uri: URI){

    }
}

//if you have 1 primary and 1 secondary constructor then secondary must call primary using this()
class Downloader1 constructor(url: String){

    constructor(uri: URI) : this(uri.host) {

    }
}

//if we extend from a class then we must call its constructor
//if we have 2 constructors then each constructor must call a constructor of super class
//or if one constructor calls a constructor of super class then other constructor must call this constructor
//at the end super class constructor must be called by both constructors directly or indirectly.
class Downloader2(url: String) : Downloader(url) {
    constructor(uri: URI) : this(uri.host) {

    }
}