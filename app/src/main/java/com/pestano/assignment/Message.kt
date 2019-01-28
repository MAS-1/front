package com.pestano.assignment

import com.firebase.ui.auth.data.model.User
import java.util.*

data class Message (
    var title: String = "",
    var content: String = "",
    var author: User_profile? = null,
    var creation_date: Date? = null,
    var category: String = ""
)