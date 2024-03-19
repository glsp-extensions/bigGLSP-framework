/********************************************************************************
 * Copyright (c) 2024 borkdominik and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0, or the MIT License which is
 * available at https://opensource.org/licenses/MIT.
 *
 * SPDX-License-Identifier: EPL-2.0 OR MIT
 ********************************************************************************/
package com.borkdominik.big.glsp.server.features.property_palette.provider;

import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.glsp.server.emf.EMFIdGenerator;

import com.borkdominik.big.glsp.server.core.model.BGModelState;
import com.borkdominik.big.glsp.server.core.model.BGTypeProvider;
import com.borkdominik.big.glsp.server.core.model.BGTypeProviderAll;
import com.borkdominik.big.glsp.server.features.property_palette.model.PropertyPalette;
import com.google.inject.Inject;

public class BGDefaultPropertyPaletteProvider implements BGPropertyPaletteProvider {

   @Inject
   protected BGModelState modelState;
   @Inject
   protected BGPropertyProviderRegistry registry;
   @Inject
   protected EMFIdGenerator idGenerator;

   @Override
   public Set<BGTypeProvider> getHandledElementTypes() { return Set.of(BGTypeProviderAll.instance); }

   @Override
   public PropertyPalette provide(final EObject element) {
      var providers = registry.retrieve(modelState.representation().getUnsafe(), element.getClass());

      return new PropertyPalette(
         idGenerator.getOrCreateId(element),
         getLabel(element),
         providers.stream().flatMap(p -> p.provide(element).stream()).toList());
   }

   protected String getLabel(final EObject element) {
      return element.getClass().getSimpleName();
   }
}
