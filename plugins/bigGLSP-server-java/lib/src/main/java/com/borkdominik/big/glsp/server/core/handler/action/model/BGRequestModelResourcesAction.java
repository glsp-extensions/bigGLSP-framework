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
package com.borkdominik.big.glsp.server.core.handler.action.model;

import org.eclipse.glsp.server.actions.RequestAction;

public class BGRequestModelResourcesAction extends RequestAction<BGModelResourcesResponseAction> {
   public static final String KIND = "requestModelResources";

   public BGRequestModelResourcesAction() {
      super(KIND);
   }

}
