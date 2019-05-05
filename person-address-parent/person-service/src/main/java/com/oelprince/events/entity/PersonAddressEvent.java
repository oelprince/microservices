package com.oelprince.events.entity;

import java.time.Instant;
import java.util.Objects;
import javax.json.bind.annotation.JsonbProperty;

/**
 *
 * @author oelprince
 */
public abstract class PersonAddressEvent {
    @JsonbProperty
    private final Instant instant;

    protected PersonAddressEvent() {
        instant = Instant.now();
    }

    protected PersonAddressEvent(final Instant instant) {
        Objects.requireNonNull(instant);
        this.instant = instant;
    }

    public Instant getInstant() {
        return instant;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final PersonAddressEvent that = (PersonAddressEvent) o;

        return instant.equals(that.instant);
    }

    @Override
    public int hashCode() {
        return instant.hashCode();
    }
}
