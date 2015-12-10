package com.geese.server.dao.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Created by Ni on 2015-10-30.
 *
 * MySQL does not store timezone information in its timestamps
 * The JDBC connector by default assumes the dates in the db
 * have a timezone of where the db server is hosted.
 *
 * This quickly becomes confusing as you scale to multiple dbs,
 * additionally, if a db is ever migrated to another time zone,
 * the data becomes invalid.
 *
 * A common industry practice is to only store UTC times in DBs,
 * which means converting them before persisting and after retrieval
 *
 * Java 8 features a decent alternative to Joda Time, and the flow goes as such
 * LocalDateTime(implied current timezone) -> Instant(convert to UTC) -> java.sql.Timestamp(now UTC, can be stored)
 * java.sql.Timestamp(assume to be UTC) -> Instant -> LocalDatetime(conversion to local timezone should be automatic)
 *
 * This convenience class was written to help developers in case they forget
 * the correct way of making the conversion.
 */
public class TimeHelper {
    public static Timestamp toDB(LocalDateTime time) {
        if (time == null) {
            return null;
        }
        return Timestamp.from(time.toInstant(ZoneOffset.UTC));
    }

    public static LocalDateTime fromDB(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
