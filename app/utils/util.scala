package utils

import java.util.Date

object util {
  def getAge(date: Date): Int = {
    val today = new Date().getTime
    val day = date.getTime
    val diff = today - day
    Math.floor(diff/60*60*24*365).toInt
  }

  def getDate():String = {
    import java.util.Calendar
    val now = Calendar.getInstance
    now.get(Calendar.DAY_OF_MONTH) + "/" +
    now.get(Calendar.MONTH) + "/" +
    now.get(Calendar.YEAR) + "/" + " " + now.get(Calendar.HOUR_OF_DAY) + ":" +
    now.get(Calendar.MINUTE) + ":" +
    now.get(Calendar.SECOND)
  }

}
