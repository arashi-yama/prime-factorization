package com.example.primefactorization

import android.graphics.Point
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.concurrent.thread
import kotlin.math.floor
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    var clacing=false
    val handler=Handler(Looper.getMainLooper())
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.zero).setOnClickListener(BtClick())
        findViewById<Button>(R.id.one).setOnClickListener(BtClick())
        findViewById<Button>(R.id.two).setOnClickListener(BtClick())
        findViewById<Button>(R.id.three).setOnClickListener(BtClick())
        findViewById<Button>(R.id.four).setOnClickListener(BtClick())
        findViewById<Button>(R.id.five).setOnClickListener(BtClick())
        findViewById<Button>(R.id.six).setOnClickListener(BtClick())
        findViewById<Button>(R.id.seven).setOnClickListener(BtClick())
        findViewById<Button>(R.id.eight).setOnClickListener(BtClick())
        findViewById<Button>(R.id.nine).setOnClickListener(BtClick())
        findViewById<Button>(R.id.delete).setOnClickListener(BtClick())
        findViewById<Button>(R.id.deleteall).setOnClickListener(BtClick())
    }

    fun pf(input:String):List<Long>{
        var n=parseStr(input)
        var i:Long=2
        var result=mutableListOf<Long>()
        while(n>1){
            if(n%i==0L){
                result+=i
                n/=i
                i=1
            }
            i++
        }
        return result.toList()
    }

    fun parseStr(input:String):Long{
        if(input.length>=20)throw Throwable("")
        if(input.length<=10)return input.toLong()
        val str=input.reversed()
        val seg1=str.substring(0,10).reversed().toLong()
        val seg2=str.substring(10).reversed().toLong()
        return seg2*10000000000+seg1
    }

    fun List<Any>.indexesOf(int:Int):List<Int>{
        var result=mutableListOf<Int>()
        for(i in 0..this.size-1){
            if(this[i] as Int==int){
                result+=i
            }

        }
        return result.toList()
    }

    inner class BtClick: View.OnClickListener{
        override fun onClick(view:View){
            if(clacing){
                Toast.makeText(this@MainActivity,"計算中",Toast.LENGTH_SHORT).show()
                return
            }
            clacing=true
            var tv=findViewById<TextView>(R.id.result)
            var tvi=findViewById<TextView>(R.id.inp)
            var num=tvi.text.toString()
            var c=""
            when(view.id) {
                R.id.zero -> c = "0"
                R.id.one -> c = "1"
                R.id.two -> c = "2"
                R.id.three -> c = "3"
                R.id.four -> c = "4"
                R.id.five -> c = "5"
                R.id.six -> c = "6"
                R.id.seven -> c = "7"
                R.id.eight -> c = "8"
                R.id.nine -> c = "9"
            }
            if(view.id===R.id.delete){
               if(num!="") num=num.substring(0,num.length-1)
                if(num=="")num="0"
            }else if(view.id===R.id.deleteall){
                num="0"
            }else if(num=="0")num=c
            else num+=c
            if(!(c=="0"&&num=="0"))tvi.text=num
            var ex = findViewById<TextView>(R.id.exresult)
            if (c == "") {
                ex.text = ""
            }
            thread {
                var result = if (num == "" || num == "0" || num == "1") listOf(num) else {
                    var res = listOf<Long>()
                    try {
                        res = pf(num)
                    } catch (e: Throwable) {
                        println(e)
                        Toast.makeText(this@MainActivity, "計算範囲を超過しました $e", Toast.LENGTH_SHORT)
                            .show()
                    }
                    res
                }
                tv.text = "${result.joinToString("*")}"
                var tei = result.toSet().toList()
                var sisu = mutableListOf<Int>()
                for (i in 0..tei.size - 1) {
                    sisu += result.filter { it == tei[i] }.size
                }
                var texts = mutableListOf<String>()
                for (i in 0..tei.size - 1) {
                    texts += "(${tei[i]}^${sisu[i]})"
                }
                handler.post{
                    var ex=findViewById<TextView>(R.id.exresult)
                    ex.text = texts.joinToString("*")
                    clacing=false
                }
            }
        }
    }
}
