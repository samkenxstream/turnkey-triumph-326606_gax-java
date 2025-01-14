/*
 * Copyright 2022 Google LLC
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
 *     * Neither the name of Google LLC nor the names of its
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

import static com.google.common.truth.Truth.assertThat;

import com.google.api.pathtemplate.PathTemplate;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class RequestParamsBuilderTest {

  private RequestParamsBuilder requestParamsBuilder;

  @Before
  public void setUp() {
    requestParamsBuilder = RequestParamsBuilder.create();
  }

  @Test
  public void add_happyPath() {
    String headerKey = "table_location";
    Map<String, String> actual =
        getRoutingHeaders(
            headerKey,
            "projects/**/{table_location=instances/*}",
            "projects/my_cozy_home/instances/living_room");
    assertThat(actual).containsExactly(headerKey, "instances/living_room");
  }

  @Test
  public void add_matchedValuesWithNoRoutingHeaderKey() {
    Map<String, String> actual =
        getRoutingHeaders("table_location", "projects/**", "projects/my_cozy_home/");
    assertThat(actual).isEmpty();
  }

  @Test
  public void add_emptyMatchedValues() {
    Map<String, String> actual =
        getRoutingHeaders(
            "table_location",
            "projects/**/{table_location=instances/*}",
            "projects/does_not_matter");
    assertThat(actual).isEmpty();
  }

  @Test
  public void add_nullFieldValue() {
    Map<String, String> actual = getRoutingHeaders("table_location", "projects/**", null);
    assertThat(actual).isEmpty();
  }

  private Map<String, String> getRoutingHeaders(
      String headerKey, String patternString, String fieldValue) {
    PathTemplate pathTemplate = PathTemplate.create(patternString);
    requestParamsBuilder.add(fieldValue, headerKey, pathTemplate);
    return requestParamsBuilder.build();
  }
}
