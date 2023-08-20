package me.beary.jwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@RequestMapping("system")
@RestController
public class SystemController {

    @GetMapping("health")
    public String health() {
        var now = ZonedDateTime.now();
        return DateTimeFormatter.ISO_ZONED_DATE_TIME.format(now);
    }
}
