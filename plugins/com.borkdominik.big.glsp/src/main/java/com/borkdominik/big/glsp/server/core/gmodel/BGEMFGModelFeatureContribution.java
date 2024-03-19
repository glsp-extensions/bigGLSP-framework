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
package com.borkdominik.big.glsp.server.core.gmodel;

import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.glsp.graph.GModelElement;

import com.borkdominik.big.glsp.server.core.manifest.BGContributionManifest;
import com.borkdominik.big.glsp.server.core.manifest.BGRepresentationManifest;
import com.borkdominik.big.glsp.server.core.manifest.contribution.factory.BGRElementFactorySetContributionModule;
import com.borkdominik.big.glsp.server.core.model.BGTypeProvider;
import com.borkdominik.big.glsp.server.lib.utils.TypeLiteralUtils;
import com.google.inject.TypeLiteral;

import lombok.Builder;
import lombok.Getter;

public class BGEMFGModelFeatureContribution extends BGContributionManifest {

   @Getter
   @Builder
   public static class Options {
      protected BGRepresentationManifest manifest;
      protected Set<BGTypeProvider> elementTypes;
      protected Class<? extends BGEMFGModelMapper<? extends EObject, ? extends GModelElement>> concrete;
   }

   protected final Options options;

   public BGEMFGModelFeatureContribution(final Options options) {
      this.options = options;
   }

   @Override
   protected void configure() {
      super.configure();

      install(new BGRElementFactorySetContributionModule<>(
         BGRElementFactorySetContributionModule.Options
            .<BGEMFGModelMapper<? extends EObject, ? extends GModelElement>> BGRElementFactorySetContributionModuleBuilder()
            .manifest(this.options.manifest)
            .elementTypes(this.options.elementTypes)
            .contributionType(new TypeLiteral<BGEMFGModelMapper<? extends EObject, ? extends GModelElement>>() {})
            .concreteTypes(Set.of(TypeLiteralUtils.of(options.concrete)))
            .build()));
   }
}
