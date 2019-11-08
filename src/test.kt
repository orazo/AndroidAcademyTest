import java.math.BigDecimal
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.Month

class OrdersAnalyzer {
    data class Order(val orderId: Int, val creationDate: LocalDateTime, val orderLines: List<OrderLine>)

    data class OrderLine(val productId: Int, val name: String, val quantity: Int, val unitPrice: BigDecimal)

    fun totalDailySales(orders: List<Order>): Map<DayOfWeek, Int> {
        val map = mutableMapOf<DayOfWeek, Int>()
        for (order in orders) {
            val dayOfWeek = order.creationDate.dayOfWeek
            isInTheMap(order,map,dayOfWeek, map.keys.contains(dayOfWeek))
        }
        return map
    }

    private fun isInTheMap(
        order: Order, map: MutableMap<DayOfWeek, Int>, dayOfWeek: DayOfWeek, isIn:Boolean)
    {
        if(isIn){
            for (orderline in order.orderLines) map.get(dayOfWeek)?.plus(orderline.quantity)?.let {
                map.put(dayOfWeek, it)
            }
        }
        else{
            var quantity = 0
            for (orderlines in order.orderLines) {
                quantity += orderlines.quantity
            }
            map.put(dayOfWeek, quantity)
        }
    }
}