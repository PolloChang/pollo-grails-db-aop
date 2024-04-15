package work.pollochang.pollo.grails.db.aop

class DemoController {

    def demoService

    def index() {
        demoService.insert()
    }
}
