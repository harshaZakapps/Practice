package com.hybris.zakapps.core.job;

import com.hybris.zakapps.commerceservices.service.ZakappsCommerceService;
import com.hybris.zakapps.core.model.OrderSummaryCronJobModel;
import com.hybris.zakapps.core.model.OrderSummaryEmailProcessModel;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.keygenerator.KeyGenerator;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.store.BaseStoreModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import java.util.Date;
import java.util.List;

public class OrderSummaryJob extends AbstractJobPerformable<OrderSummaryCronJobModel> {

    private static final Logger LOG = LoggerFactory.getLogger(OrderSummaryJob.class);

    private ZakappsCommerceService zakappsCommerceService;
    private ModelService modelService;
    private BusinessProcessService businessProcessService;
    private KeyGenerator processCodeGenerator;

    protected ModelService getModelService()
    {
        return modelService;
    }

    @Required
    public void setModelService(final ModelService modelService)
    {
        this.modelService = modelService;
    }

    public void setZakappsCommerceService(ZakappsCommerceService zakappsCommerceService) {
        this.zakappsCommerceService = zakappsCommerceService;
    }

    protected BusinessProcessService getBusinessProcessService()
    {
        return businessProcessService;
    }

    @Required
    public void setBusinessProcessService(final BusinessProcessService businessProcessService)
    {
        this.businessProcessService = businessProcessService;
    }

    public KeyGenerator getProcessCodeGenerator() {
        return processCodeGenerator;
    }

    @Required
    public void setProcessCodeGenerator(KeyGenerator processCodeGenerator) {
        this.processCodeGenerator = processCodeGenerator;
    }

    @Override
    public PerformResult perform(OrderSummaryCronJobModel orderSummaryCronJobModel) {
        try {

            Date orderDate = orderSummaryCronJobModel.getOrderDate();
            LOG.info("OrderSummaryJob [orderDate: {}]", orderDate);
            final List<OrderModel> orders =  zakappsCommerceService.findOrdersByOrderDate(orderDate);
            LOG.info("OrderSummaryJob [resultSize: {}]", orders.size());

            // Create a new instance of the process
            OrderSummaryEmailProcessModel orderSummaryEmailProcessModel = (OrderSummaryEmailProcessModel) getBusinessProcessService()
                    .createProcess("orderSummary-" + System.currentTimeMillis(), "orderSummaryEmailProcess");

            // Fill the process with the appropriate data
            orderSummaryEmailProcessModel.setSite(orders.get(0).getSite());
            orderSummaryEmailProcessModel.setCustomer((CustomerModel) orders.get(0).getUser());
            orderSummaryEmailProcessModel.setLanguage(orders.get(0).getLanguage());
            orderSummaryEmailProcessModel.setCurrency(orders.get(0).getCurrency());
            orderSummaryEmailProcessModel.setStore(orders.get(0).getStore());

            orderSummaryEmailProcessModel.setOrders(orders);

            // Save the process
            getModelService().save(orderSummaryEmailProcessModel);

            // Then start the process to send the Email
            getBusinessProcessService().startProcess(orderSummaryEmailProcessModel);

            // In case of success return result: SUCCESS and status: FINISHED
            return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);

        } catch(Exception e) {
            LOG.info("OrderSummaryJob [Exception: {}]", e.getStackTrace());
            // In case of exception return result: ERROR and status: ABORTED
            return new PerformResult(CronJobResult.ERROR, CronJobStatus.ABORTED);

        }
    }

}
