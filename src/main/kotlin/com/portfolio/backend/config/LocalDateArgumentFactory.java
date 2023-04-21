package com.portfolio.backend.config;

import org.jdbi.v3.core.argument.AbstractArgumentFactory;
import org.jdbi.v3.core.argument.Argument;
import org.jdbi.v3.core.config.ConfigRegistry;

import java.sql.Types;
import java.time.LocalDate;

public class LocalDateArgumentFactory extends AbstractArgumentFactory<LocalDate> {

    public LocalDateArgumentFactory() {
        super(Types.DATE);
    }

    @Override
    protected Argument build(LocalDate value, ConfigRegistry config) {
        return (position, statement, ctx) -> statement.setDate(position, java.sql.Date.valueOf(value));
    }
}
