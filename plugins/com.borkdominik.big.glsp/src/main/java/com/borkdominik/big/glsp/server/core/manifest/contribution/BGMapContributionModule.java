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

import com.borkdominik.big.glsp.server.core.manifest.contribution.representation.BGRContributionModule;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.MapBinder;

import lombok.experimental.SuperBuilder;

public class BGMapContributionModule<TKey, TContribution, TOptions extends BGMapContributionModule.Options<TKey, TContribution>>
   extends
   BGContributionModule<TContribution, MapBinder<TKey, TContribution>, TOptions> {

   @SuperBuilder(builderMethodName = "BGMapContributionModuleBuilder")
   public static class Options<TKey, TContribution> extends BGRContributionModule.Options<TContribution> {
      public TypeLiteral<TKey> keyType;
      public Consumer<MapBinder<TKey, TContribution>> consumer;
   }

   public BGMapContributionModule(final TOptions options) {
      super(options);
   }

   @Override
   protected MapBinder<TKey, TContribution> initContributionBinding() {
      if (options.bindingName != null) {
         return MapBinder.newMapBinder(binder(), options.keyType, options.contributionType, options.bindingName);
      }

      return MapBinder.newMapBinder(binder(), options.keyType, options.contributionType);
   }

   @Override
   protected void contribute(final MapBinder<TKey, TContribution> binding) {
      options.consumer.accept(binding);
   }
}
