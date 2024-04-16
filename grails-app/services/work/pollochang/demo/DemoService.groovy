package work.pollochang.demo

import grails.gorm.transactions.Transactional
import groovy.sql.Sql
import work.pollochang.Test1

@Transactional
class DemoService {

    def dataSource

    /**
     * domain insert
     */
    void insert() {

        log.trace("execute.insert")
        log.trace("execute.insert: before new Domain")
        Test1 test1 = new Test1(col1: 123)
        log.trace("execute.insert: before save")
        test1.save()
        log.trace("execute.insert: after save")

    }

    /**
     * 執行 SQL
     */
    List<LinkedHashMap> executeSql(){
        log.trace("execute.executeSql")

        List<LinkedHashMap> rs = []
        log.trace("execute.executeSql: before new Sql")
        Sql sql = new Sql(dataSource)
        log.trace("execute.executeSql: before sql.eachRow")
        sql.eachRow("""
            select id ,col1  from test1
        """){ row ->
            rs << [id: row['id'], col1: row['col1']]
        }
        log.trace("execute.executeSql: after sql.eachRow")

        return rs

    }
}
