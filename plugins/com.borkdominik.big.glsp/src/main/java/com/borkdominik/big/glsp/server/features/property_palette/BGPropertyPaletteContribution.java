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
package com.borkdominik.big.glsp.server.features.property_palette;

import java.util.Set;

import com.borkdominik.big.glsp.server.core.manifest.BGContributionManifest;
import com.borkdominik.big.glsp.server.core.manifest.BGRepresentationManifest;
import com.borkdominik.big.glsp.server.core.manifest.contribution.factory.BGRElementFactorySetContributionModule;
import com.borkdominik.big.glsp.server.core.manifest.contribution.representation.BGRItemContributionModule;
import com.borkdominik.big.glsp.server.core.model.BGTypeProvider;
import com.borkdominik.big.glsp.server.features.property_palette.provider.BGPropertyPaletteProvider;
import com.borkdominik.big.glsp.server.features.property_palette.provider.BGPropertyProvider;
import com.borkdominik.big.glsp.server.lib.utils.TypeLiteralUtils;

import lombok.Builder;
import lombok.Getter;

public class BGPropertyPaletteContribution extends BGContributionManifest {

   @Getter
   @Builder
   public static class Options {
      protected BGRepresentationManifest manifest;
      protected Set<BGTypeProvider> elementTypes;
      protected Set<Class<? extends BGPropertyProvider>> propertyProviders;
      protected Class<? extends BGPropertyPaletteProvider> defaultPaletteProvider;
      protected Class<? extends BGPropertyPaletteProvider> paletteProvider;
   }

   protected final Options options;

   public BGPropertyPaletteContribution() {
      this(Options.builder().build());
   }

   public BGPropertyPaletteContribution(final Options options) {
      this.options = options;
   }

   @Override
   protected void configure() {
      super.configure();

      install(new BGRItemContributionModule<>(BGRItemContributionModule.Options
         .<BGPropertyPaletteProvider> BGRItemContributionModuleBuilder()
         .manifest(this.options.manifest)
         .contributionType(TypeLiteralUtils.of(BGPropertyPaletteProvider.class))
         .concreteType(TypeLiteralUtils.of(this.options.defaultPaletteProvider))
         .build()));

      install(new BGRElementFactorySetContributionModule<>(
         BGRElementFactorySetContributionModule.Options
            .<BGPropertyPaletteProvider> BGRElementFactorySetContributionModuleBuilder()
            .manifest(this.options.manifest)
            .elementTypes(this.options.elementTypes)
            .contributionType(TypeLiteralUtils.of(BGPropertyPaletteProvider.class))
            .concreteTypes(TypeLiteralUtils.ofs(options.paletteProvider))
            .build()));

      install(new BGRElementFactorySetContributionModule<>(
         BGRElementFactorySetContributionModule.Options
            .<BGPropertyProvider> BGRElementFactorySetContributionModuleBuilder()
            .manifest(this.options.manifest)
            .elementTypes(this.options.elementTypes)
            .contributionType(TypeLiteralUtils.of(BGPropertyProvider.class))
            .concreteTypes(TypeLiteralUtils.ofs(options.propertyProviders))
            .build()));

   }
}
