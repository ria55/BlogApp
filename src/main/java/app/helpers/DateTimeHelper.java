package app.helpers;

import java.time.LocalDateTime;

/**
 * Do not use this class - use @CreationTimestamp annotation instead.
 */
@Deprecated
public class DateTimeHelper {

    public static LocalDateTime getNow() {
        return LocalDateTime.now();
    }

}
