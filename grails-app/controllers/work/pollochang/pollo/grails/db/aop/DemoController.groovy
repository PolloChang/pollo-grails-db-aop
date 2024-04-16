package work.pollochang.pollo.grails.db.aop

class DemoController {

    def demoService

    def index() {

        println "DemoController 9"
        demoService.insert()
        println "DemoController 11"

    }

    def testSql(){

        println "testSql 17"

        List<LinkedHashMap> rs = demoService.executeSql()

        println rs

        println "testSql 23"

        render(view: "/demo/index")

    }
}
