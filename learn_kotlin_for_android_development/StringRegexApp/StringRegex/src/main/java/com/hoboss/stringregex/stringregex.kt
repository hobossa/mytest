package com.hoboss.stringregex

operator fun String.rem(re:String):Boolean =
    this.matches(Regex(re))