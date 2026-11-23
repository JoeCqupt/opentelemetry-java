/*
 * Copyright The OpenTelemetry Authors
 * SPDX-License-Identifier: Apache-2.0
 */

package io.opentelemetry.sdk.logs.internal;

import java.util.Queue;
import org.jctools.queues.atomic.MpscAtomicArrayQueue;

/**
 * Internal accessor of JCTools package for fast queues.
 *
 * <p>This class is internal and is hence not for public use. Its APIs are unstable and can change
 * at any time.
 */
public final class JcTools {

  /**
   * Returns a new {@link Queue} appropriate for use with multiple producers and a single consumer.
   */
  public static <T> Queue<T> newFixedSizeQueue(int capacity) {
    return new MpscAtomicArrayQueue<>(capacity);
  }

  /**
   * Offers an element while keeping the given limit even if the queue's capacity was rounded up.
   */
  public static <T> boolean offerIfBelowThreshold(Queue<T> queue, T element, int threshold) {
    return ((MpscAtomicArrayQueue<T>) queue).offerIfBelowThreshold(element, threshold);
  }

  private JcTools() {}
}
