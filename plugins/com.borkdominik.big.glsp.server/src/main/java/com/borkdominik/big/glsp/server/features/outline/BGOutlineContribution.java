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
package com.borkdominik.big.glsp.server.features.outline;

import com.borkdominik.big.glsp.server.core.manifest.BGContributionManifest;
import com.borkdominik.big.glsp.server.core.manifest.BGRepresentationManifest;
import com.borkdominik.big.glsp.server.core.manifest.contribution.representation.BGRItemContributionModule;
import com.borkdominik.big.glsp.server.features.outline.generator.BGOutlineGenerator;
import com.borkdominik.big.glsp.server.lib.utils.TypeLiteralUtils;

import lombok.Builder;
import lombok.Getter;

public class BGOutlineContribution extends BGContributionManifest {

   @Getter
   @Builder
   public static class Options {
      protected BGRepresentationManifest manifest;
      protected Class<? extends BGOutlineGenerator> concrete;
   }

   protected final Options options;

   public BGOutlineContribution() {
      this(Options.builder().build());
   }

   public BGOutlineContribution(final Options options) {
      this.options = options;
   }

   @Override
   protected void configure() {
      super.configure();

      install(new BGRItemContributionModule<>(
         BGRItemContributionModule.Options
            .<BGOutlineGenerator> BGRItemContributionModuleBuilder()
            .contributionType(TypeLiteralUtils.of(BGOutlineGenerator.class))
            .concreteType(TypeLiteralUtils.of(this.options.concrete))
            .build()));
   }
}
