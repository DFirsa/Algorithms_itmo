package com.company.Spring.lab6

import java.io.File
import java.io.PrintWriter

var writer: PrintWriter = PrintWriter(File("prefix.out"))
val reader = File("prefix.in").bufferedReader()

fun printPrefix(original: String){
    var prefix = Array(original.length, {0})

    var i = 1
    while (i < original.length){
        var k = prefix[i - 1]

        while (k > 0 && original[i] != original[k]) k = prefix[k-1]

        if (original[i] == original[k])k++
        prefix[i] = k

        i++
    }

    for (i in prefix){
        writer.print(i)
        writer.print(" ")
    }
}

fun main(args: Array<String>) {

    val str: String

    str = reader.readLine()

    printPrefix(str)

    reader.close()
    writer.close()
}