package work.pollochang.demo

import grails.gorm.transactions.Transactional
import groovy.sql.Sql
import work.pollochang.Test1

@Transactional
class DemoService {

    def dataSource



    def insert() {
        Test1 test1 = new Test1(col1: 123)
        test1.save()
    }

    /**
     * 執行 SQL
     */
    List<LinkedHashMap> executeSql(){

        List<LinkedHashMap> rs = []
        Sql sql = new Sql(dataSource)
        sql.eachRow("""
            select id ,col1  from test1
        """){ row ->
            rs << [id: row['id'], col1: row['col1']]
        }

        return rs

    }
}
