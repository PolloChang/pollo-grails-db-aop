package work.pollochang.pollo.grails.db.aop

import grails.gorm.transactions.Transactional
import work.pollochang.Test1

@Transactional
class DemoService {

    def insert() {
        Test1 test1 = new Test1(col1: 123)
        test1.save()
    }
}
