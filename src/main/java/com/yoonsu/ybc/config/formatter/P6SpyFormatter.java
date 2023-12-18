package com.yoonsu.ybc.config.formatter;

import com.p6spy.engine.logging.Category;
import com.p6spy.engine.spy.P6SpyOptions;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import jakarta.annotation.PostConstruct;
import org.hibernate.engine.jdbc.internal.FormatStyle;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ClassUtils;

import java.util.Locale;

/**
 * packageName    : com.yoonsu.ybc.config.formatter
 * fileName       : P6SpyFormatter
 * author         : yoons
 * date           : 2023-12-18
 * description    : SQL 로그 라이브러리(P6Spy) 설정
 */
@Configuration
public class P6SpyFormatter implements MessageFormattingStrategy {
    private final String myPackage = "com.yoonsu.ybc";
    private final boolean isStacktrace = true;
    private final boolean isMultiline = true;

    @PostConstruct
    public void postConstruct() {
        P6SpyOptions.getActiveInstance().setLogMessageFormat(this.getClass().getName());
    }

    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
        // commit
        if (category.contains("commit")) return String.format("[%s] | %d ms", category, elapsed);

        // statement
        String infoMessage = isStacktrace ? String.format("[%s] | %d ms | %s", category, elapsed, stackTrace()) : String.format("[%s] | %d ms", category, elapsed, stackTrace());
        String sqlMessage = String.format("%s", formatSql(category, sql, isMultiline));
        return infoMessage + sqlMessage;
    }

    private String formatSql(String category, String sql, Boolean multiLine) {
        if (sql != null && !sql.trim().isEmpty() && Category.STATEMENT.getName().equals(category)) {
            String trimmedSQL = sql.trim().toLowerCase(Locale.ROOT);
            if (trimmedSQL.startsWith("create") || trimmedSQL.startsWith("alter") || trimmedSQL.startsWith("comment")) {
                sql = FormatStyle.DDL.getFormatter().format(sql);
            } else if (multiLine) {
                sql = FormatStyle.BASIC.getFormatter().format(sql);
            } else {
                sql = "\n" + FormatStyle.NONE.getFormatter().format(sql);
            }
            return sql;
        }
        return sql;
    }

    public String stackTrace() {
        StackTraceElement[] stacks = (new Throwable()).getStackTrace();
        for (StackTraceElement element : stacks) {
            String trace = element.toString();
            boolean isMyPackage = trace.toString().startsWith(myPackage);
            boolean isTraceClass = !trace.toString().contains(ClassUtils.getUserClass(this).getName());
            if (isMyPackage && isTraceClass) return trace;
        }
        return "";
    }
}

