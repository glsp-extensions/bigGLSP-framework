/********************************************************************************
 * Copyright (c) 2022 borkdominik and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0, or the MIT License which is
 * available at https://opensource.org/licenses/MIT.
 *
 * SPDX-License-Identifier: EPL-2.0 OR MIT
 ********************************************************************************/
package com.borkdominik.big.glsp.server.lib.registry;

import java.util.List;
import java.util.stream.Collectors;

public abstract class BGClassRegistry<K extends Class<?>, V> extends BGRepresentationMultiKeyRegistry<K, V> {

   @Override
   protected String deriveKey(final BGRegistryKey<K> key) {
      var representation = key.representation;
      var clazz = key.key;

      if (clazz.isInterface()) {
         return representation.getName() + ":" + clazz;
      }

      var interfaces = List.of(clazz.getInterfaces());
      var keys = keys().stream().filter(k -> k.representation.equals(representation)).map(k -> k.key)
         .collect(Collectors.toSet());

      var found = keys.stream().filter(k -> interfaces.contains(k)).findFirst();
      if (found.isPresent()) {
         return representation.getName() + ":" + found.get();
      }

      return super.deriveKey(key);
   }
}
