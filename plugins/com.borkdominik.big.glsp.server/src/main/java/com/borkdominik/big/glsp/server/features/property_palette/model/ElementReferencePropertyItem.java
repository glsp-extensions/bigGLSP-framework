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
package com.borkdominik.big.glsp.server.features.property_palette.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.glsp.server.actions.Action;

import lombok.Builder;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public final class ElementReferencePropertyItem extends ElementPropertyItem {

   public String label;
   @Builder.Default
   public List<Reference> references = new ArrayList<>();
   @Builder.Default
   public List<CreateReference> creates = new ArrayList<>();
   public Boolean isOrderable;
   public Boolean isAutocomplete;

   {
      type = ElementPropertyType.REFERENCE.name();
   }

   @Builder
   public static class Reference {
      public String elementId;
      public String label;
      public String name;
      public String hint;

      @Builder.Default
      public List<Action> deleteActions = new ArrayList<>();
   }

   @Builder
   public static class CreateReference {
      public String label;
      public Action action;
   }
}
