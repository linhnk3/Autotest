package generator;

import java.sql.Timestamp;

public class TimeStamp {
    public static long TimeStamps(long ts1) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        long ts = timestamp.getTime();
        return ts;
    }
}
