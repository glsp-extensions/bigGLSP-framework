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
package com.borkdominik.big.glsp.server.core.manifest.contribution.factory;

import java.util.HashSet;
import java.util.Set;

import com.borkdominik.big.glsp.server.core.di.BGRElementTypeFactory;
import com.borkdominik.big.glsp.server.core.di.DIProvider;
import com.borkdominik.big.glsp.server.core.manifest.contribution.representation.BGRSetContributionModule;
import com.borkdominik.big.glsp.server.core.model.BGTypeProvider;
import com.borkdominik.big.glsp.server.lib.utils.TypeLiteralUtils;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;

import lombok.experimental.SuperBuilder;

public class BGRElementFactorySetContributionModule<TContribution, TOptions extends BGRElementFactorySetContributionModule.Options<TContribution>>
   extends BGRSetContributionModule<TContribution, TOptions> {

   protected Set<TypeLiteral<BGRElementTypeFactory<? extends TContribution>>> factoryTypes = new HashSet<>();

   @SuperBuilder(builderMethodName = "BGRElementFactorySetContributionModuleBuilder")
   public static class Options<TContribution> extends BGRSetContributionModule.Options<TContribution> {
      public Set<BGTypeProvider> elementTypes;
      public Set<TypeLiteral<? extends TContribution>> concreteTypes;
   }

   public BGRElementFactorySetContributionModule(final TOptions options) {
      super(options);
      options.consumer = this::consume;
   }

   @Override
   protected boolean isDefinition() {
      return super.isDefinition() || options.concreteTypes == null || options.concreteTypes.size() == 0;
   }

   @Override
   protected void onBeforeContribute() {
      super.onBeforeContribute();

      this.configureFactory();
   }

   protected void configureFactory() {
      for (var concreteType : options.concreteTypes) {
         TypeLiteral<BGRElementTypeFactory<? extends TContribution>> factoryType = TypeLiteralUtils.parameterizedType(
            BGRElementTypeFactory.class,
            concreteType);

         this.factoryTypes.add(factoryType);
         this.install(new BGRFactoryContributionModule<>(factoryType));
      }
   }

   protected void consume(final Multibinder<TContribution> contribution) {
      for (var factoryType : factoryTypes) {
         var provider = DIProvider.byLiteral(factoryType,
            (fa) -> fa.create(options.manifest.representation(), options.elementTypes));

         contribution.addBinding().toProvider(provider);
      }
   }
}
