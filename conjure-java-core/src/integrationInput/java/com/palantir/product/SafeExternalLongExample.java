package com.palantir.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.palantir.logsafe.Preconditions;
import com.palantir.logsafe.Safe;
import com.palantir.logsafe.SafeArg;
import com.palantir.logsafe.exceptions.SafeIllegalArgumentException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;

@Safe
@JsonDeserialize(builder = SafeExternalLongExample.Builder.class)
@Generated("com.palantir.conjure.java.types.BeanGenerator")
public final class SafeExternalLongExample {
    private final @Safe Long safeExternalLongValue;

    private SafeExternalLongExample(@Safe Long safeExternalLongValue) {
        validateFields(safeExternalLongValue);
        this.safeExternalLongValue = safeExternalLongValue;
    }

    @JsonProperty("safeExternalLongValue")
    public @Safe Long getSafeExternalLongValue() {
        return this.safeExternalLongValue;
    }

    @Override
    public boolean equals(@Nullable Object other) {
        return this == other || (other instanceof SafeExternalLongExample && equalTo((SafeExternalLongExample) other));
    }

    private boolean equalTo(SafeExternalLongExample other) {
        return this.safeExternalLongValue.equals(other.safeExternalLongValue);
    }

    @Override
    public int hashCode() {
        return this.safeExternalLongValue.hashCode();
    }

    @Override
    @Safe
    public String toString() {
        return "SafeExternalLongExample{safeExternalLongValue: " + safeExternalLongValue + '}';
    }

    public static SafeExternalLongExample of(@Safe Long safeExternalLongValue) {
        return builder().safeExternalLongValue(safeExternalLongValue).build();
    }

    private static void validateFields(@Safe Long safeExternalLongValue) {
        List<String> missingFields = null;
        missingFields = addFieldIfMissing(missingFields, safeExternalLongValue, "safeExternalLongValue");
        if (missingFields != null) {
            throw new SafeIllegalArgumentException(
                    "Some required fields have not been set", SafeArg.of("missingFields", missingFields));
        }
    }

    private static List<String> addFieldIfMissing(List<String> prev, Object fieldValue, String fieldName) {
        List<String> missingFields = prev;
        if (fieldValue == null) {
            if (missingFields == null) {
                missingFields = new ArrayList<>(1);
            }
            missingFields.add(fieldName);
        }
        return missingFields;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated("com.palantir.conjure.java.types.BeanBuilderGenerator")
    public static final class Builder {
        boolean _buildInvoked;

        private @Safe Long safeExternalLongValue;

        private Builder() {}

        public Builder from(SafeExternalLongExample other) {
            checkNotBuilt();
            safeExternalLongValue(other.getSafeExternalLongValue());
            return this;
        }

        @JsonSetter("safeExternalLongValue")
        public Builder safeExternalLongValue(@Nonnull @Safe Long safeExternalLongValue) {
            checkNotBuilt();
            this.safeExternalLongValue =
                    Preconditions.checkNotNull(safeExternalLongValue, "safeExternalLongValue cannot be null");
            return this;
        }

        public SafeExternalLongExample build() {
            checkNotBuilt();
            this._buildInvoked = true;
            return new SafeExternalLongExample(safeExternalLongValue);
        }

        private void checkNotBuilt() {
            Preconditions.checkState(!_buildInvoked, "Build has already been called");
        }
    }
}
