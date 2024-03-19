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
package com.borkdominik.big.glsp.server.core.manifest.contribution;

import java.util.function.Consumer;

import com.google.inject.multibindings.Multibinder;

import lombok.experimental.SuperBuilder;

public final class BGSetContributionModule<TContribution, TOptions extends BGSetContributionModule.Options<TContribution>>
   extends
   BGContributionModule<TContribution, Multibinder<TContribution>, TOptions> {

   @SuperBuilder(builderMethodName = "BGSetContributionModuleBuilder")
   public static class Options<TContribution> extends BGContributionModule.Options<TContribution> {
      public Consumer<Multibinder<TContribution>> consumer;
   }

   public BGSetContributionModule(final TOptions options) {
      super(options);
   }

   @Override
   protected boolean isDefinition() { return super.isDefinition() || options.consumer == null; }

   @Override
   protected Multibinder<TContribution> initContributionBinding() {
      if (options.bindingName != null) {
         return Multibinder.newSetBinder(binder(), options.contributionType, options.bindingName);
      }

      return Multibinder.newSetBinder(binder(), options.contributionType);
   }

   @Override
   protected void contribute(final Multibinder<TContribution> binding) {
      options.consumer.accept(binding);
   }
}
