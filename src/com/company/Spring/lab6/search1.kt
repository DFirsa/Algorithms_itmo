package com.company.Spring.lab6

import java.io.File
import java.io.PrintWriter
import java.nio.charset.Charset

var positions = arrayListOf<Int>()

fun findSubstring(str: String, substr: String, position: ArrayList<Int>){
    var hasSubstr: Boolean
    var i: Int = 0

    while (i <(str.length - substr.length + 1)){

        hasSubstr = true
        var j:Int = 0

        while (j < substr.length){
            if (str.get(i+j) != substr.get(j)){
                hasSubstr = false
                break
            }
            j++
        }

        i++

        if (hasSubstr){

            position.add(i)
        }
    }
}

fun main(args: Array<String>) {

    var writer: PrintWriter = PrintWriter(File("search1.out"))
    val reader = File("search1.in").bufferedReader()

    val substr: String
    val str:String

    substr = reader.readLine()
    str = reader.readLine()

    findSubstring(str,substr, positions)

    writer.println(positions.size)

    for (i in positions){
        writer.print(i)
        writer.print(" ")
    }

    writer.close()
    reader.close()
}