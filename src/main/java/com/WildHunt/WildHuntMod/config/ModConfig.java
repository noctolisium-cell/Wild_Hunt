package com.WildHunt.WildHuntMod.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;

@Config(name = "wildhunt")
public class ModConfig implements ConfigData {
    // ----- Боссы -----
    public double eredinHealth = 3000.0;
    public double imlerithHealth = 2500.0;
    public double caranthirHealth = 2800.0;
    public int bossSpecialAttackCooldown = 600; // тиков (30 секунд)

    // ----- Метель -----
    public int blizzardCooldownTicks = 30 * 24000; // 30 игровых дней
    public int blizzardDurationSeconds = 30;
    public int blizzardEffectRadius = 50;
    public float blizzardFreezeDamage = 2.0f;

    // ----- Оружие – базовая механика -----
    public double eredinSwordDamage = 14.0;   // итоговый урон меча (база меча 3 + бонус 11)
    public double imlerithAxeDamage = 16.0;   // итоговый урон топора
    public double caranthirStaffDamage = 12.0; // урон от снаряда посоха

    // ----- Ледяная погибель (меч Эредина) -----
    public int maxFrostStacks = 8;
    public double armorReductionPerStack = 0.5;      // уменьшение брони за стак (единиц)
    public int freezeDurationTicksBase = 40;         // базовая длительность заморозки (тики)
    public int freezeDurationTicksPerStack = 10;     // доп. длительность за стак
    public double coldDamageBase = 2.0;              // базовый урон от холода при ударе
    public double coldDamagePerStack = 0.5;          // доп. урон за стак
    public int stackResetDelayTicks = 100;           // через сколько тиков без удара стаки сбрасываются (5 сек)

    // ----- Сокрушающий удар (топор Имлериха) -----
    public double crushingArmorReduction = 4.0;      // временное снижение брони (единиц)
    public int crushingStunDuration = 40;            // длительность оглушения (тики, 2 сек)
    public double crushingExplosionDamage = 8.0;     // урон от ударной волны (AoE)

    // ----- Магия холода (посох Карантира) -----
    public int frozenProjectilesCount = 3;           // количество снарядов за выстрел
    public double frozenProjectileDamage = 8.0;      // урон одного снаряда
    public int frozenProjectileSlowDuration = 40;    // замедление от снаряда (тики)
    public int staffCooldownTicks = 40;              // перезарядка посоха (тики, 2 сек)

    // Инициализация и доступ
    public static void init() {
        AutoConfig.register(ModConfig.class, Toml4jConfigSerializer::new);
    }

    public static ModConfig get() {
        return AutoConfig.getConfigHolder(ModConfig.class).getConfig();
    }
}