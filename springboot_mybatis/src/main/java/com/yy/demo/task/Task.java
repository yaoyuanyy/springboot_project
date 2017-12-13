package com.yy.demo.task;

import com.yy.demo.service.IUserService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Description:
 * <p></p>
 * <pre></pre>
 * NB.
 * Created by skyler on 2017/11/15 at 下午3:59
 */
@Component
public class Task {

    @Resource
    private IUserService userService;

    //@Scheduled(initialDelay= 1000, fixedDelay = 1000*1000)
    public void updateScore() {
        int score = 0;
        Executor executor = Executors.newFixedThreadPool(10);
        for (int i : new int[]{1,2,3}) {
            executor.execute(() -> {
                userService.updateScore(score, 2);
            });
        }
    }
}
