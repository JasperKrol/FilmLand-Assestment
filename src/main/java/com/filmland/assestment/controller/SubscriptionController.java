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

@RestController()
@RequestMapping("/api/subscription")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SessionFacade sessionFacade;

    @PostMapping("/subscribe")
    public ResponseEntity<DefaultResponseMessage> subscribeToCategory(@RequestBody SubscriptionInputDto subscriptionInputDto) {

        sessionFacade.subscribeToCategory(subscriptionInputDto);

        DefaultResponseMessage defaultResponseMessage = DefaultResponseMessage.create("Login successful", "enjoy watching");

        return ResponseEntity.ok(defaultResponseMessage);
    }

    @PostMapping("/shareSubscription")
    public ResponseEntity<DefaultResponseMessage> shareCategory(@RequestBody ShareSubscriptionDto shareSubscriptionDto) {

        sessionFacade.shareSubscription(shareSubscriptionDto);

        DefaultResponseMessage defaultResponseMessage = DefaultResponseMessage.create("Login successful", "enjoy watching");

        return ResponseEntity.ok(defaultResponseMessage);
    }
}
