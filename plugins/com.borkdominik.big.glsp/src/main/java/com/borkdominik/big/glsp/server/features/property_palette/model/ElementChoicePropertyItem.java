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

import lombok.Builder;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public final class ElementChoicePropertyItem extends ElementPropertyItem {

   public String label;
   @Builder.Default
   public List<ElementChoicePropertyItem.Choice> choices = new ArrayList<>();
   public String choice;

   {
      type = ElementPropertyType.CHOICE.name();
   }

   @Builder
   public static class Choice {
      public String label;
      public String value;
      public String secondaryText;
   }
}
