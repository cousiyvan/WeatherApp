package com.learning.cousiyvan.weatherapp.domain.commands

public interface Command<out T> {
    fun execute(): T
}