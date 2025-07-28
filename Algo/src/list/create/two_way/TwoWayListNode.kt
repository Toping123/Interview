package list.create.two_way

/**
 * 双向链表的节点
 * @author LTP  2025/7/28
 */
class TwoWayListNode(val value: Int) {
    /** 前一个节点 */
    var prev: TwoWayListNode? = null

    /** 后一个节点 */
    var next: TwoWayListNode? = null
}