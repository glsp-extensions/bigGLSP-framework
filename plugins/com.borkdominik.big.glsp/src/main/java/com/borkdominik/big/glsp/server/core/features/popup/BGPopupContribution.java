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
package com.borkdominik.big.glsp.server.core.features.popup;

import org.eclipse.emf.ecore.EObject;

import com.borkdominik.big.glsp.server.core.manifest.BGContributionManifest;
import com.borkdominik.big.glsp.server.core.manifest.BGRepresentationManifest;
import com.borkdominik.big.glsp.server.core.manifest.contribution.representation.BGRSetContributionModule;
import com.borkdominik.big.glsp.server.lib.utils.TypeLiteralUtils;

import lombok.Builder;
import lombok.Getter;

public class BGPopupContribution extends BGContributionManifest {

   @Getter
   @Builder
   public static class Options {
      protected BGRepresentationManifest manifest;
      protected Class<? extends BGPopupMapper<? extends EObject>> concrete;
   }

   protected final Options options;

   public BGPopupContribution() {
      this(Options.builder().build());
   }

   public BGPopupContribution(final Options options) {
      this.options = options;
   }

   @Override
   protected void configure() {
      super.configure();

      install(new BGRSetContributionModule<>(
         BGRSetContributionModule.Options
            .<BGPopupMapper<? extends EObject>> BGRSetContributionModuleBuilder()
            .contributionType(TypeLiteralUtils.subtypeOf(BGPopupMapper.class, EObject.class))
            .consumer((contribution) -> contribution.addBinding().to(options.concrete))
            .build()));
   }
}
