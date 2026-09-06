package com.example.review.play

data class Booking(
    val id: Int,
    val destination: String,
    val pax: Int,
    val amount: Double,
)

val bookings = listOf(
    Booking(
        id = 1,
        destination = "Boracay",
        pax = 2,
        amount = 15000.0,
    ),
    Booking(
        id = 2,
        destination = "Palawan",
        pax = 4,
        amount = 32000.0,
    ),
    Booking(
        id = 3,
        destination = "Boracay",
        pax = 1,
        amount = 8000.0,
    ),
    Booking(
        id = 4,
        destination = "Siargao",
        pax = 3,
        amount = 21000.0,
    ),
    Booking(
        id = 5,
        destination = "Palawan",
        pax = 2,
        amount = 200.0,
    ),
)

val bookingMoreThanTwo = bookings.filter { it.pax > 2 }
val bookingDestination = bookings.map { it.destination }
val bookingTotalAmount = bookings.sumOf { it.amount }
val bookingHighestAmount = bookings.maxOf { it.amount }
val bookingRevenueRanking = bookings.sortedByDescending { it.amount }

fun main() {
    for (booking in bookings) {
        println(booking.pax > 2)
        println(booking.destination)
    }
//    val num1: Int = (a: Int, b: Int)
}