package com.spark11e.bot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
final class MainApplication

fun main(args: Array<String>) {
	runApplication<MainApplication>(*args)
}
