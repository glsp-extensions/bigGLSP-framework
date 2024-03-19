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

import java.util.Set;

import com.borkdominik.big.glsp.server.core.di.BGRFactory;
import com.borkdominik.big.glsp.server.core.di.DIProvider;
import com.borkdominik.big.glsp.server.core.manifest.contribution.representation.BGRSetContributionModule;
import com.borkdominik.big.glsp.server.core.model.BGTypeProvider;
import com.borkdominik.big.glsp.server.lib.utils.TypeLiteralUtils;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;

import lombok.experimental.SuperBuilder;

public class BGRFactoryMapContributionModule<TContribution, TConcrete extends TContribution, TOptions extends BGRFactoryMapContributionModule.Options<TContribution, TConcrete>>
   extends BGRSetContributionModule<TContribution, TOptions> {

   protected TypeLiteral<TConcrete> concreteType;
   protected TypeLiteral<BGRFactory<TConcrete>> factoryType;

   @SuperBuilder(builderMethodName = "BGRFactoryMapContributionModuleBuilder")
   public static class Options<TContribution, TConcrete> extends BGRSetContributionModule.Options<TContribution> {
      public Set<BGTypeProvider> elementTypes;
      public Class<TConcrete> concrete;
   }

   public BGRFactoryMapContributionModule(final TOptions options) {
      super(options);
      options.consumer = this::consume;
   }

   @Override
   protected boolean isDefinition() { return super.isDefinition() || options.concrete == null; }

   @Override
   protected void onBeforeContribute() {
      super.onBeforeContribute();

      this.concreteType = TypeLiteral.get(options.concrete);
      this.configureFactory();
   }

   protected void configureFactory() {
      this.factoryType = TypeLiteralUtils.parameterizedType(BGRFactory.class,
         this.concreteType);

      this.install(new BGRFactoryContributionModule<>(this.factoryType));
   }

   protected void consume(final Multibinder<TContribution> contribution) {
      var provider = DIProvider.byLiteral(this.factoryType,
         (fa) -> fa.create(options.manifest.representation()));

      contribution.addBinding()
         .toProvider(provider);
   }
}
