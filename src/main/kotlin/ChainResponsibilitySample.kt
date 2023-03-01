/**
 * 责任链模式示例
 */
fun main(args: Array<String>) {
    val leader = LeaderHandler()
    val manager = ManagerHandler()
    val majordomo = MajordomoHandler()
    leader.nextHandler = manager
    manager.nextHandler = majordomo
    leader.approvalVocation(5)
}

abstract class Handler {
    var nextHandler: Handler? = null

    abstract fun approvalVocation(days: Int)
}

class LeaderHandler : Handler() {

    override fun approvalVocation(days: Int) {
        if (days >= 1) {
            nextHandler?.approvalVocation(days)
        } else {
            println("领导假条批准")
        }
    }
}

class ManagerHandler : Handler() {
    override fun approvalVocation(days: Int) {
        if (days in 1 until  3){
            println("经理假条批准")
        } else {
            nextHandler?.approvalVocation(days)
        }
    }
}

class MajordomoHandler: Handler() {
    override fun approvalVocation(days: Int) {
        println("总监假条批准")
    }
}

