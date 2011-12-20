package com.mojang.ld22.crafting;

import com.mojang.ld22.entity.Player;
import com.mojang.ld22.item.ToolItem;
import com.mojang.ld22.item.ToolType;

public class ToolRecipe extends Recipe {
	private ToolType type;
	private int level;

	public ToolRecipe(ToolType type, int level) {
		super(new ToolItem(type, level));
		this.type = type;
		this.level = level;
	}

	public void checkCanCraft(Player player) {
		super.checkCanCraft(player);
		if (player.inventory.hasToolBetterThan((ToolItem) resultTemplate)) {
			canCraft = false;
		} else if (player.activeItem instanceof ToolItem) {
			ToolItem ti = (ToolItem) player.activeItem;
			if (ti.type == type && ti.level >= level) {
				canCraft = false;
			}
		}
	}

	public void craft(Player player) {
		if (player.activeItem instanceof ToolItem) {
			ToolItem ti = (ToolItem) player.activeItem;
			if (ti.type == type) {
				player.activeItem = new ToolItem(type, level);
				return;
			}
		}
		player.inventory.add(0, new ToolItem(type, level));
	}
}
