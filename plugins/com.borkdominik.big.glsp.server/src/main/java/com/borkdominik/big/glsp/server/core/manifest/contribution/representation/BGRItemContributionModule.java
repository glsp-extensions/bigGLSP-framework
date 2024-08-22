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

import org.eclipse.emf.common.util.Enumerator;

import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.MapBinder;

import lombok.experimental.SuperBuilder;

public class BGRItemContributionModule<TContribution>
   extends BGRContributionModule<TContribution, TContribution, BGRItemContributionModule.Options<TContribution>> {

   @SuperBuilder(builderMethodName = "BGRItemContributionModuleBuilder")
   public static class Options<TContribution> extends BGRContributionModule.Options<TContribution> {
      public TypeLiteral<? extends TContribution> concreteType;
   }

   public BGRItemContributionModule(final Options<TContribution> options) {
      super(options);
   }

   @Override
   protected boolean isDefinition() { return super.isDefinition() || options.concreteType == null; }

   @Override
   protected MapBinder<Enumerator, TContribution> initContributionBinding() {
      if (options.bindingName != null) {
         return MapBinder.newMapBinder(binder(), new TypeLiteral<Enumerator>() {}, options.contributionType,
            options.bindingName);
      }

      return MapBinder.newMapBinder(binder(), new TypeLiteral<Enumerator>() {}, options.contributionType);
   }

   @Override
   protected void contribute(final MapBinder<Enumerator, TContribution> defined) {
      defined
         .addBinding(options.manifest.representation())
         .to(options.concreteType);
   }
}
