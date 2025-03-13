package com.example.kidsnesia

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories("com.example.kidsnesia.repository")
@EntityScan("com.example.kidsnesia.entity")
class KidsnesiaApplication

annotation class EntityScan(val value: String)

fun main(args: Array<String>) {
	runApplication<KidsnesiaApplication>(*args)
}

