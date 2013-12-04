package com.xtuple



import grails.test.mixin.*
import spock.lang.*

@TestFor(OrganizationController)
@Mock(Organization)
class OrganizationControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.organizationInstanceList
            model.organizationInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.organizationInstance!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            def organization = new Organization()
            organization.validate()
            controller.save(organization)

        then:"The create view is rendered again with the correct model"
            model.organizationInstance!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            organization = new Organization(params)

            controller.save(organization)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/organization/show/1'
            controller.flash.message != null
            Organization.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def organization = new Organization(params)
            controller.show(organization)

        then:"A model is populated containing the domain instance"
            model.organizationInstance == organization
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def organization = new Organization(params)
            controller.edit(organization)

        then:"A model is populated containing the domain instance"
            model.organizationInstance == organization
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/organization/index'
            flash.message != null


        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def organization = new Organization()
            organization.validate()
            controller.update(organization)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.organizationInstance == organization

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            organization = new Organization(params).save(flush: true)
            controller.update(organization)

        then:"A redirect is issues to the show action"
            response.redirectedUrl == "/organization/show/$organization.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/organization/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def organization = new Organization(params).save(flush: true)

        then:"It exists"
            Organization.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(organization)

        then:"The instance is deleted"
            Organization.count() == 0
            response.redirectedUrl == '/organization/index'
            flash.message != null
    }
}
