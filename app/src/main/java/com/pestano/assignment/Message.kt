package com.pestano.assignment

import java.sql.Timestamp

data class Message(
    var title: String,
    var content: String,
    var author: String,
    var timestamp: Timestamp,
    var category: String
)
