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
package com.hybris.zakapps.commerceservices.service.impl;

import de.hybris.platform.catalog.model.CatalogUnawareMediaModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.exceptions.SystemException;
import de.hybris.platform.servicelayer.internal.dao.AbstractItemDao;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

import de.hybris.platform.servicelayer.search.SearchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.hybris.zakapps.commerceservices.service.ZakappsCommerceService;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;


public class DefaultZakappsCommerceService extends AbstractItemDao implements ZakappsCommerceService
{
	private static final Logger LOG = LoggerFactory.getLogger(DefaultZakappsCommerceService.class);

	private MediaService mediaService;

	private static final String FIND_ORDERS_BY_ORDER_DATE_QUERY = "SELECT {" + OrderModel.PK + "}, {"
			+ OrderModel.CREATIONTIME + "}, {" + OrderModel.CODE + "} FROM {" + OrderModel._TYPECODE + "} WHERE CAST({" + OrderModel.DATE
			+ "} AS DATE) = TO_DATE(?orderDate,'YYYY-MM-DD') AND {" + OrderModel.VERSIONID + "} IS NULL";


	@Override
	public List<OrderModel> findOrdersByOrderDate(final Date orderDate)
	{
		validateParameterNotNull(orderDate, "Order Date must not be null");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String paramOrderDate = null;
		paramOrderDate = format.format(orderDate);
		LOG.info("ZakappsCommerceService [orderDate: {}]", paramOrderDate);
		final Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("orderDate", paramOrderDate);

		final SearchResult<OrderModel> result = getFlexibleSearchService().search(FIND_ORDERS_BY_ORDER_DATE_QUERY, queryParams);
		return result.getResult();
	}

	@Override
	public String getHybrisLogoUrl(final String logoCode)
	{
		final MediaModel media = mediaService.getMedia(logoCode);

		// Keep in mind that with Slf4j you don't need to check if debug is enabled, it is done under the hood.
		LOG.debug("Found media [code: {}]", media.getCode());

		return media.getURL();
	}

	@Override
	public void createLogo(final String logoCode)
	{
		final Optional<CatalogUnawareMediaModel> existingLogo = findExistingLogo(logoCode);

		final CatalogUnawareMediaModel media = existingLogo.isPresent() ? existingLogo.get()
				: modelService.create(CatalogUnawareMediaModel.class);
		media.setCode(logoCode);
		media.setRealFileName("sap-hybris-platform.png");
		modelService.save(media);

		mediaService.setStreamForMedia(media, getImageStream());
	}

	private final static String FIND_LOGO_QUERY = "SELECT {" + CatalogUnawareMediaModel.PK + "} FROM {"
			+ CatalogUnawareMediaModel._TYPECODE + "} WHERE {" + CatalogUnawareMediaModel.CODE + "}=?code";

	private Optional<CatalogUnawareMediaModel> findExistingLogo(final String logoCode)
	{
		final FlexibleSearchQuery fQuery = new FlexibleSearchQuery(FIND_LOGO_QUERY);
		fQuery.addQueryParameter("code", logoCode);

		try
		{
			return Optional.of(flexibleSearchService.searchUnique(fQuery));
		}
		catch (final SystemException e)
		{
			return Optional.empty();
		}
	}

	private InputStream getImageStream()
	{
		return DefaultZakappsCommerceService.class.getResourceAsStream("/zakappscommerceservices/sap-hybris-platform.png");
	}

	@Required
	public void setMediaService(final MediaService mediaService)
	{
		this.mediaService = mediaService;
	}

	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	@Required
	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}
}
