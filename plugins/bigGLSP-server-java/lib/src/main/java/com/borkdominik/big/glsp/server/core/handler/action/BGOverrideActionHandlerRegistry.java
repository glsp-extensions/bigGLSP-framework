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
package com.borkdominik.big.glsp.server.core.handler.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.glsp.server.actions.Action;
import org.eclipse.glsp.server.actions.ActionHandler;

import com.borkdominik.big.glsp.server.lib.registry.BGClassRegistry;
import com.borkdominik.big.glsp.server.lib.registry.BGRegistryKey;
import com.google.inject.Inject;

public class BGOverrideActionHandlerRegistry
   extends BGClassRegistry<Class<? extends Action>, List<ActionHandler>> {

   @Inject
   public BGOverrideActionHandlerRegistry(
      final Map<Enumerator, Set<ActionHandler>> handlers) {
      handlers.entrySet().forEach(e -> {
         var representation = e.getKey();

         e.getValue().forEach(handler -> {
            handler.getHandledActionTypes().forEach(a -> {
               var key = BGRegistryKey.<Class<? extends Action>> of(representation, a);
               if (hasKey(key)) {
                  var list = get(key).get();
                  if (list instanceof ArrayList<?>) {
                     ((ArrayList) list).add(handler);
                  } else {
                     printContent();
                     throw new NoSuchElementException(
                        String.format("[%s] Cannot add to representation %s and key %s",
                           this.getClass().getSimpleName(), key.representation.getName(), deriveKey(key)));
                  }
               } else {
                  register(key, new ArrayList<>(List.of(handler)));
               }
            });
         });
      });

      // printContent();
   }
}
