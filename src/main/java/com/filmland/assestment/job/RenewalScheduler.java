package com.filmland.assestment.job;

import com.filmland.assestment.service.RenewalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class RenewalScheduler {

    private final RenewalService renewalService;

    @Scheduled()
    public void renewSubscriptions() {
        log.info("Scheduled job for subscriptions starting");

        renewalService.renewSubscribers();

    }
}
