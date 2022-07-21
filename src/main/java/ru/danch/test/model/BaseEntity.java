package ru.danch.test.model;

import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;

@MappedSuperclass
public abstract class BaseEntity implements Serializable, Persistable<Long> {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(
            name = "id",
            nullable = false
    )
    protected Long id;
    @Version
    @Column(
            name = "last_editing_date",
            nullable = false
    )
    private @NotNull OffsetDateTime lastEditingDate;

    public BaseEntity() {
    }

    public boolean isNew() {
        return this.id == null;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OffsetDateTime getLastEditingDate() {
        return this.lastEditingDate;
    }

    public void setLastEditingDate(OffsetDateTime lastEditingDate) {
        this.lastEditingDate = lastEditingDate;
    }

    public boolean equals(Object o) {
        boolean isEqual;
        if (this == o) {
            isEqual = true;
        } else if (o == null) {
            isEqual = false;
        } else if (o.getClass() == this.getClass()) {
            BaseEntity that = (BaseEntity)o;
            if (this.getId() == null && that.getId() == null) {
                isEqual = Objects.equals(this.identity(), that.identity());
            } else {
                isEqual = Objects.equals(this.getId(), that.getId());
            }
        } else {
            isEqual = false;
        }

        return isEqual;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.getId() == null ? this.identity() : this.getId()});
    }

    public Object identity() {
        return Objects.hash(id);
    }
}
