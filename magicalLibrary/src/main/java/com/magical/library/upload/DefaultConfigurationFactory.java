package com.magical.library.upload;

import com.magical.library.upload.parser.BaseResponseParser;
import com.magical.library.upload.parser.StringResponseParser;
import com.magical.library.upload.uploader.BaseUploader;
import com.magical.library.upload.uploader.OKHttpUploader;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Project: TShow
 * FileName: DefaultConfigurationFactory.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 9/26/16 11:22 PM
 * Editor: ldy
 * Modify Date: 9/26/16 11:22 PM
 * Remark:
 */
public class DefaultConfigurationFactory {

    /**
     * 创建线程池
     *
     * @param poolSize
     * @param threadPriority
     * @return
     */
    public static ExecutorService createExecutor(int poolSize, int threadPriority) {
        BlockingDeque<Runnable> taskQueue = new LinkedBlockingDeque<Runnable>();
        ThreadFactory threadFactory = creadDefaultThreadFactory(threadPriority);
        return new ThreadPoolExecutor(poolSize, poolSize, 30, TimeUnit.MILLISECONDS, taskQueue, threadFactory);
    }

    public static BaseUploader createDefaultUploader() {
        return new OKHttpUploader();
    }

    public static BaseResponseParser createDefaultResponseProcessor() {
        return new StringResponseParser();
    }

    private static DefaultThreadFactory creadDefaultThreadFactory(int priority) {
        return new DefaultThreadFactory(priority);
    }

    private static class DefaultThreadFactory implements ThreadFactory {

        private static final AtomicInteger THREAD_POOL_NUMBER = new AtomicInteger(1);

        private ThreadGroup threadGroup;
        private AtomicInteger threadNumber = new AtomicInteger(1);
        private int threadPriority;
        private String namePrefix;


        public DefaultThreadFactory(int priority) {
            this.threadPriority = priority;
            threadGroup = Thread.currentThread().getThreadGroup();
            namePrefix = "fileupload-" + THREAD_POOL_NUMBER.getAndIncrement() + "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(threadGroup, r, namePrefix + threadNumber.getAndIncrement());
            thread.setPriority(threadPriority);
            if(thread.isDaemon())
                thread.setDaemon(false);
            return thread;
        }
    }


}