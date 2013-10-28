package com.xtuple

import org.springframework.dao.DataIntegrityViolationException

class MobileServerController
{

  static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

  def index()
  {
    redirect(action: "list", params: params)
  }

  def list(Integer max)
  {
    params.max = Math.min(max ?: 10, 100)
    [mobileServerInstanceList: MobileServer.list(params), mobileServerInstanceTotal: MobileServer.count()]
  }

  def create()
  {
    [mobileServerInstance: new MobileServer(params)]
  }

  def save()
  {
    def mobileServerInstance = new MobileServer(params)
    if (!mobileServerInstance.save(flush: true))
    {
      render(view: "create", model: [mobileServerInstance: mobileServerInstance])
      return
    }

    flash.message = message(code: 'default.created.message', args: [message(code: 'mobileServer.label', default: 'MobileServer'), mobileServerInstance.id])
    redirect(action: "show", id: mobileServerInstance.id)
  }

  def show(Long id)
  {
    def mobileServerInstance = MobileServer.get(id)
    if (!mobileServerInstance)
    {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'mobileServer.label', default: 'MobileServer'), id])
      redirect(action: "list")
      return
    }

    [mobileServerInstance: mobileServerInstance]
  }

  def edit(Long id)
  {
    def mobileServerInstance = MobileServer.get(id)
    if (!mobileServerInstance)
    {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'mobileServer.label', default: 'MobileServer'), id])
      redirect(action: "list")
      return
    }

    [mobileServerInstance: mobileServerInstance]
  }

  def update(Long id, Long version)
  {
    def mobileServerInstance = MobileServer.get(id)
    if (!mobileServerInstance)
    {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'mobileServer.label', default: 'MobileServer'), id])
      redirect(action: "list")
      return
    }

    if (version != null)
    {
      if (mobileServerInstance.version > version)
      {
        mobileServerInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                [message(code: 'mobileServer.label', default: 'MobileServer')] as Object[],
                "Another user has updated this MobileServer while you were editing")
        render(view: "edit", model: [mobileServerInstance: mobileServerInstance])
        return
      }
    }

    mobileServerInstance.properties = params

    if (!mobileServerInstance.save(flush: true))
    {
      render(view: "edit", model: [mobileServerInstance: mobileServerInstance])
      return
    }

    flash.message = message(code: 'default.updated.message', args: [message(code: 'mobileServer.label', default: 'MobileServer'), mobileServerInstance.id])
    redirect(action: "show", id: mobileServerInstance.id)
  }

  def delete(Long id)
  {
    def mobileServerInstance = MobileServer.get(id)
    if (!mobileServerInstance)
    {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'mobileServer.label', default: 'MobileServer'), id])
      redirect(action: "list")
      return
    }

    try
    {
      mobileServerInstance.delete(flush: true)
      flash.message = message(code: 'default.deleted.message', args: [message(code: 'mobileServer.label', default: 'MobileServer'), id])
      redirect(action: "list")
    }
    catch (DataIntegrityViolationException e)
    {
      flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'mobileServer.label', default: 'MobileServer'), id])
      redirect(action: "show", id: id)
    }
  }
}
