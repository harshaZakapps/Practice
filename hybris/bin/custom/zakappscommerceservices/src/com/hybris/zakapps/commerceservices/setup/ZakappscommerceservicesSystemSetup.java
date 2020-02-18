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
package com.hybris.zakapps.commerceservices.setup;

import static com.hybris.zakapps.commerceservices.constants.ZakappscommerceservicesConstants.PLATFORM_LOGO_CODE;

import com.hybris.zakapps.commerceservices.service.ZakappsCommerceService;
import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import com.hybris.zakapps.commerceservices.constants.ZakappscommerceservicesConstants;


@SystemSetup(extension = ZakappscommerceservicesConstants.EXTENSIONNAME)
public class ZakappscommerceservicesSystemSetup
{
	private final ZakappsCommerceService zakappsCommerceService;

	public ZakappscommerceservicesSystemSetup(final ZakappsCommerceService zakappsCommerceService)
	{
		this.zakappsCommerceService = zakappsCommerceService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		zakappsCommerceService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return ZakappscommerceservicesSystemSetup.class.getResourceAsStream("/zakappscommerceservices/sap-hybris-platform.png");
	}
}
