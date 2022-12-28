package com.example.android12

class UserModel {

    var id = 0
    lateinit var name:String
    lateinit var surname:String
    lateinit var address:String

    constructor(id: Int, name: String, surname: String, address: String) {
        this.id = id
        this.name = name
        this.surname = surname
        this.address = address
    }

    constructor()
}