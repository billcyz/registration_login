import java.sql.Timestamp;

public class TimeStamp {
	// return current epoch timestamp in milliseconds
	long getEpochTime() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return timestamp.getTime();
	}
}
