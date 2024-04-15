package work.pollochang.pollo.grails.db.aop

class DemoController {

    def demoService

    def index() {
        println "DemoController 8"
        demoService.insert()
        println "DemoController 10"
    }
}
