/*
 * Copyright 2016, Google Inc.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.census;

import java.nio.ByteBuffer;
import java.util.HashMap;

import javax.annotation.Nullable;

/** Native Implementation of {@link CensusContextFactory} */
class CensusContextFactoryImpl implements CensusContextFactory {
  static final CensusContextImpl DEFAULT = new CensusContextImpl(new HashMap<String, String>(0));

  /**
   * The serialized tags are of the form:
   * {@code (<tag prefix> + 'key' + <tag delim> + 'value')}
   */
  @Override
  @Nullable
  public CensusContextImpl deserialize(ByteBuffer buffer) {
    return CensusSerializer.deserialize(buffer);
  }

  /** Returns the current thread-local {@link CensusContext}. */
  @Override
  public CensusContext getCurrent() {
    return CensusCurrentContext.get();
  }

  /** Returns the default {@link CensusContext}. */
  @Override
  public CensusContext getDefault() {
    return DEFAULT;
  }
}
