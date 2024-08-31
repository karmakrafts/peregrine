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

package io.karma.peregrine.font;

import it.unimi.dsi.fastutil.objects.Object2FloatMap;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

/**
 * Describes a font family and its configuration.
 * A font family is a font with one or more possible styles
 * and variations.
 * It also describes basic rendering properties of the font.
 *
 * @author Alexander Hinze
 * @since 30/08/2024
 */
public interface FontFamily {
    /**
     * Helper function for tricking static analysis.
     * This can be used to declare registry entries which
     * are populated at runtime, such as {@link net.minecraftforge.registries.ObjectHolder}.
     *
     * @return null.
     */
    @SuppressWarnings("all")
    @NotNull
    static FontFamily nullType() {
        return null;
    }

    /**
     * Retrieves the name of this font family.
     * This is without any file extensions, as it is used
     * as a template for multiple file names with different extensions.
     *
     * @return the name of this font family.
     */
    ResourceLocation getName();

    /**
     * Retrieves the display name of this font family.
     *
     * @return the display name of this font family.
     */
    String getDisplayName();

    /**
     * Retrieves a set of all possible styles supported
     * by this font family. This is specified by the font
     * family configuration.
     *
     * @return a set of all possible styles supported by this font family.
     */
    Set<FontStyle> getStyles();

    /**
     * Retrieves the size of the glyph sprites in the font
     * texture built for this font family in pixels.
     *
     * @return the size of the glyph sprites in the font
     * texture built for this font family in pixels.
     */
    int getGlyphSpriteSize();

    /**
     * Retrieves the size of the glyph sprite border in the font
     * texture built for this font family in pixels.
     *
     * @return the size of the glyph sprite border in the font
     * texture built for this font family in pixels.
     */
    int getGlyphSpriteBorder();

    /**
     * Retrieves the distance field range of this font family
     * used for generating the glyph sprites in pixels.
     *
     * @return the distance field range of this font family in pixels.
     */
    float getDistanceFieldRange();

    /**
     * Retrieves a variant of this font family with the given style and size.
     *
     * @param style The style which to retrieve a font variant for.
     * @param size  The size which to retrieve a font variant for in points.
     * @return a variant of this font family with the given style and size.
     */
    FontVariant getFont(final FontStyle style, final float size);

    /**
     * Retrieves a variant of this font family with the given style, size
     * and variation axes.
     *
     * @param style The style which to retrieve a font variant for.
     * @param size  The size which to retrieve a font variant for in points.
     * @return a variant of this font family with the given style, size
     * and variation axes.
     */
    FontVariant getFont(final FontStyle style, final float size, final Object2FloatMap<String> variationAxes);

    /**
     * Retrieves the default variant of this font family.
     *
     * @return the default variant of this font family.
     */
    default FontVariant getDefaultFont() {
        return getFont(FontStyle.REGULAR, FontVariant.DEFAULT_SIZE);
    }
}
