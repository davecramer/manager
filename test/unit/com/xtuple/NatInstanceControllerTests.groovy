package com.xtuple



import org.junit.*
import grails.test.mixin.*

@TestFor(NatInstanceController)
@Mock(NatInstance)
class NatInstanceControllerTests
{

  def populateValidParams(params)
  {
    assert params != null
    // TODO: Populate valid properties like...
    //params["name"] = 'someValidName'
  }

  void testIndex()
  {
    controller.index()
    assert "/natInstance/list" == response.redirectedUrl
  }

  void testList()
  {

    def model = controller.list()

    assert model.natInstanceInstanceList.size() == 0
    assert model.natInstanceInstanceTotal == 0
  }

  void testCreate()
  {
    def model = controller.create()

    assert model.natInstanceInstance != null
  }

  void testSave()
  {
    controller.save()

    assert model.natInstanceInstance != null
    assert view == '/natInstance/create'

    response.reset()

    populateValidParams(params)
    controller.save()

    assert response.redirectedUrl == '/natInstance/show/1'
    assert controller.flash.message != null
    assert NatInstance.count() == 1
  }

  void testShow()
  {
    controller.show()

    assert flash.message != null
    assert response.redirectedUrl == '/natInstance/list'

    populateValidParams(params)
    def natInstance = new NatInstance(params)

    assert natInstance.save() != null

    params.id = natInstance.id

    def model = controller.show()

    assert model.natInstanceInstance == natInstance
  }

  void testEdit()
  {
    controller.edit()

    assert flash.message != null
    assert response.redirectedUrl == '/natInstance/list'

    populateValidParams(params)
    def natInstance = new NatInstance(params)

    assert natInstance.save() != null

    params.id = natInstance.id

    def model = controller.edit()

    assert model.natInstanceInstance == natInstance
  }

  void testUpdate()
  {
    controller.update()

    assert flash.message != null
    assert response.redirectedUrl == '/natInstance/list'

    response.reset()

    populateValidParams(params)
    def natInstance = new NatInstance(params)

    assert natInstance.save() != null

    // test invalid parameters in update
    params.id = natInstance.id
    //TODO: add invalid values to params object

    controller.update()

    assert view == "/natInstance/edit"
    assert model.natInstanceInstance != null

    natInstance.clearErrors()

    populateValidParams(params)
    controller.update()

    assert response.redirectedUrl == "/natInstance/show/$natInstance.id"
    assert flash.message != null

    //test outdated version number
    response.reset()
    natInstance.clearErrors()

    populateValidParams(params)
    params.id = natInstance.id
    params.version = -1
    controller.update()

    assert view == "/natInstance/edit"
    assert model.natInstanceInstance != null
    assert model.natInstanceInstance.errors.getFieldError('version')
    assert flash.message != null
  }

  void testDelete()
  {
    controller.delete()
    assert flash.message != null
    assert response.redirectedUrl == '/natInstance/list'

    response.reset()

    populateValidParams(params)
    def natInstance = new NatInstance(params)

    assert natInstance.save() != null
    assert NatInstance.count() == 1

    params.id = natInstance.id

    controller.delete()

    assert NatInstance.count() == 0
    assert NatInstance.get(natInstance.id) == null
    assert response.redirectedUrl == '/natInstance/list'
  }
}
