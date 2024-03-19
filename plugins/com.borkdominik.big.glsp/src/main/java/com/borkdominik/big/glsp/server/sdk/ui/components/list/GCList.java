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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.glsp.graph.GModelElement;

import com.borkdominik.big.glsp.server.core.constants.BGGapValues;
import com.borkdominik.big.glsp.server.core.constants.BGPaddingValues;
import com.borkdominik.big.glsp.server.sdk.cdk.GCModelContext;
import com.borkdominik.big.glsp.server.sdk.cdk.base.GCIdentifiable;
import com.borkdominik.big.glsp.server.sdk.cdk.base.GCProvider;
import com.borkdominik.big.glsp.server.sdk.cdk.gmodel.GCModelList;
import com.borkdominik.big.glsp.server.sdk.gmodel.BCCompartmentBuilder;
import com.borkdominik.big.glsp.server.sdk.gmodel.BCDividerBuilder;
import com.borkdominik.big.glsp.server.sdk.gmodel.BCLayoutOptions;

import lombok.Builder;
import lombok.experimental.SuperBuilder;

public class GCList extends GCModelList<EObject, GModelElement> implements GCIdentifiable {
   public static final String CSS_ID = "gc-list";
   protected Options options;
   protected ArrayList<Integer> dividerIndex = new ArrayList<>();

   public GCList(final GCModelContext context, final EObject source) {
      this(context, source, Options.builder().build());
   }

   public GCList(final GCModelContext context, final EObject source, final Options options) {
      super(context, source);
      this.options = options;
   }

   @Override
   public String getIdentifier() { return CSS_ID; }

   @Override
   public boolean add(final GCProvider e) {
      dividerIndex.add(size());
      return super.add(e);
   }

   @Override
   public boolean addAll(final Collection<? extends GCProvider> children) {
      dividerIndex.add(size());
      return super.addAll(children);
   }

   @Override
   protected void assignChildrenToGModel(final GModelElement rootGModel) {
      var gmodel = getChildrenGModel();
      var children = getAssignableChildren();
      var dividerIterator = dividerIndex.iterator();
      var dividerI = -1;

      for (int i = 0; i < children.size(); i++) {
         if (dividerI != i & dividerIterator.hasNext()) {
            dividerI = dividerIterator.next();
         }
         if (dividerI == i) {
            gmodel.getChildren()
               .add(new BCDividerBuilder(origin, context).build());
         }
         var child = children.get(i);

         assignChildToGModel(gmodel, child);
      }
   }

   @Override
   protected Optional<GModelElement> createRootGModel() {
      return Optional.of(new BCCompartmentBuilder<>(origin, context)
         .withVBoxLayout()
         .addLayoutOptions(new BCLayoutOptions()
            .paddingBottom(BGPaddingValues.LEVEL_1)
            .vGap(BGGapValues.LEVEL_1))
         .build());
   }

   @SuperBuilder
   public static class Options {
      @Builder.Default
      public boolean dividerBeforeInserts = false;
   }

}
