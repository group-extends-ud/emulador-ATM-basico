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