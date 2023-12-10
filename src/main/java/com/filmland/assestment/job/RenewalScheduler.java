package com.filmland.assestment.job;

import com.filmland.assestment.service.RenewalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class RenewalScheduler {

    private final RenewalService renewalService;


    @Value("${filmland.auto.renewal:true}")
    private boolean cronJobToggle;

    @Scheduled(cron = "${filmland.auto.renewal.cronExpression}")
    public void renewSubscriptions() {

        if (cronJobToggle) {
            log.info("Scheduled job for subscriptions starting");
            renewalService.renewSubscribers();
        }
    }
}
