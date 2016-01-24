package org.lihai.weixin.Quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class RefreshAccessTokenJob extends  QuartzJobBean {
	private RefreshAccessTokenTask refreshAccessTokenTask;
	public void setRefreshAccessTokenTask(
			RefreshAccessTokenTask refreshAccessTokenTask) {
		this.refreshAccessTokenTask = refreshAccessTokenTask;
	}
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		refreshAccessTokenTask.refreshToken();
	}

}
