package springframework.msscbeerorder.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@SuperBuilder
public class BaseEntity {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
			name = "UUID",
			strategy = "org.hibernate.id.UUIDGenerator"
	)
	@Type(type = "org.hibernate.type.UUIDCharType")
	@Column(
			length = 36,
			columnDefinition = "varchar(36)",
			updatable = false,
			nullable = false
	)
	private UUID id;
	@Version
	private Long version;
	@CreationTimestamp
	@Column(updatable = false)
	private Timestamp createdDate;
	@UpdateTimestamp
	private Timestamp lastModifiedDate;

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
			return false;
		}
		BaseEntity that = (BaseEntity) o;
		return id != null && Objects.equals(id, that.id);
	}
}
