package springframework.msscbrewery.web.mappers;

import org.mapstruct.Mapper;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

@Mapper
public class DateMapper {
	public static Timestamp asTimestamp(java.time.OffsetDateTime offsetDateTime) {
		if (offsetDateTime != null) {
			return Timestamp.valueOf(offsetDateTime.atZoneSameInstant(ZoneId.systemDefault())
					.toLocalDateTime());
		} else {
			return null;
		}
	}

	public static OffsetDateTime asOffsetDateTime(Timestamp timestamp) {
		if (timestamp != null) {
			return OffsetDateTime.of(timestamp.toLocalDateTime(), ZoneOffset.UTC);
		} else {
			return null;
		}
	}
}
