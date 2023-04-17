/*
 * Copyright The OpenTelemetry Authors
 * SPDX-License-Identifier: Apache-2.0
 */

package io.opentelemetry.sdk.metrics.internal.descriptor;

import com.google.auto.value.AutoValue;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@AutoValue
@Immutable
public abstract class Advice {

  private static final Advice EMPTY_ADVICE = create(null);

  public static Advice empty() {
    return EMPTY_ADVICE;
  }

  public static Advice create(@Nullable List<Double> explicitBucketBoundaries) {
    return new AutoValue_Advice(explicitBucketBoundaries);
  }

  Advice() {}

  @Nullable
  public abstract List<Double> getExplicitBucketBoundaries();
}