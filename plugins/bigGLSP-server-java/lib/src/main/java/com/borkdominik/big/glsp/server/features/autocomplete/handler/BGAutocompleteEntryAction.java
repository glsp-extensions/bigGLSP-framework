/********************************************************************************
 * Copyright (c) 2023 borkdominik and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0, or the MIT License which is
 * available at https://opensource.org/licenses/MIT.
 *
 * SPDX-License-Identifier: EPL-2.0 OR MIT
 ********************************************************************************/
package com.borkdominik.big.glsp.server.features.autocomplete.handler;

import java.util.List;

import org.eclipse.glsp.server.actions.Action;
import org.eclipse.glsp.server.features.directediting.LabeledAction;

public class BGAutocompleteEntryAction extends LabeledAction {

   protected String hint;

   public BGAutocompleteEntryAction(final String label, final List<Action> actions) {
      super(label, actions);
   }

   public BGAutocompleteEntryAction(final String label, final List<Action> actions, final String hint) {
      super(label, actions);
      this.hint = hint;
   }

   public String getHint() { return hint; }

   public void setHint(final String hint) { this.hint = hint; }

}
