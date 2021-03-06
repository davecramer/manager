package com.xtuple

import org.springframework.dao.DataIntegrityViolationException

class ZoneController {

    def discoverService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [zoneInstanceList: Zone.list(params), zoneInstanceTotal: Zone.count()]
    }

    def create() {
        [zoneInstance: new Zone(params)]
    }

    def save() {
        def zoneInstance = new Zone(params)
        if (!zoneInstance.save(flush: true)) {
            render(view: "create", model: [zoneInstance: zoneInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'zone.label', default: 'Zone'), zoneInstance.id])
        redirect(action: "show", id: zoneInstance.id)
    }

    def show(Long id) {
        def zoneInstance = Zone.get(id)
        if (!zoneInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'zone.label', default: 'Zone'), id])
            redirect(action: "list")
            return
        }

        [zoneInstance: zoneInstance]
    }

    def edit(Long id) {
        def zoneInstance = Zone.get(id)
        if (!zoneInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'zone.label', default: 'Zone'), id])
            redirect(action: "list")
            return
        }

        [zoneInstance: zoneInstance]
    }

    def update(Long id, Long version) {
        def zoneInstance = Zone.get(id)
        if (!zoneInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'zone.label', default: 'Zone'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (zoneInstance.version > version) {
                zoneInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'zone.label', default: 'Zone')] as Object[],
                          "Another user has updated this Zone while you were editing")
                render(view: "edit", model: [zoneInstance: zoneInstance])
                return
            }
        }

        zoneInstance.properties = params

        if (!zoneInstance.save(flush: true)) {
            render(view: "edit", model: [zoneInstance: zoneInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'zone.label', default: 'Zone'), zoneInstance.id])
        redirect(action: "show", id: zoneInstance.id)
    }

    def delete(Long id) {
        def zoneInstance = Zone.get(id)
        if (!zoneInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'zone.label', default: 'Zone'), id])
            redirect(action: "list")
            return
        }

        try {
            zoneInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'zone.label', default: 'Zone'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'zone.label', default: 'Zone'), id])
            redirect(action: "show", id: id)
        }
    }

    def iptables() {
      NatInstance.findAll().each { natInstance ->
        discoverService.iptables(natInstance)
      }
  }
}
