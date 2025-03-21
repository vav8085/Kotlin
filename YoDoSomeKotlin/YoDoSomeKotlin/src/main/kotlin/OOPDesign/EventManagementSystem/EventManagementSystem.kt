package com.vav.OOPDesign.EventManagementSystem

import java.time.LocalDateTime
import java.util.UUID


data class Notification(
    val id: String = UUID.randomUUID().toString(),
    val message: String,
    val event: String,
    val sentAt: LocalDateTime = LocalDateTime.now()
)

//updates the observer who overrides the update method
interface Observer{
    fun update(notification: Notification)
}

//the subject will be the event
interface Subject{
    fun subscribe(observer: Observer)
    fun unsubscribe(observer: Observer)
    fun notify(notification: Notification)
}

class Student: Observer{
    override fun update(notification: Notification) {
        println("message received ${notification.message}")
    }
}

class Event(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val venue: String,
): Subject{
    private val observers = mutableListOf<Observer>()

    override fun subscribe(observer: Observer) {
        observers.add(observer)
    }

    override fun unsubscribe(observer: Observer) {
        observers.remove(observer)
    }

    override fun notify(notification: Notification) {
        observers.forEach {
            it.update(notification)
        }
    }
}

class EventManagementSystem(val student: Student, val event: Event){

    val students = mutableListOf<Student>()
    val events = mutableListOf<Event>()


    fun subscribeToEvent(student: Student, event: Event){
        event.subscribe(student)
    }
}