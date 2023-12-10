package com.filmland.assestment.controller;

import com.filmland.assestment.dto.ShareSubscriptionDto;
import com.filmland.assestment.dto.SubscriptionInputDto;
import com.filmland.assestment.facade.SessionFacade;
import com.filmland.assestment.util.DefaultResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.filmland.assestment.util.AppDefaultConstants.DEFAULT_FAILURE_STATUS;
import static com.filmland.assestment.util.AppDefaultConstants.DEFAULT_SUCCES_STATUS;

@RestController()
@RequestMapping("/api/subscription")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SessionFacade sessionFacade;

    @PostMapping("/subscribe")
    public ResponseEntity<DefaultResponseMessage> subscribeToCategory(@RequestBody SubscriptionInputDto subscriptionInputDto) {
        DefaultResponseMessage message;

        try {
            sessionFacade.subscribeToCategory(subscriptionInputDto);

            message = DefaultResponseMessage.create(DEFAULT_SUCCES_STATUS, "Subscription successful. Enjoy watching!");

            return ResponseEntity.ok(message);
        } catch (Exception e) {

            message = DefaultResponseMessage.create(DEFAULT_FAILURE_STATUS, e.getMessage());

            return ResponseEntity.badRequest().body(message);
        }
    }

    @PostMapping("/shareSubscription")
    public ResponseEntity<DefaultResponseMessage> shareCategory(@RequestBody ShareSubscriptionDto shareSubscriptionDto) {
        try {
            sessionFacade.shareSubscription(shareSubscriptionDto);

            DefaultResponseMessage message = DefaultResponseMessage.create("success", "Subscription shared successfully. Enjoy watching!");

            return ResponseEntity.ok(message);
        } catch (Exception e) {

            DefaultResponseMessage message = DefaultResponseMessage.create("failure", e.getMessage());

            return ResponseEntity.badRequest().body(message);
        }
    }
}
