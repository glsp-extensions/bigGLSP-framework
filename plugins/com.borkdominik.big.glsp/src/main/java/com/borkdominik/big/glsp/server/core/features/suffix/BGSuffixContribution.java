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
package com.borkdominik.big.glsp.server.core.features.suffix;

import java.util.function.Consumer;

import com.borkdominik.big.glsp.server.core.manifest.BGContributionManifest;
import com.borkdominik.big.glsp.server.core.manifest.BGRepresentationManifest;
import com.borkdominik.big.glsp.server.core.manifest.contribution.BGMapContributionModule;
import com.borkdominik.big.glsp.server.lib.utils.TypeLiteralUtils;
import com.google.inject.multibindings.MapBinder;

import lombok.Builder;
import lombok.Getter;

public class BGSuffixContribution extends BGContributionManifest {

   @Getter
   @Builder
   public static class Options {
      protected BGRepresentationManifest manifest;
      protected Consumer<MapBinder<String, BGSuffixIdAppender>> suffix;
   }

   protected final Options options;

   public BGSuffixContribution(final Options options) {
      this.options = options;
   }

   @Override
   protected void configure() {
      super.configure();

      install(new BGMapContributionModule<>(
         BGMapContributionModule.Options.<String, BGSuffixIdAppender> BGMapContributionModuleBuilder()
            .contributionType(TypeLiteralUtils.of(BGSuffixIdAppender.class))
            .keyType(TypeLiteralUtils.of(String.class))
            .consumer(this.options.suffix)
            .build()));
   }
}
