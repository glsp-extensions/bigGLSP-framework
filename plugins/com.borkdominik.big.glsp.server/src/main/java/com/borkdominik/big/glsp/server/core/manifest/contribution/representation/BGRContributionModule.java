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
package com.borkdominik.big.glsp.server.core.manifest.contribution.representation;

import org.eclipse.emf.common.util.Enumerator;

import com.borkdominik.big.glsp.server.core.manifest.BGRepresentationManifest;
import com.borkdominik.big.glsp.server.core.manifest.contribution.BGContributionModule;
import com.google.inject.multibindings.MapBinder;

import lombok.experimental.SuperBuilder;

public abstract class BGRContributionModule<TContribution, TBinding, TOptions extends BGRContributionModule.Options<TContribution>>
   extends
   BGContributionModule<TContribution, MapBinder<Enumerator, TBinding>, TOptions> {

   @SuperBuilder(builderMethodName = "BGRContributionModuleBuilder")
   public static class Options<TContribution> extends BGContributionModule.Options<TContribution> {
      public final BGRepresentationManifest manifest;
   }

   public BGRContributionModule(final TOptions options) {
      super(options);
   }

   @Override
   protected boolean isDefinition() { return options.manifest == null; }
}
