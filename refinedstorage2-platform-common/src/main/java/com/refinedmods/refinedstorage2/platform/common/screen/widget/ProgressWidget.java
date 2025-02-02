package com.refinedmods.refinedstorage2.platform.common.screen.widget;

import com.refinedmods.refinedstorage2.platform.common.screen.TooltipRenderer;

import java.util.List;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import static com.refinedmods.refinedstorage2.platform.common.util.IdentifierUtil.createIdentifier;

public class ProgressWidget extends AbstractWidget {
    private static final ResourceLocation TEXTURE = createIdentifier("textures/gui/widgets.png");

    private final DoubleSupplier progressSupplier;
    private final TooltipRenderer tooltipRenderer;
    private final Supplier<List<Component>> tooltipSupplier;

    public ProgressWidget(final int x,
                          final int y,
                          final int width,
                          final int height,
                          final DoubleSupplier progressSupplier,
                          final TooltipRenderer tooltipRenderer,
                          final Supplier<List<Component>> tooltipSupplier) {
        super(x, y, width, height, Component.empty());
        this.progressSupplier = progressSupplier;
        this.tooltipRenderer = tooltipRenderer;
        this.tooltipSupplier = tooltipSupplier;
    }

    @Override
    public void renderWidget(final PoseStack poseStack, final int mouseX, final int mouseY, final float partialTicks) {
        final int correctedHeight = (int) (progressSupplier.getAsDouble() * height);

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);

        RenderSystem.enableDepthTest();
        blit(poseStack, getX(), getY() + height - correctedHeight, 179, height - correctedHeight, width,
            correctedHeight);
        RenderSystem.disableDepthTest();

        if (isHovered) {
            tooltipRenderer.render(poseStack, tooltipSupplier.get(), mouseX, mouseY);
        }
    }

    @Override
    protected void updateWidgetNarration(final NarrationElementOutput narrationElementOutput) {
        // intentionally empty
    }
}
