/*
 * [y] hybris Platform
 * 
 * Copyright (c) 2000-2016 SAP SE
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information of SAP 
 * Hybris ("Confidential Information"). You shall not disclose such 
 * Confidential Information and shall use it only in accordance with the 
 * terms of the license agreement you entered into with SAP Hybris.
 */
package com.hybris.zakapps.commerceservices.service;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.internal.dao.Dao;

import java.util.Date;
import java.util.List;

public interface ZakappsCommerceService extends Dao
{
	String getHybrisLogoUrl(String logoCode);

	void createLogo(String logoCode);

	/**
	 * Finds orders for the specified user in the current session's active catalog versions
	 *
	 * @param orderDate
	 *           any valid date
	 * @return The list of orders owned by the customer associated with the store
	 */
	List<OrderModel> findOrdersByOrderDate(Date orderDate);

}
