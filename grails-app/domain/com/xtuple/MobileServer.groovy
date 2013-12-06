package com.xtuple

class MobileServer extends Server {


    DatabaseServer databaseServer
    Boolean replica =false

    static belongsTo = [zone:Zone]
    static hasMany = [replicas:MobileServer]

    static constraints = {
    }


}
