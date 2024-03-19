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
package com.borkdominik.big.glsp.server.core.handler.operation.delete;

import java.util.Set;

import com.borkdominik.big.glsp.server.core.manifest.BGContributionManifest;
import com.borkdominik.big.glsp.server.core.manifest.BGRepresentationManifest;
import com.borkdominik.big.glsp.server.core.manifest.contribution.factory.BGRElementFactorySetContributionModule;
import com.borkdominik.big.glsp.server.core.manifest.contribution.representation.BGRItemContributionModule;
import com.borkdominik.big.glsp.server.core.model.BGTypeProvider;
import com.borkdominik.big.glsp.server.lib.utils.TypeLiteralUtils;

import lombok.Builder;
import lombok.Getter;

public class BGDeleteOperationContribution extends BGContributionManifest {

   @Getter
   @Builder
   public static class Options {
      protected BGRepresentationManifest manifest;
      protected Set<BGTypeProvider> elementTypes;
      protected Class<? extends BGDeleteHandler> defaultHandler;
      protected Class<? extends BGDeleteHandler> concrete;
   }

   protected final Options options;

   public BGDeleteOperationContribution() {
      this(Options.builder().build());
   }

   public BGDeleteOperationContribution(final Options options) {
      this.options = options;
   }

   @Override
   protected void configure() {
      super.configure();

      install(new BGRItemContributionModule<>(BGRItemContributionModule.Options
         .<BGDeleteHandler> BGRItemContributionModuleBuilder()
         .manifest(this.options.manifest)
         .contributionType(TypeLiteralUtils.of(BGDeleteHandler.class))
         .concreteType(TypeLiteralUtils.of(this.options.defaultHandler))
         .build()));

      install(new BGRElementFactorySetContributionModule<>(
         BGRElementFactorySetContributionModule.Options
            .<BGDeleteHandler> BGRElementFactorySetContributionModuleBuilder()
            .manifest(this.options.manifest)
            .elementTypes(this.options.elementTypes)
            .contributionType(TypeLiteralUtils.of(BGDeleteHandler.class))
            .concreteTypes(TypeLiteralUtils.ofs(options.concrete))
            .build()));
   }
}
