/*
 * Copyright (C) 2022 NotEnoughUpdates contributors
 *
 * This file is part of NotEnoughUpdates.
 *
 * NotEnoughUpdates is free software: you can redistribute it
 * and/or modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation, either
 * version 3 of the License, or (at your option) any later version.
 *
 * NotEnoughUpdates is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with NotEnoughUpdates. If not, see <https://www.gnu.org/licenses/>.
 */

package io.github.moulberry.notenoughupdates.profileviewer.weight.senither;

import io.github.moulberry.notenoughupdates.profileviewer.ProfileViewer;
import io.github.moulberry.notenoughupdates.profileviewer.weight.weight.Weight;
import java.util.Map;

public class SenitherWeight extends Weight {

	public SenitherWeight(Map<String, ProfileViewer.Level> player) {
		super(new SenitherSlayerWeight(player), new SenitherSkillsWeight(player), new SenitherDungeonsWeight(player));
	}

	@Override
	public void calculateWeight() {
		slayerWeight.getWeightStruct().reset();
		skillsWeight.getWeightStruct().reset();
		dungeonsWeight.getWeightStruct().reset();

		for (String slayerName : SLAYER_NAMES) {
			slayerWeight.getSlayerWeight(slayerName);
		}

		for (String skillName : SKILL_NAMES) {
			skillsWeight.getSkillsWeight(skillName);
		}

		dungeonsWeight.getDungeonWeight();
		for (String dungeonClassName : DUNGEON_CLASS_NAMES) {
			((SenitherDungeonsWeight) dungeonsWeight).getClassWeight(dungeonClassName);
		}
	}
}
