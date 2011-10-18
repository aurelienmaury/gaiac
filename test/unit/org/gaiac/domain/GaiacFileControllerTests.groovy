package org.gaiac.domain



import org.junit.*
import grails.test.mixin.*
import javax.servlet.http.HttpServletResponse

@TestFor(GaiacFileController)
@Mock(GaiacFile)
class GaiacFileControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/gaiacFile/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.gaiacFileInstanceList.size() == 0
        assert model.gaiacFileInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.gaiacFileInstance != null
    }

    void testSave() {
        controller.save()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.save()

        assert model.gaiacFileInstance != null
        assert view == '/gaiacFile/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/gaiacFile/show/1'
        assert controller.flash.message != null
        assert GaiacFile.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/gaiacFile/list'


        populateValidParams(params)
        def gaiacFile = new GaiacFile(params)

        assert gaiacFile.save() != null

        params.id = gaiacFile.id

        def model = controller.show()

        assert model.gaiacFileInstance == gaiacFile
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/gaiacFile/list'


        populateValidParams(params)
        def gaiacFile = new GaiacFile(params)

        assert gaiacFile.save() != null

        params.id = gaiacFile.id

        def model = controller.edit()

        assert model.gaiacFileInstance == gaiacFile
    }

    void testUpdate() {

        controller.update()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/gaiacFile/list'

        response.reset()


        populateValidParams(params)
        def gaiacFile = new GaiacFile(params)

        assert gaiacFile.save() != null

        // test invalid parameters in update
        params.id = gaiacFile.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/gaiacFile/edit"
        assert model.gaiacFileInstance != null

        gaiacFile.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/gaiacFile/show/$gaiacFile.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        gaiacFile.clearErrors()

        populateValidParams(params)
        params.id = gaiacFile.id
        params.version = -1
        controller.update()

        assert view == "/gaiacFile/edit"
        assert model.gaiacFileInstance != null
        assert model.gaiacFileInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/gaiacFile/list'

        response.reset()

        populateValidParams(params)
        def gaiacFile = new GaiacFile(params)

        assert gaiacFile.save() != null
        assert GaiacFile.count() == 1

        params.id = gaiacFile.id

        controller.delete()

        assert GaiacFile.count() == 0
        assert GaiacFile.get(gaiacFile.id) == null
        assert response.redirectedUrl == '/gaiacFile/list'
    }
}
