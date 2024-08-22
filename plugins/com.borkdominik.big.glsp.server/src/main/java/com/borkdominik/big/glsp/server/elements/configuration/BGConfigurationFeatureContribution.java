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
package com.borkdominik.big.glsp.server.elements.configuration;

import java.util.Set;

import com.borkdominik.big.glsp.server.core.manifest.BGContributionManifest;
import com.borkdominik.big.glsp.server.core.manifest.BGRepresentationManifest;
import com.borkdominik.big.glsp.server.core.manifest.contribution.factory.BGRElementFactorySetContributionModule;
import com.borkdominik.big.glsp.server.core.model.BGTypeProvider;
import com.borkdominik.big.glsp.server.lib.utils.TypeLiteralUtils;

import lombok.Builder;
import lombok.Getter;

public class BGConfigurationFeatureContribution extends BGContributionManifest {

   @Getter
   @Builder
   public static class Options {
      protected BGRepresentationManifest manifest;
      protected Set<BGTypeProvider> elementTypes;
      protected Class<? extends BGEdgeConfiguration> edgeConfiguration;
      protected Class<? extends BGNodeConfiguration> nodeConfiguration;
   }

   protected final Options options;

   public BGConfigurationFeatureContribution(final Options options) {
      this.options = options;
   }

   @Override
   protected void configure() {
      super.configure();

      if (options.nodeConfiguration != null) {
         install(new BGRElementFactorySetContributionModule<>(
            BGRElementFactorySetContributionModule.Options
               .<BGNodeConfiguration> BGRElementFactorySetContributionModuleBuilder()
               .manifest(this.options.manifest)
               .elementTypes(this.options.elementTypes)
               .contributionType(TypeLiteralUtils.of(BGNodeConfiguration.class))
               .concreteTypes(Set.of(TypeLiteralUtils.of(options.nodeConfiguration)))
               .build()));
      }

      if (options.edgeConfiguration != null) {
         install(new BGRElementFactorySetContributionModule<>(
            BGRElementFactorySetContributionModule.Options
               .<BGEdgeConfiguration> BGRElementFactorySetContributionModuleBuilder()
               .manifest(this.options.manifest)
               .elementTypes(this.options.elementTypes)
               .contributionType(TypeLiteralUtils.of(BGEdgeConfiguration.class))
               .concreteTypes(Set.of(TypeLiteralUtils.of(options.edgeConfiguration)))
               .build()));
      }
   }
}
