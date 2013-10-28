package com.xtuple



import org.junit.*
import grails.test.mixin.*

@TestFor(NginxInstanceController)
@Mock(NginxInstance)
class NginxInstanceControllerTests
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
    assert "/nginxInstance/list" == response.redirectedUrl
  }

  void testList()
  {

    def model = controller.list()

    assert model.nginxInstanceInstanceList.size() == 0
    assert model.nginxInstanceInstanceTotal == 0
  }

  void testCreate()
  {
    def model = controller.create()

    assert model.nginxInstanceInstance != null
  }

  void testSave()
  {
    controller.save()

    assert model.nginxInstanceInstance != null
    assert view == '/nginxInstance/create'

    response.reset()

    populateValidParams(params)
    controller.save()

    assert response.redirectedUrl == '/nginxInstance/show/1'
    assert controller.flash.message != null
    assert NginxInstance.count() == 1
  }

  void testShow()
  {
    controller.show()

    assert flash.message != null
    assert response.redirectedUrl == '/nginxInstance/list'

    populateValidParams(params)
    def nginxInstance = new NginxInstance(params)

    assert nginxInstance.save() != null

    params.id = nginxInstance.id

    def model = controller.show()

    assert model.nginxInstanceInstance == nginxInstance
  }

  void testEdit()
  {
    controller.edit()

    assert flash.message != null
    assert response.redirectedUrl == '/nginxInstance/list'

    populateValidParams(params)
    def nginxInstance = new NginxInstance(params)

    assert nginxInstance.save() != null

    params.id = nginxInstance.id

    def model = controller.edit()

    assert model.nginxInstanceInstance == nginxInstance
  }

  void testUpdate()
  {
    controller.update()

    assert flash.message != null
    assert response.redirectedUrl == '/nginxInstance/list'

    response.reset()

    populateValidParams(params)
    def nginxInstance = new NginxInstance(params)

    assert nginxInstance.save() != null

    // test invalid parameters in update
    params.id = nginxInstance.id
    //TODO: add invalid values to params object

    controller.update()

    assert view == "/nginxInstance/edit"
    assert model.nginxInstanceInstance != null

    nginxInstance.clearErrors()

    populateValidParams(params)
    controller.update()

    assert response.redirectedUrl == "/nginxInstance/show/$nginxInstance.id"
    assert flash.message != null

    //test outdated version number
    response.reset()
    nginxInstance.clearErrors()

    populateValidParams(params)
    params.id = nginxInstance.id
    params.version = -1
    controller.update()

    assert view == "/nginxInstance/edit"
    assert model.nginxInstanceInstance != null
    assert model.nginxInstanceInstance.errors.getFieldError('version')
    assert flash.message != null
  }

  void testDelete()
  {
    controller.delete()
    assert flash.message != null
    assert response.redirectedUrl == '/nginxInstance/list'

    response.reset()

    populateValidParams(params)
    def nginxInstance = new NginxInstance(params)

    assert nginxInstance.save() != null
    assert NginxInstance.count() == 1

    params.id = nginxInstance.id

    controller.delete()

    assert NginxInstance.count() == 0
    assert NginxInstance.get(nginxInstance.id) == null
    assert response.redirectedUrl == '/nginxInstance/list'
  }
}
