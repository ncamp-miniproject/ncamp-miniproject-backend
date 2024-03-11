package com.model2.mvc.purchase.util;

import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class LocalDateEditor extends PropertyEditorSupport {
    private final SimpleDateFormat simpleDateFormat;

    public LocalDateEditor(SimpleDateFormat simpleDateFormat) {
        this.simpleDateFormat = simpleDateFormat;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try {
            Date parsed = this.simpleDateFormat.parse(text);
            Instant instant = parsed.toInstant();
            ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
            LocalDate localDate = zonedDateTime.toLocalDate();
            super.setValue(localDate);
        } catch (ParseException e) {
            super.setValue(null);
        }
    }
}
