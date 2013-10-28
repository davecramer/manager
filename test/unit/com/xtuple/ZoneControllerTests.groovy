package com.xtuple



import org.junit.*
import grails.test.mixin.*

@TestFor(ZoneController)
@Mock(Zone)
class ZoneControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/zone/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.zoneInstanceList.size() == 0
        assert model.zoneInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.zoneInstance != null
    }

    void testSave() {
        controller.save()

        assert model.zoneInstance != null
        assert view == '/zone/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/zone/show/1'
        assert controller.flash.message != null
        assert Zone.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/zone/list'

        populateValidParams(params)
        def zone = new Zone(params)

        assert zone.save() != null

        params.id = zone.id

        def model = controller.show()

        assert model.zoneInstance == zone
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/zone/list'

        populateValidParams(params)
        def zone = new Zone(params)

        assert zone.save() != null

        params.id = zone.id

        def model = controller.edit()

        assert model.zoneInstance == zone
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/zone/list'

        response.reset()

        populateValidParams(params)
        def zone = new Zone(params)

        assert zone.save() != null

        // test invalid parameters in update
        params.id = zone.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/zone/edit"
        assert model.zoneInstance != null

        zone.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/zone/show/$zone.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        zone.clearErrors()

        populateValidParams(params)
        params.id = zone.id
        params.version = -1
        controller.update()

        assert view == "/zone/edit"
        assert model.zoneInstance != null
        assert model.zoneInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/zone/list'

        response.reset()

        populateValidParams(params)
        def zone = new Zone(params)

        assert zone.save() != null
        assert Zone.count() == 1

        params.id = zone.id

        controller.delete()

        assert Zone.count() == 0
        assert Zone.get(zone.id) == null
        assert response.redirectedUrl == '/zone/list'
    }
}
