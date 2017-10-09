/*
 * Copyright 2017, Google Inc. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 *     * Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above
 * copyright notice, this list of conditions and the following disclaimer
 * in the documentation and/or other materials provided with the
 * distribution.
 *     * Neither the name of Google Inc. nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.google.api.gax.rpc;

import com.google.api.core.AbstractApiFuture;
import com.google.api.core.BetaApi;
import com.google.auto.value.AutoValue;
import java.util.Set;

/** Data necessary to translate an API call exception to a transport-agnostic form. */
@AutoValue
@BetaApi
public abstract class TranslateExceptionParameters {
  public abstract Throwable getThrowable();

  public abstract Set<StatusCode> getRetryableCodes();

  public abstract boolean isCancelled();

  public abstract AbstractApiFuture<?> getResultFuture();

  public static Builder newBuilder() {
    return new AutoValue_TranslateExceptionParameters.Builder();
  }

  @AutoValue.Builder
  public abstract static class Builder {
    public abstract Builder setThrowable(Throwable throwable);

    public abstract Builder setRetryableCodes(Set<StatusCode> retryableCodes);

    public abstract Builder setCancelled(boolean cancelled);

    public abstract Builder setResultFuture(AbstractApiFuture<?> resultFuture);

    public abstract TranslateExceptionParameters build();
  }
}