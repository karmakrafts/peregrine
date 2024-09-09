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

package io.karma.peregrine.target;

import io.karma.peregrine.api.target.RenderTarget;
import net.minecraft.client.renderer.RenderStateShard.OutputStateShard;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * @author Alexander Hinze
 * @since 01/09/2024
 */
@OnlyIn(Dist.CLIENT)
public final class OutputStateRenderTarget implements RenderTarget {
    private final OutputStateShard state;

    public OutputStateRenderTarget(final OutputStateShard state) {
        this.state = state;
    }

    @Override
    public void bind() {
        state.setupRenderState();
    }

    @Override
    public void unbind() {
        state.clearRenderState();
    }

    @Override
    public OutputStateShard asStateShard() {
        return state;
    }

    @Override
    public int hashCode() {
        return state.hashCode();
    }

    @Override
    public String toString() {
        return String.format("OutputStateRenderTarget[state=%s]", state);
    }
}
