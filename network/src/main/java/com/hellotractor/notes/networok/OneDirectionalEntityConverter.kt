package com.hellotractor.notes.networok

interface OneDirectionalEntityConverter<L, R> {

    fun from(entity: L): R
}
