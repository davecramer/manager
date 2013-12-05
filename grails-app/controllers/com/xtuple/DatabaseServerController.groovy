package com.xtuple

import org.springframework.dao.DataIntegrityViolationException

class DatabaseServerController
{

  static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

  def index()
  {
    redirect(action: "list", params: params)
  }

  def list(Integer max)
  {
    params.max = Math.min(max ?: 10, 100)
    [databaseServerInstanceList: DatabaseServer.list(params), databaseServerInstanceTotal: DatabaseServer.count()]
  }

  def create()
  {
    [databaseServerInstance: new DatabaseServer(params)]
  }

  def save()
  {
    def databaseServerInstance = new DatabaseServer(params)
    if (!databaseServerInstance.save(flush: true))
    {
      render(view: "create", model: [databaseServerInstance: databaseServerInstance])
      return
    }

    flash.message = message(code: 'default.created.message', args: [message(code: 'databaseServer.label', default: 'DatabaseServer'), databaseServerInstance.id])
    redirect(action: "show", id: databaseServerInstance.id)
  }

  def show(Long id)
  {
    def databaseServerInstance = DatabaseServer.get(id)
    if (!databaseServerInstance)
    {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'databaseServer.label', default: 'DatabaseServer'), id])
      redirect(action: "list")
      return
    }

    [databaseServerInstance: databaseServerInstance]
  }

  def edit(Long id)
  {
    def databaseServerInstance = DatabaseServer.get(id)
    if (!databaseServerInstance)
    {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'databaseServer.label', default: 'DatabaseServer'), id])
      redirect(action: "list")
      return
    }

    [databaseServerInstance: databaseServerInstance]
  }

  def update(Long id, Long version)
  {
    def databaseServerInstance = DatabaseServer.get(id)
    if (!databaseServerInstance)
    {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'databaseServer.label', default: 'DatabaseServer'), id])
      redirect(action: "list")
      return
    }

    if (version != null)
    {
      if (databaseServerInstance.version > version)
      {
        databaseServerInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                [message(code: 'databaseServer.label', default: 'DatabaseServer')] as Object[],
                "Another user has updated this DatabaseServer while you were editing")
        render(view: "edit", model: [databaseServerInstance: databaseServerInstance])
        return
      }
    }

    databaseServerInstance.properties = params

    if (!databaseServerInstance.save(flush: true))
    {
      render(view: "edit", model: [databaseServerInstance: databaseServerInstance])
      return
    }

    flash.message = message(code: 'default.updated.message', args: [message(code: 'databaseServer.label', default: 'DatabaseServer'), databaseServerInstance.id])
    redirect(action: "show", id: databaseServerInstance.id)
  }

  def delete(Long id)
  {
    def databaseServerInstance = DatabaseServer.get(id)
    if (!databaseServerInstance)
    {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'databaseServer.label', default: 'DatabaseServer'), id])
      redirect(action: "list")
      return
    }

    try
    {
      databaseServerInstance.delete(flush: true)
      flash.message = message(code: 'default.deleted.message', args: [message(code: 'databaseServer.label', default: 'DatabaseServer'), id])
      redirect(action: "list")
    }
    catch (DataIntegrityViolationException e)
    {
      flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'databaseServer.label', default: 'DatabaseServer'), id])
      redirect(action: "show", id: id)
    }
  }
}
