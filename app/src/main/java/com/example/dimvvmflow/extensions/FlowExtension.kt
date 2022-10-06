package com.example.dimvvmflow.extensions

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*

//Extension fun
inline fun <reified T, R> CoroutineScope.combineStates(
    vararg flows: StateFlow<T>,
    sharedStarted: SharingStarted = SharingStarted.WhileSubscribed(),
    crossinline transform: (Array<T>) -> R
): StateFlow<R> = combine(flows = flows) {
    transform.invoke(it)
}.stateIn(this, sharedStarted,
    transform.invoke(flows.map { it.value }.toTypedArray())
)

