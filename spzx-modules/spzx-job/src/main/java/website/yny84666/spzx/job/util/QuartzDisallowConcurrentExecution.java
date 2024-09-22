package website.yny84666.spzx.job.util;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;

import website.yny84666.spzx.job.domain.SysJob;

/**
 * 定时任务处理（禁止并发执行）
 *
 * @author spzx
 *
 */
@DisallowConcurrentExecution
public class QuartzDisallowConcurrentExecution extends AbstractQuartzJob
{
    @Override
    protected void doExecute(JobExecutionContext context, SysJob sysJob) throws Exception
    {
        JobInvokeUtil.invokeMethod(sysJob);
    }
}
