package com.example.messenger.api

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class MessengerApiApplication

fun main(args: Array<String>) {
	SpringApplication.run(MessengerApiApplication::class.java, *args)
}
