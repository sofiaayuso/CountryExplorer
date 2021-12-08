package com.example.countryexplorer.countryexplorer

//    private fun getEmojiByUnicode(unicode: String): String {
//        //TODO: something here to get displayable emoji string from the API given "flag" string
//        //return String(Character.toChars(unicode))
//    }

fun String?.getEmojiByUnicode(): String? {
    return this?.lowercase()
//    return String(Character.toChars(this))
}

