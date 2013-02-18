package com.xtuple

class MobileServer extends Server {


    DatabaseServer databaseServer
    Boolean replica =false

    static hasMany = [replicas:MobileServer]

    static constraints = {
    }
}
