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
package com.borkdominik.big.glsp.server.core.diagram;

import java.util.Set;

import com.borkdominik.big.glsp.server.core.manifest.BGContributionManifest;
import com.borkdominik.big.glsp.server.core.manifest.BGRepresentationManifest;
import com.borkdominik.big.glsp.server.core.manifest.contribution.factory.BGRFactorySetContributionModule;
import com.borkdominik.big.glsp.server.lib.utils.TypeLiteralUtils;
import com.google.inject.TypeLiteral;

import lombok.Builder;
import lombok.Getter;

public class BGDiagramConfigurationContribution extends BGContributionManifest {

   @Getter
   @Builder
   public static class Options {
      protected BGRepresentationManifest manifest;
      protected Set<TypeLiteral<? extends BGDiagramConfigurationProvider>> concretes;
   }

   protected final Options options;

   public BGDiagramConfigurationContribution() {
      this(Options.builder().build());
   }

   public BGDiagramConfigurationContribution(final Options options) {
      this.options = options;
   }

   @Override
   protected void configure() {
      super.configure();

      install(new BGRFactorySetContributionModule<>(
         BGRFactorySetContributionModule.Options
            .<BGDiagramConfigurationProvider> BGRFactorySetContributionModuleBuilder()
            .manifest(this.options.manifest)
            .contributionType(TypeLiteralUtils.of(BGDiagramConfigurationProvider.class))
            .concreteTypes(options.concretes)
            .build()));
   }
}
