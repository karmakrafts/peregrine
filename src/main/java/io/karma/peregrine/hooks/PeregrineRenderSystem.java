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

package io.karma.peregrine.hooks;

import com.mojang.blaze3d.systems.RenderSystem;
import io.karma.peregrine.api.Peregrine;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.Lazy;

/**
 * @author Alexander Hinze
 * @since 29/08/2024
 */
@OnlyIn(Dist.CLIENT)
public interface PeregrineRenderSystem {
    @SuppressWarnings("all")
    Lazy<PeregrineRenderSystem> INSTANCE = Lazy.of(() -> {
        try {
            final var field = RenderSystem.class.getDeclaredField("peregrine$instance");
            field.setAccessible(true);
            final var instance = (PeregrineRenderSystem) field.get(null);
            field.setAccessible(false);
            return instance;
        }
        catch (Throwable error) {
            Peregrine.LOGGER.error("Could not retrieve render system instance", error);
            throw new IllegalStateException(error);
        }
    });

    static PeregrineRenderSystem getInstance() {
        return INSTANCE.get();
    }

    PeregrineShader peregrine$getShader();

    void peregrine$setShader(final PeregrineShader shader);
}
