package test.prefix.com.palantir.product;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;

@Generated("com.palantir.conjure.java.types.AliasGenerator")
public final class SafeExternalLongAlias {
    private final long value;

    private SafeExternalLongAlias(long value) {
        this.value = value;
    }

    @JsonValue
    public long get() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(@Nullable Object other) {
        return this == other
                || (other instanceof SafeExternalLongAlias && this.value == ((SafeExternalLongAlias) other).value);
    }

    @Override
    public int hashCode() {
        return Long.hashCode(value);
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static SafeExternalLongAlias valueOf(String value) {
        return of(Long.valueOf(value));
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static SafeExternalLongAlias of(long value) {
        return new SafeExternalLongAlias(value);
    }
}
