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
package com.borkdominik.big.glsp.server.core.handler.operation.create;

import java.util.Set;

import com.borkdominik.big.glsp.server.core.manifest.BGContributionManifest;
import com.borkdominik.big.glsp.server.core.manifest.BGRepresentationManifest;
import com.borkdominik.big.glsp.server.core.manifest.contribution.factory.BGRElementFactorySetContributionModule;
import com.borkdominik.big.glsp.server.core.model.BGTypeProvider;
import com.borkdominik.big.glsp.server.lib.utils.TypeLiteralUtils;

import lombok.Builder;
import lombok.Getter;

public class BGCreateOperationContribution extends BGContributionManifest {

   @Getter
   @Builder
   public static class Options {
      protected BGRepresentationManifest manifest;
      protected Set<BGTypeProvider> elementTypes;
      protected Class<? extends BGCreateNodeHandler> createNodeConcrete;
      protected Class<? extends BGCreateEdgeHandler> createEdgeConcrete;
   }

   protected final Options options;

   public BGCreateOperationContribution(final Options options) {
      this.options = options;
   }

   @Override
   protected void configure() {
      super.configure();

      if (options.createNodeConcrete != null) {
         install(new BGRElementFactorySetContributionModule<>(
            BGRElementFactorySetContributionModule.Options
               .<BGCreateNodeHandler> BGRElementFactorySetContributionModuleBuilder()
               .manifest(this.options.manifest)
               .elementTypes(this.options.elementTypes)
               .contributionType(TypeLiteralUtils.of(BGCreateNodeHandler.class))
               .concreteTypes(Set.of(TypeLiteralUtils.of(options.createNodeConcrete)))
               .build()));
      }

      if (options.createEdgeConcrete != null) {
         install(new BGRElementFactorySetContributionModule<>(
            BGRElementFactorySetContributionModule.Options
               .<BGCreateEdgeHandler> BGRElementFactorySetContributionModuleBuilder()
               .manifest(this.options.manifest)
               .elementTypes(this.options.elementTypes)
               .contributionType(TypeLiteralUtils.of(BGCreateEdgeHandler.class))
               .concreteTypes(Set.of(TypeLiteralUtils.of(options.createEdgeConcrete)))
               .build()));
      }
   }
}
