package com.oelprince.events.entity;
/*
 * The MIT License
 *
 * Copyright 2018 oelprince.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

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
