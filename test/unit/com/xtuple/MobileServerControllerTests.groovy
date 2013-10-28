package com.xtuple



import org.junit.*
import grails.test.mixin.*

@TestFor(MobileServerController)
@Mock(MobileServer)
class MobileServerControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/mobileServer/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.mobileServerInstanceList.size() == 0
        assert model.mobileServerInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.mobileServerInstance != null
    }

    void testSave() {
        controller.save()

        assert model.mobileServerInstance != null
        assert view == '/mobileServer/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/mobileServer/show/1'
        assert controller.flash.message != null
        assert MobileServer.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/mobileServer/list'

        populateValidParams(params)
        def mobileServer = new MobileServer(params)

        assert mobileServer.save() != null

        params.id = mobileServer.id

        def model = controller.show()

        assert model.mobileServerInstance == mobileServer
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/mobileServer/list'

        populateValidParams(params)
        def mobileServer = new MobileServer(params)

        assert mobileServer.save() != null

        params.id = mobileServer.id

        def model = controller.edit()

        assert model.mobileServerInstance == mobileServer
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/mobileServer/list'

        response.reset()

        populateValidParams(params)
        def mobileServer = new MobileServer(params)

        assert mobileServer.save() != null

        // test invalid parameters in update
        params.id = mobileServer.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/mobileServer/edit"
        assert model.mobileServerInstance != null

        mobileServer.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/mobileServer/show/$mobileServer.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        mobileServer.clearErrors()

        populateValidParams(params)
        params.id = mobileServer.id
        params.version = -1
        controller.update()

        assert view == "/mobileServer/edit"
        assert model.mobileServerInstance != null
        assert model.mobileServerInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/mobileServer/list'

        response.reset()

        populateValidParams(params)
        def mobileServer = new MobileServer(params)

        assert mobileServer.save() != null
        assert MobileServer.count() == 1

        params.id = mobileServer.id

        controller.delete()

        assert MobileServer.count() == 0
        assert MobileServer.get(mobileServer.id) == null
        assert response.redirectedUrl == '/mobileServer/list'
    }
}
