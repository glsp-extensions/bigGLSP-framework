/********************************************************************************
 * Copyright (c) 2023 borkdominik and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0, or the MIT License which is
 * available at https://opensource.org/licenses/MIT.
 *
 * SPDX-License-Identifier: EPL-2.0 OR MIT
 ********************************************************************************/
package com.borkdominik.big.glsp.server.sdk.ui.components.list;

import java.util.Optional;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.glsp.graph.GModelElement;

import com.borkdominik.big.glsp.server.core.constants.BGPaddingValues;
import com.borkdominik.big.glsp.server.sdk.cdk.GCModelContext;
import com.borkdominik.big.glsp.server.sdk.cdk.base.GCIdentifiable;
import com.borkdominik.big.glsp.server.sdk.cdk.gmodel.GCModelElement;
import com.borkdominik.big.glsp.server.sdk.gmodel.BCCompartmentBuilder;
import com.borkdominik.big.glsp.server.sdk.gmodel.BCLayoutOptions;

import lombok.experimental.SuperBuilder;

public class GCListItem extends GCModelElement<EObject, GModelElement> implements GCIdentifiable {
   public static final String CSS_ID = "gc-list-item";
   protected final GModelElement item;

   public GCListItem(final GCModelContext context, final EObject source, final GModelElement item) {
      this(context, source, item, Options.builder().build());
   }

   public GCListItem(final GCModelContext context, final EObject source, final GModelElement item,
      final Options options) {
      super(context, source);
      this.item = item;
   }

   @Override
   public String getIdentifier() { return CSS_ID; }

   @Override
   protected Optional<GModelElement> createRootGModel() {
      return Optional.of(new BCCompartmentBuilder<>(origin, context)
         .withVBoxLayout()
         .addLayoutOptions(new BCLayoutOptions()
            .padding(BGPaddingValues.NONE, BGPaddingValues.LEVEL_2))
         .build());
   }

   @Override
   protected void extendGModel(final GModelElement gmodel) {
      gmodel.getChildren().add(item);
   }

   @SuperBuilder
   public static class Options {}

}
