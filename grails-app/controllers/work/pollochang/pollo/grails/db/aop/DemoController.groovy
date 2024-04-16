package work.pollochang.pollo.grails.db.aop

class DemoController {

    def demoService

    def index() {

        demoService.insert()

    }

    def testSql(){

        List<LinkedHashMap> rs = demoService.executeSql()

        render(view: "/demo/index")

    }
}
