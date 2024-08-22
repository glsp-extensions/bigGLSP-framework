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
package com.borkdominik.big.glsp.server.sdk.ui.components.header;

import java.util.Optional;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.glsp.graph.DefaultTypes;
import org.eclipse.glsp.graph.GModelElement;
import org.eclipse.glsp.graph.util.GConstants;

import com.borkdominik.big.glsp.server.core.constants.BGPaddingValues;
import com.borkdominik.big.glsp.server.sdk.cdk.GCModelContext;
import com.borkdominik.big.glsp.server.sdk.cdk.base.GCIdentifiable;
import com.borkdominik.big.glsp.server.sdk.cdk.gmodel.GCModelList;
import com.borkdominik.big.glsp.server.sdk.gmodel.BCCompartmentBuilder;
import com.borkdominik.big.glsp.server.sdk.gmodel.BCLayoutOptions;

import lombok.Builder;
import lombok.experimental.SuperBuilder;

public class GCHeader extends GCModelList<EObject, GModelElement> implements GCIdentifiable {
   public static final String CSS_ID = "gc-header";
   protected final Boolean fullHeight;

   public GCHeader(final GCModelContext context, final EObject source) {
      this(context, source, Options.builder().build());
   }

   public GCHeader(final GCModelContext context, final EObject source, final Options options) {
      super(context, source);
      this.fullHeight = options.fullHeight;
   }

   @Override
   public String getIdentifier() { return CSS_ID; }

   @Override
   protected Optional<GModelElement> createRootGModel() {
      if (!this.fullHeight) {
         return Optional.of(new BCCompartmentBuilder<>(origin, context)
            .type(DefaultTypes.COMPARTMENT_HEADER)
            .layout(GConstants.Layout.VBOX)
            .layoutOptions(new BCLayoutOptions()
               .padding(BGPaddingValues.LEVEL_1, BGPaddingValues.LEVEL_2)
               .hGrab(true)
               .hAlign(GConstants.HAlign.CENTER))
            .build());
      }

      return Optional.of(new BCCompartmentBuilder<>(origin, context)
         .layout(GConstants.Layout.HBOX)
         .layoutOptions(new BCLayoutOptions()
            .padding(BGPaddingValues.LEVEL_1, BGPaddingValues.LEVEL_2)
            .hGrab(true)
            .vGrab(true)
            .vAlign(GConstants.VAlign.CENTER))
         .build());
   }

   @Override
   protected Optional<GModelElement> createChildrenGModel() {
      if (!fullHeight) {
         return Optional.empty();
      }

      return Optional.of(new BCCompartmentBuilder<>(origin, context)
         .layout(GConstants.Layout.VBOX)
         .layoutOptions(new BCLayoutOptions()
            .hGrab(true)
            .hAlign(GConstants.HAlign.CENTER))
         .build());
   }

   @SuperBuilder
   public static class Options {
      @Builder.Default
      public boolean fullHeight = false;
   }
}
