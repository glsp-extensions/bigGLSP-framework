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
package com.borkdominik.big.glsp.server.core.manifest.contribution.representation;

import java.util.Set;
import java.util.function.Consumer;

import org.eclipse.emf.common.util.Enumerator;

import com.borkdominik.big.glsp.server.lib.utils.TypeLiteralUtils;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.MapBinder;
import com.google.inject.multibindings.Multibinder;

import lombok.experimental.SuperBuilder;

public class BGRSetContributionModule<TContribution, TOptions extends BGRSetContributionModule.Options<TContribution>>
   extends BGRContributionModule<TContribution, Set<TContribution>, TOptions> {

   protected TypeLiteral<Set<TContribution>> contributionSetType;

   @SuperBuilder(builderMethodName = "BGRSetContributionModuleBuilder")
   public static class Options<TContribution> extends BGRContributionModule.Options<TContribution> {
      public Set<TypeLiteral<? extends TContribution>> concretes;
      public Consumer<Multibinder<TContribution>> consumer;
   }

   public BGRSetContributionModule(final TOptions options) {
      super(options);
   }

   @Override
   protected boolean isDefinition() {
      return super.isDefinition() || (options.concretes == null && options.consumer == null);
   }

   @Override
   protected void onBeforeContributionBinding() {
      super.onBeforeContributionBinding();
      this.contributionSetType = TypeLiteralUtils.parameterizedType(Set.class, this.options.contributionType);
   }

   @Override
   protected MapBinder<Enumerator, Set<TContribution>> initContributionBinding() {
      if (options.bindingName != null) {
         return MapBinder.newMapBinder(binder(), new TypeLiteral<Enumerator>() {}, contributionSetType,
            options.bindingName);
      }

      return MapBinder.newMapBinder(binder(), new TypeLiteral<Enumerator>() {}, contributionSetType);

   }

   @Override
   protected void contribute(final MapBinder<Enumerator, Set<TContribution>> defined) {
      var binder = binder();

      var multibinder = Multibinder.newSetBinder(binder, this.options.contributionType, options.manifest.idNamed());

      if (options.concretes != null) {
         options.concretes.forEach(concrete -> {
            multibinder.addBinding().to(concrete);
         });
      }

      if (options.consumer != null) {
         options.consumer.accept(multibinder);
      }

      var key = Key.get(contributionSetType, options.manifest.idNamed());
      defined
         .addBinding(options.manifest.representation())
         .to(key);
   }
}
