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
package com.borkdominik.big.glsp.server.core.handler.operation.operation_handler;

import org.eclipse.glsp.server.operations.OperationHandler;

import com.borkdominik.big.glsp.server.core.manifest.BGContributionManifest;
import com.borkdominik.big.glsp.server.core.manifest.BGRepresentationManifest;
import com.borkdominik.big.glsp.server.core.manifest.contribution.BGSetContributionModule;
import com.borkdominik.big.glsp.server.core.manifest.contribution.representation.BGRSetContributionModule;
import com.google.inject.TypeLiteral;

import lombok.Builder;
import lombok.Getter;

public class BGOperationHandlerContribution extends BGContributionManifest {

   @Getter
   @Builder
   public static class Options {
      protected BGRepresentationManifest manifest;
      protected Class<OperationHandler<?>> handlers;
      protected Class<OperationHandler<?>> overrideHandlers;
   }

   protected final Options options;

   public BGOperationHandlerContribution() {
      this(Options.builder().build());
   }

   public BGOperationHandlerContribution(final Options options) {
      this.options = options;
   }

   @Override
   protected void configure() {
      super.configure();

      if (options.handlers != null) {
         install(new BGSetContributionModule<>(
            BGSetContributionModule.Options
               .<OperationHandler<?>> BGSetContributionModuleBuilder()
               .contributionType(new TypeLiteral<OperationHandler<?>>() {})
               .consumer((contribution) -> contribution.addBinding().to(options.handlers))
               .build()));
      }

      install(new BGRSetContributionModule<>(
         BGRSetContributionModule.Options
            .<OperationHandler<?>> BGRSetContributionModuleBuilder()
            .manifest(this.options.manifest)
            .contributionType(new TypeLiteral<OperationHandler<?>>() {})
            .consumer((contribution) -> contribution.addBinding().to(options.overrideHandlers))
            .build()));
   }
}
