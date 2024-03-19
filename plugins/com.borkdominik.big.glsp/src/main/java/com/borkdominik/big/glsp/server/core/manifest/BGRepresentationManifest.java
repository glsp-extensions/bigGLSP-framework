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
package com.borkdominik.big.glsp.server.core.manifest;

import org.eclipse.emf.common.util.Enumerator;

import com.google.inject.AbstractModule;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

public abstract class BGRepresentationManifest extends AbstractModule {

   public String id() {
      return representation().getName();
   }

   public Named idNamed() {
      return Names.named(id());
   }

   public abstract Enumerator representation();

   public Named representationNamed() {
      return Names.named(representation().getName());
   }
}
