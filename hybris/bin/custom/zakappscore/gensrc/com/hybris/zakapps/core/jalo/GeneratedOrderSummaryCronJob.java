/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 18-Feb-2020 10:46:23                        ---
 * ----------------------------------------------------------------
 */
package com.hybris.zakapps.core.jalo;

import com.hybris.zakapps.core.constants.ZakappsCoreConstants;
import de.hybris.platform.cronjob.jalo.CronJob;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.C2LManager;
import de.hybris.platform.jalo.c2l.Language;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.hybris.zakapps.core.jalo.OrderSummaryCronJob OrderSummaryCronJob}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedOrderSummaryCronJob extends CronJob
{
	/** Qualifier of the <code>OrderSummaryCronJob.orderDate</code> attribute **/
	public static final String ORDERDATE = "orderDate";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(CronJob.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(ORDERDATE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderSummaryCronJob.orderDate</code> attribute.
	 * @return the orderDate
	 */
	public Date getOrderDate(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedOrderSummaryCronJob.getOrderDate requires a session language", 0 );
		}
		return (Date)getLocalizedProperty( ctx, ORDERDATE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderSummaryCronJob.orderDate</code> attribute.
	 * @return the orderDate
	 */
	public Date getOrderDate()
	{
		return getOrderDate( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderSummaryCronJob.orderDate</code> attribute. 
	 * @return the localized orderDate
	 */
	public Map<Language,Date> getAllOrderDate(final SessionContext ctx)
	{
		return (Map<Language,Date>)getAllLocalizedProperties(ctx,ORDERDATE,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderSummaryCronJob.orderDate</code> attribute. 
	 * @return the localized orderDate
	 */
	public Map<Language,Date> getAllOrderDate()
	{
		return getAllOrderDate( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderSummaryCronJob.orderDate</code> attribute. 
	 * @param value the orderDate
	 */
	public void setOrderDate(final SessionContext ctx, final Date value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedOrderSummaryCronJob.setOrderDate requires a session language", 0 );
		}
		setLocalizedProperty(ctx, ORDERDATE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderSummaryCronJob.orderDate</code> attribute. 
	 * @param value the orderDate
	 */
	public void setOrderDate(final Date value)
	{
		setOrderDate( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderSummaryCronJob.orderDate</code> attribute. 
	 * @param value the orderDate
	 */
	public void setAllOrderDate(final SessionContext ctx, final Map<Language,Date> value)
	{
		setAllLocalizedProperties(ctx,ORDERDATE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderSummaryCronJob.orderDate</code> attribute. 
	 * @param value the orderDate
	 */
	public void setAllOrderDate(final Map<Language,Date> value)
	{
		setAllOrderDate( getSession().getSessionContext(), value );
	}
	
}
