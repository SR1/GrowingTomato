package com.sr1.growingtomato.module;

import java.util.ArrayList;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.sr1.growingtomato.entity.Reward;

public class RewardModule {

	public static final String tag = "RewardModule";

	Context context;
	String key = "reward";

	public RewardModule(Context context) {
		this.context = context;
	}

 	public ArrayList<Reward> getRewardList() {
		SharedPreferences RewardSetting = context.getSharedPreferences(
				"RewardSetting", Context.MODE_PRIVATE);
		ArrayList<Reward> rewards = new ArrayList<Reward>();
		Reward reward;
		for (int i = 0; i < 6; i++) {
			String data = RewardSetting.getString(key + i, "none");
			reward = new Reward();
			reward.setPosition(i);
			if ("none".equals(data)) {
				reward.setChance((double) (i + 1));
				reward.setName("让自己额外休息" + (i+1) * 5 + "分钟吧~");
			} else {
				reward.setName(data.split(":|:|:")[0]);
				reward.setChance(Double.valueOf(data.split(":|:|:")[1]));
			}
			rewards.add(reward);
		}
		return rewards;
	}

	public void setReward(int position, String name, double chance) {
		SharedPreferences RewardSetting = context.getSharedPreferences(
				"RewardSetting", Context.MODE_PRIVATE);

		Editor editor = RewardSetting.edit();
		editor.putString(key + position,
				String.format("%s:|:|:%d", name, chance));
		editor.commit();
	}

}
