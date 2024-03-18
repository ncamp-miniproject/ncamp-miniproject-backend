package com.model2.mvc.purchase.util;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class LocalDateEditor extends PropertyEditorSupport {
    private static final LocalDateEditor singleton = new LocalDateEditor();

    private final SimpleDateFormat simpleDateFormat;

    private LocalDateEditor() {
        this.simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    public static LocalDateEditor getInstance() {
        return singleton;
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
