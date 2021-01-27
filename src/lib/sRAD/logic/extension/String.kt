package lib.sRAD.logic

import java.lang.NumberFormatException

fun String.isDouble(): Boolean {
    return try {
        this.toDouble()
        true
    }catch (numberFormat: NumberFormatException){
        false
    }
}

fun String.isInt(): Boolean {
    return try {
        this.toInt()
        true
    }catch (numberFormat: NumberFormatException){
        false
    }
}
