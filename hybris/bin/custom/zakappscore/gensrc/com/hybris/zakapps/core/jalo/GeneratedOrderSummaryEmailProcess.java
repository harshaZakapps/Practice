/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 18-Feb-2020 10:46:23                        ---
 * ----------------------------------------------------------------
 */
package com.hybris.zakapps.core.jalo;

import com.hybris.zakapps.core.constants.ZakappsCoreConstants;
import de.hybris.platform.commerceservices.jalo.process.StoreFrontCustomerProcess;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.order.Order;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.hybris.zakapps.core.jalo.OrderSummaryEmailProcess OrderSummaryEmailProcess}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedOrderSummaryEmailProcess extends StoreFrontCustomerProcess
{
	/** Qualifier of the <code>OrderSummaryEmailProcess.orders</code> attribute **/
	public static final String ORDERS = "orders";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(StoreFrontCustomerProcess.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(ORDERS, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderSummaryEmailProcess.orders</code> attribute.
	 * @return the orders - List of orders for the given date
	 */
	public List<Order> getOrders(final SessionContext ctx)
	{
		List<Order> coll = (List<Order>)getProperty( ctx, ORDERS);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderSummaryEmailProcess.orders</code> attribute.
	 * @return the orders - List of orders for the given date
	 */
	public List<Order> getOrders()
	{
		return getOrders( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderSummaryEmailProcess.orders</code> attribute. 
	 * @param value the orders - List of orders for the given date
	 */
	public void setOrders(final SessionContext ctx, final List<Order> value)
	{
		setProperty(ctx, ORDERS,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderSummaryEmailProcess.orders</code> attribute. 
	 * @param value the orders - List of orders for the given date
	 */
	public void setOrders(final List<Order> value)
	{
		setOrders( getSession().getSessionContext(), value );
	}
	
}
