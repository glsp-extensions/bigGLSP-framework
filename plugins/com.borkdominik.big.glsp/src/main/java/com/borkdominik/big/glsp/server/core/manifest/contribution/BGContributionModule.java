/********************************************************************************
 * Copyright (c) 2022 borkdominik and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0, or the MIT License which is
 * available at https://opensource.org/licenses/MIT.
 *
 * SPDX-License-Identifier: EPL-2.0 OR MIT
 ********************************************************************************/
package com.borkdominik.big.glsp.server.core.manifest.contribution;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Named;

import lombok.experimental.SuperBuilder;

public abstract class BGContributionModule<TContribution, TBinding, TOptions extends BGContributionModule.Options<TContribution>>
   extends AbstractModule {

   @SuperBuilder(builderMethodName = "BGContributionModuleBuilder")
   public static class Options<TContribution> {
      public TypeLiteral<TContribution> contributionType;
      public Named bindingName;
   }

   protected TOptions options;

   public BGContributionModule(final TOptions options) {
      this.options = options;
   }

   protected boolean isDefinition() { return false; }

   @Override
   protected void configure() {
      super.configure();

      onBeforeContributionBinding();
      var binding = initContributionBinding();
      if (!isDefinition()) {
         onBeforeContribute();
         contribute(binding);
      }
   }

   protected void onBeforeContributionBinding() {}

   protected abstract TBinding initContributionBinding();

   protected void onBeforeContribute() {}

   protected abstract void contribute(TBinding binding);

   protected void bindAdditionals(final TBinding binding) {}
}
