package com.xtuple

import org.springframework.dao.DataIntegrityViolationException

class NatInstanceController
{

  static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

  def index()
  {
    redirect(action: "list", params: params)
  }

  def list(Integer max)
  {
    params.max = Math.min(max ?: 10, 100)
    [natInstanceInstanceList: NatInstance.list(params), natInstanceInstanceTotal: NatInstance.count()]
  }

  def create()
  {
    [natInstanceInstance: new NatInstance(params)]
  }

  def save()
  {
    def natInstanceInstance = new NatInstance(params)
    if (!natInstanceInstance.save(flush: true))
    {
      render(view: "create", model: [natInstanceInstance: natInstanceInstance])
      return
    }

    flash.message = message(code: 'default.created.message', args: [message(code: 'natInstance.label', default: 'NatInstance'), natInstanceInstance.id])
    redirect(action: "show", id: natInstanceInstance.id)
  }

  def show(Long id)
  {
    def natInstanceInstance = NatInstance.get(id)
    if (!natInstanceInstance)
    {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'natInstance.label', default: 'NatInstance'), id])
      redirect(action: "list")
      return
    }

    [natInstanceInstance: natInstanceInstance]
  }

  def edit(Long id)
  {
    def natInstanceInstance = NatInstance.get(id)
    if (!natInstanceInstance)
    {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'natInstance.label', default: 'NatInstance'), id])
      redirect(action: "list")
      return
    }

    [natInstanceInstance: natInstanceInstance]
  }

  def update(Long id, Long version)
  {
    def natInstanceInstance = NatInstance.get(id)
    if (!natInstanceInstance)
    {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'natInstance.label', default: 'NatInstance'), id])
      redirect(action: "list")
      return
    }

    if (version != null)
    {
      if (natInstanceInstance.version > version)
      {
        natInstanceInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                [message(code: 'natInstance.label', default: 'NatInstance')] as Object[],
                "Another user has updated this NatInstance while you were editing")
        render(view: "edit", model: [natInstanceInstance: natInstanceInstance])
        return
      }
    }

    natInstanceInstance.properties = params

    if (!natInstanceInstance.save(flush: true))
    {
      render(view: "edit", model: [natInstanceInstance: natInstanceInstance])
      return
    }

    flash.message = message(code: 'default.updated.message', args: [message(code: 'natInstance.label', default: 'NatInstance'), natInstanceInstance.id])
    redirect(action: "show", id: natInstanceInstance.id)
  }

  def delete(Long id)
  {
    def natInstanceInstance = NatInstance.get(id)
    if (!natInstanceInstance)
    {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'natInstance.label', default: 'NatInstance'), id])
      redirect(action: "list")
      return
    }

    try
    {
      natInstanceInstance.delete(flush: true)
      flash.message = message(code: 'default.deleted.message', args: [message(code: 'natInstance.label', default: 'NatInstance'), id])
      redirect(action: "list")
    }
    catch (DataIntegrityViolationException e)
    {
      flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'natInstance.label', default: 'NatInstance'), id])
      redirect(action: "show", id: id)
    }
  }
}
