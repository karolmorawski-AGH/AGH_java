package agh.lab12.service.Jobs;

import agh.lab12.service.Controllers.APIHandler;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@Component
@DisallowConcurrentExecution
public class UpdateWeatherData extends QuartzJobBean {

	@Autowired
	APIHandler apiHandler;


	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		try {
			//TODO
			SchedulerContext schedulerContext = null;
			try {
				schedulerContext = context.getScheduler().getContext();
			} catch (SchedulerException e1) {
				e1.printStackTrace();
			}
			Integer id = (Integer) schedulerContext.get("id");
			System.out.println(id);

			apiHandler.updateTemperature(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
