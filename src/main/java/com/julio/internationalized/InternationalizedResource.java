package com.julio.internationalized;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequestMapping("/internationalized")
public class InternationalizedResource {

    @Autowired
    private MessageSource messageSource;

    @GetMapping
    public String returnGoodMorning() {
        return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
    }


}
