package com.xtuple

import org.springframework.dao.DataIntegrityViolationException

class NginxInstanceController
{

  static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

  def index()
  {
    redirect(action: "list", params: params)
  }

  def list(Integer max)
  {
    params.max = Math.min(max ?: 10, 100)
    [nginxInstanceInstanceList: NginxInstance.list(params), nginxInstanceInstanceTotal: NginxInstance.count()]
  }

  def create()
  {
    [nginxInstanceInstance: new NginxInstance(params)]
  }

  def save()
  {
    def nginxInstanceInstance = new NginxInstance(params)
    if (!nginxInstanceInstance.save(flush: true))
    {
      render(view: "create", model: [nginxInstanceInstance: nginxInstanceInstance])
      return
    }

    flash.message = message(code: 'default.created.message', args: [message(code: 'nginxInstance.label', default: 'NginxInstance'), nginxInstanceInstance.id])
    redirect(action: "show", id: nginxInstanceInstance.id)
  }

  def show(Long id)
  {
    def nginxInstanceInstance = NginxInstance.get(id)
    if (!nginxInstanceInstance)
    {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'nginxInstance.label', default: 'NginxInstance'), id])
      redirect(action: "list")
      return
    }

    [nginxInstanceInstance: nginxInstanceInstance]
  }

  def edit(Long id)
  {
    def nginxInstanceInstance = NginxInstance.get(id)
    if (!nginxInstanceInstance)
    {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'nginxInstance.label', default: 'NginxInstance'), id])
      redirect(action: "list")
      return
    }

    [nginxInstanceInstance: nginxInstanceInstance]
  }

  def update(Long id, Long version)
  {
    def nginxInstanceInstance = NginxInstance.get(id)
    if (!nginxInstanceInstance)
    {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'nginxInstance.label', default: 'NginxInstance'), id])
      redirect(action: "list")
      return
    }

    if (version != null)
    {
      if (nginxInstanceInstance.version > version)
      {
        nginxInstanceInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                [message(code: 'nginxInstance.label', default: 'NginxInstance')] as Object[],
                "Another user has updated this NginxInstance while you were editing")
        render(view: "edit", model: [nginxInstanceInstance: nginxInstanceInstance])
        return
      }
    }

    nginxInstanceInstance.properties = params

    if (!nginxInstanceInstance.save(flush: true))
    {
      render(view: "edit", model: [nginxInstanceInstance: nginxInstanceInstance])
      return
    }

    flash.message = message(code: 'default.updated.message', args: [message(code: 'nginxInstance.label', default: 'NginxInstance'), nginxInstanceInstance.id])
    redirect(action: "show", id: nginxInstanceInstance.id)
  }

  def delete(Long id)
  {
    def nginxInstanceInstance = NginxInstance.get(id)
    if (!nginxInstanceInstance)
    {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'nginxInstance.label', default: 'NginxInstance'), id])
      redirect(action: "list")
      return
    }

    try
    {
      nginxInstanceInstance.delete(flush: true)
      flash.message = message(code: 'default.deleted.message', args: [message(code: 'nginxInstance.label', default: 'NginxInstance'), id])
      redirect(action: "list")
    }
    catch (DataIntegrityViolationException e)
    {
      flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'nginxInstance.label', default: 'NginxInstance'), id])
      redirect(action: "show", id: id)
    }
  }
}
