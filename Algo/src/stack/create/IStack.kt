/**
 * @author LTP  2025/7/23
 */
interface IStack<T> {
    fun size(): Int
    fun isEmpty(): Boolean
    fun push(t: T)
    fun pop(): T
    fun peek(): T
}