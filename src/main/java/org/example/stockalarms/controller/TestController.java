package org.example.stockalarms.controller;

import lombok.RequiredArgsConstructor;
import org.example.stockalarms.service.email.EmailServiceImpl;
import org.example.stockalarms.utils.email.EmailUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/test")
public class TestController {
    private final EmailServiceImpl emailService;
    private final EmailUtils emailUtils;
    @PostMapping("sendEmail")
    public void testSendEmail(@RequestParam String email){
        emailService.sendHtmlEmail(email,"StockAlarmsTest",emailUtils.getHtmlStockAlarmPage());
    }
}
