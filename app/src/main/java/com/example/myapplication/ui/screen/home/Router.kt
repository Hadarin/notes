package com.example.myapplication.ui.screen.home

sealed interface Router {
    val route: String

    object Home : Router {
        override val route = "home"
    }

    object TestOne : Router {
        override val route = "testOne"
    }

    object TestTwo : Router {
        override val route = "testTwo"
    }

    object TestThree : Router {
        override val route = "testThree"
    }

    data object EditNote : Router {
        override val route = "edit_note/{noteId}"
        fun createRouteByNoteId(noteId: Int) = "edit_note/$noteId"
    }

}