package io.pivotal.bookshop.buslogic;

import io.pivotal.bookshop.domain.BookOrder;
import io.pivotal.bookshop.domain.BookOrderItem;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;

import java.util.Date;

// TODO-02: Start locator and one server, enabling REST support. Use port 7071 as the HTTP port. Then Run this OrderLoader class.
// TODO-03: Open a browser to http://localhost:7071/gemfire-api/v1 and verify you see 4 regions listed.
public class OrderLoader
{
	public static void main(String[] args) {
		try (ClientCache cache = new ClientCacheFactory().create()) {
			populateBookOrders(cache);
		}
	}

	private static void populateBookOrders(ClientCache cache)
	{
		Region<String, BookOrder> orders = cache.getRegion("BookOrder");
		// Order for Kari Powell for book: A Treatise of Treatises
		BookOrder order1 = BookOrder.builder().orderNumber(17699).orderDate(new Date())
				.shippingCost(5.99f).shipDate(new Date())
				.orderItem(BookOrderItem.builder().orderLine(1).itemNumber(123).build())
				.customerNumber(5598).totalPrice(40.98f).build();
		orders.put("17699", order1);

		// Order for Lula Wax   book: A Treatise of Treatises & Clifford the Big Red Dog
		BookOrder order2 = BookOrder.builder().orderNumber(17700).orderDate(new Date())
				.shippingCost(5.99f).shipDate(new Date())
				.orderItem(BookOrderItem.builder().orderLine(1).itemNumber(123).build())
				.orderItem(BookOrderItem.builder().orderLine(2).itemNumber(456).build())
				.customerNumber(5543).totalPrice(52.97f).build();
		orders.put("17700", order2);
	}

}
