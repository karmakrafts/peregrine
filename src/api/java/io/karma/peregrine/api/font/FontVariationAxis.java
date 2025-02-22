/*
 * Copyright 2024 Karma Krafts & associates
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package io.karma.peregrine.api.font;

/**
 * Describes a singular font variation axis in an
 * OpenType or TrueType font (family) along its bounds.
 *
 * @param name  the name of the variation axis as specified by the font.
 * @param min   the minimum value of this variation axis.
 * @param max   the maximum value of this variation axis.
 * @param value the value of this variation axis.
 * @author Alexander Hinze
 * @since 31/08/2024
 */
public record FontVariationAxis(String name, float min, float max, float value) {
}
