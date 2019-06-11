package uz.jamshid.newsapp.core.manager

import java.text.SimpleDateFormat
import java.util.*

class TimeManager {

    companion object{
        fun convertToTextTime(time: String): String{
            val newsDay = time.substring(0, 10)
            val newsTime = time.substring(11, time.length-4)
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            return if(sdf.format(Date()) == newsDay)
                "Today $newsTime"
            else "$newsDay $newsTime"
        }
    }
}