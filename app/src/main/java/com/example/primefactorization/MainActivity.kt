package com.example.primefactorization

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
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
    inner class BtClick: View.OnClickListener{
        override fun onClick(view:View){
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
            }else if(view.id===R.id.deleteall){
                num=""
            }else num=num+c
            tvi.text=num
            var result =if(num==""||num=="0"||num=="1")listOf(num) else pf(num.toInt())
            tv.text="${result.joinToString("*")}"
            var ex=findViewById<TextView>(R.id.exresult)
            if(c==""){
                ex.text=""
                return
            }
            var tei=result.toSet().toList()
            var sisu= mutableListOf<Int>()
            for(i in 0..tei.size-1){
                sisu+=result.filter{it==tei[i]}.size
            }
            var texts=mutableListOf<String>()
            for(i in 0..tei.size-1){
                texts+="(${tei[i]}^${sisu[i]})"
            }
            ex.text=texts.joinToString("*")
        }
    }
}
fun pf(int:Int):MutableList<Int>{
    var num=int
    var result=mutableListOf<Int>()
    var j=1
    for(i in 0..int){
        j++
        if(num%j===0){
            num /= j
            result+=j
            j=1
        }
    }
    return result
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