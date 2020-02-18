/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2016 SAP SE or an SAP affiliate company.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.hybris.zakapps.fulfilmentprocess.jalo;

import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import com.hybris.zakapps.fulfilmentprocess.constants.ZakappsFulfilmentProcessConstants;

@SuppressWarnings("PMD")
public class ZakappsFulfilmentProcessManager extends GeneratedZakappsFulfilmentProcessManager
{
	public static final ZakappsFulfilmentProcessManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (ZakappsFulfilmentProcessManager) em.getExtension(ZakappsFulfilmentProcessConstants.EXTENSIONNAME);
	}
	
}
