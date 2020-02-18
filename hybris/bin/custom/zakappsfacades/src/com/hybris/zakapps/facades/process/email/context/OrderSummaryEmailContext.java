package com.hybris.zakapps.facades.process.email.context;

import com.hybris.zakapps.core.model.OrderSummaryEmailProcessModel;
import de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel;
import de.hybris.platform.commerceservices.model.process.StoreFrontCustomerProcessModel;
import de.hybris.platform.core.model.order.OrderModel;

import java.util.List;

public class OrderSummaryEmailContext extends CustomerEmailContext {

    private List<OrderModel> orders;

    @Override
    public void init(final StoreFrontCustomerProcessModel processModel, final EmailPageModel emailPageModel)
    {
        super.init(processModel, emailPageModel);
        if (processModel instanceof OrderSummaryEmailProcessModel)
        {
            setOrders(((OrderSummaryEmailProcessModel) processModel).getOrders());
        }
    }

    public List<OrderModel> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderModel> orders) {
        this.orders = orders;
    }
}
