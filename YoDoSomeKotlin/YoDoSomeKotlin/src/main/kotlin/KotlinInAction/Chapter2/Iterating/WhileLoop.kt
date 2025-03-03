package com.vav.KotlinInAction.Chapter2.Iterating

fun main(){
    val strOuter = "facebook"
    val strInner = "race"

    multipleWhileLoop(strInner, strOuter)
}

fun multipleWhileLoop(strInner: String, strOuter: String){
    var i = 0
    var j = 0

    outer@ while(i < strOuter.length){
        while (j < strInner.length){
            if(strOuter[i] == strInner[j]){
                println(strOuter[i])
                break //will break the inner loop
            }
            if(strOuter[i] == 'k'){
                println("K found")
                break@outer //will break the loop specified with tag@. In this case its outer@
            }
            j++
        }
        i++
    }
}