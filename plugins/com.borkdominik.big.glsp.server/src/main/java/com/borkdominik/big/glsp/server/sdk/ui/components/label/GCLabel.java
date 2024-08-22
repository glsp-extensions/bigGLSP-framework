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
package com.borkdominik.big.glsp.server.sdk.ui.components.label;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.glsp.graph.GEdgePlacement;
import org.eclipse.glsp.graph.GLabel;
import org.eclipse.glsp.graph.builder.impl.GLabelBuilder;

import com.borkdominik.big.glsp.server.core.constants.BGCoreTypes;
import com.borkdominik.big.glsp.server.sdk.cdk.GCModelContext;
import com.borkdominik.big.glsp.server.sdk.cdk.base.GCIdentifiable;
import com.borkdominik.big.glsp.server.sdk.cdk.gmodel.GCModelElement;

import lombok.Builder;
import lombok.Singular;
import lombok.experimental.SuperBuilder;

public class GCLabel extends GCModelElement<EObject, GLabel> implements GCIdentifiable {
   public static final String CSS_ID = "gc-label";
   protected GLabel rootGModel;

   protected Options options;

   public GCLabel(final GCModelContext context, final EObject source, final Options options) {
      super(context, source);

      this.options = options;
   }

   @Override
   public String getIdentifier() { return CSS_ID; }

   public Options getOptions() { return options; }

   @Override
   protected Optional<GLabel> createRootGModel() {
      var labelBuilder = new GLabelBuilder(options.type)
         .id(context.idCountGenerator.getOrCreateId(origin))
         .addCssClasses(options.css)
         .edgePlacement(options.edgePlacement)
         .addArguments(options.arguments)
         .text(options.label);

      options.id.ifPresent(id -> labelBuilder.id(id));

      return Optional.of(labelBuilder.build());
   }

   @SuperBuilder
   public static class Options {
      public String label;
      @Builder.Default
      public Optional<String> id = Optional.empty();

      @Builder.Default
      public String type = BGCoreTypes.LABEL_TEXT;

      @Singular(value = "css")
      public List<String> css;

      @Singular
      public Map<String, Object> arguments;

      public GEdgePlacement edgePlacement;
   }

}
