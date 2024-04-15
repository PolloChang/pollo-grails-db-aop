package work.pollochang

/**
 * 測試 domain
 */
class Test1 {

    int col1

    static mapping = {

        table: "Bank"
        version false
        id column: "id", generator: "identity"
        col1 column: "col1"
    }

    static constraints = {
        col1 nullable: true
    }
}
