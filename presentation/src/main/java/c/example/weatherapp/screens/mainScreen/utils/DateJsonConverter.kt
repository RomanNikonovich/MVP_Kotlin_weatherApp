package c.example.weatherapp.screens.mainScreen.utils

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.util.*
/**
 * converting the date coming in milliseconds from Json
 */
class DateJsonConverter : JsonDeserializer<Calendar> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Calendar {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = json?.asLong!! * 1000
        return calendar
    }
}