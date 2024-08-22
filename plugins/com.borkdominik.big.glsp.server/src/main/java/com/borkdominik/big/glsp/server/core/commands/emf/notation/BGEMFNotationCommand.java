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
package com.borkdominik.big.glsp.server.core.commands.emf.notation;

import com.borkdominik.big.glsp.server.core.commands.BGRecordingCommand;
import com.borkdominik.big.glsp.server.core.commands.emf.BGEMFCommandContext;

public abstract class BGEMFNotationCommand extends BGRecordingCommand {

   protected BGEMFCommandContext context;

   public BGEMFNotationCommand(final BGEMFCommandContext context) {
      super(context.modelState().getNotationModel());
      this.context = context;
   }
}