package com.xtuple



import org.junit.*
import grails.test.mixin.*

@TestFor(DatabaseServerController)
@Mock(DatabaseServer)
class DatabaseServerControllerTests
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
    assert "/databaseServer/list" == response.redirectedUrl
  }

  void testList()
  {

    def model = controller.list()

    assert model.databaseServerInstanceList.size() == 0
    assert model.databaseServerInstanceTotal == 0
  }

  void testCreate()
  {
    def model = controller.create()

    assert model.databaseServerInstance != null
  }

  void testSave()
  {
    controller.save()

    assert model.databaseServerInstance != null
    assert view == '/databaseServer/create'

    response.reset()

    populateValidParams(params)
    controller.save()

    assert response.redirectedUrl == '/databaseServer/show/1'
    assert controller.flash.message != null
    assert DatabaseServer.count() == 1
  }

  void testShow()
  {
    controller.show()

    assert flash.message != null
    assert response.redirectedUrl == '/databaseServer/list'

    populateValidParams(params)
    def databaseServer = new DatabaseServer(params)

    assert databaseServer.save() != null

    params.id = databaseServer.id

    def model = controller.show()

    assert model.databaseServerInstance == databaseServer
  }

  void testEdit()
  {
    controller.edit()

    assert flash.message != null
    assert response.redirectedUrl == '/databaseServer/list'

    populateValidParams(params)
    def databaseServer = new DatabaseServer(params)

    assert databaseServer.save() != null

    params.id = databaseServer.id

    def model = controller.edit()

    assert model.databaseServerInstance == databaseServer
  }

  void testUpdate()
  {
    controller.update()

    assert flash.message != null
    assert response.redirectedUrl == '/databaseServer/list'

    response.reset()

    populateValidParams(params)
    def databaseServer = new DatabaseServer(params)

    assert databaseServer.save() != null

    // test invalid parameters in update
    params.id = databaseServer.id
    //TODO: add invalid values to params object

    controller.update()

    assert view == "/databaseServer/edit"
    assert model.databaseServerInstance != null

    databaseServer.clearErrors()

    populateValidParams(params)
    controller.update()

    assert response.redirectedUrl == "/databaseServer/show/$databaseServer.id"
    assert flash.message != null

    //test outdated version number
    response.reset()
    databaseServer.clearErrors()

    populateValidParams(params)
    params.id = databaseServer.id
    params.version = -1
    controller.update()

    assert view == "/databaseServer/edit"
    assert model.databaseServerInstance != null
    assert model.databaseServerInstance.errors.getFieldError('version')
    assert flash.message != null
  }

  void testDelete()
  {
    controller.delete()
    assert flash.message != null
    assert response.redirectedUrl == '/databaseServer/list'

    response.reset()

    populateValidParams(params)
    def databaseServer = new DatabaseServer(params)

    assert databaseServer.save() != null
    assert DatabaseServer.count() == 1

    params.id = databaseServer.id

    controller.delete()

    assert DatabaseServer.count() == 0
    assert DatabaseServer.get(databaseServer.id) == null
    assert response.redirectedUrl == '/databaseServer/list'
  }
}
