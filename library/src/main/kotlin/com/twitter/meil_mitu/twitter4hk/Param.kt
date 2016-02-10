package com.twitter.meil_mitu.twitter4hk

import java.util.*
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class Param<R, T, V>(
        private val map: HashMap<String, V>,
        private val key: String,
        private val converter: (T) -> V,
        private val deconverter: (V) -> T) : ReadWriteProperty<R, T?> {

    override fun setValue(thisRef: R, property: KProperty<*>, value: T?) {
        if (value == null) {
            if (map.containsKey(key)) {
                map.remove(key)
            }
        } else {
            map.put(key, converter(value))
        }
    }

    override fun getValue(thisRef: R, property: KProperty<*>): T? {
        if (map.containsKey(key)) {
            return null
        } else {
            return deconverter(map[key]!!)
        }
    }
}