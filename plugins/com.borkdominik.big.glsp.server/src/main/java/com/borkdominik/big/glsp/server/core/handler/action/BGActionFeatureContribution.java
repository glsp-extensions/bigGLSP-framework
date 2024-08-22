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
package com.borkdominik.big.glsp.server.core.handler.action;

import java.util.function.Consumer;

import org.eclipse.glsp.server.actions.Action;
import org.eclipse.glsp.server.actions.ActionHandler;
import org.eclipse.glsp.server.di.ClientSessionModule;
import org.eclipse.glsp.server.features.contextactions.ContextActionsProvider;

import com.borkdominik.big.glsp.server.core.manifest.BGContributionManifest;
import com.borkdominik.big.glsp.server.core.manifest.BGRepresentationManifest;
import com.borkdominik.big.glsp.server.core.manifest.contribution.BGSetContributionModule;
import com.borkdominik.big.glsp.server.core.manifest.contribution.representation.BGRSetContributionModule;
import com.borkdominik.big.glsp.server.lib.utils.TypeLiteralUtils;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.name.Names;

import lombok.Builder;
import lombok.Getter;

public class BGActionFeatureContribution extends BGContributionManifest {

   @Getter
   @Builder
   public static class Options {
      protected BGRepresentationManifest manifest;
      protected Consumer<Multibinder<ActionHandler>> handlers;
      protected Consumer<Multibinder<ActionHandler>> overrideHandlers;
      protected Consumer<Multibinder<Action>> clientActions;
      protected Consumer<Multibinder<ContextActionsProvider>> contextActionsProviders;
   }

   protected final Options options;

   public BGActionFeatureContribution(final Options options) {
      this.options = options;
   }

   @Override
   protected void configure() {
      super.configure();

      install(new BGSetContributionModule<>(
         BGSetContributionModule.Options.<ActionHandler> BGSetContributionModuleBuilder()
            .contributionType(TypeLiteralUtils.of(ActionHandler.class))
            .consumer(this.options.handlers)
            .build()));

      install(new BGRSetContributionModule<>(
         BGRSetContributionModule.Options.<ActionHandler> BGRSetContributionModuleBuilder()
            .manifest(this.options.manifest)
            .contributionType(TypeLiteralUtils.of(ActionHandler.class))
            .consumer(this.options.overrideHandlers)
            .build()));

      install(new BGSetContributionModule<>(
         BGSetContributionModule.Options.<Action> BGSetContributionModuleBuilder()
            .contributionType(TypeLiteralUtils.of(Action.class))
            .consumer(this.options.clientActions)
            .bindingName(Names.named(ClientSessionModule.CLIENT_ACTIONS))
            .build()));

      install(new BGSetContributionModule<>(
         BGSetContributionModule.Options.<ContextActionsProvider> BGSetContributionModuleBuilder()
            .contributionType(TypeLiteralUtils.of(ContextActionsProvider.class))
            .consumer(this.options.contextActionsProviders)
            .build()));
   }
}
