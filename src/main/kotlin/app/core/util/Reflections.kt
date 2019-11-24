package app.core.util

import kotlin.reflect.KClass
import kotlin.reflect.full.allSupertypes
import kotlin.reflect.full.superclasses

class Reflections private constructor() {

    companion object {

//        fun <A : Any> argument(instance: Any, superType: KClass<*>, argument: Int) = instance::class.superclasses.first().allSupertypes.first { it.classifier == superType }.arguments[argument].type?.classifier as KClass<A>
        fun <A : Any> argument(instance: Any, superType: KClass<*>, argument: Int) = instance::class.allSupertypes.first { it.classifier == superType }.arguments[argument].type?.classifier as KClass<A>
    }
}