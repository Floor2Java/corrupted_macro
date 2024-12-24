package com.github.floor2java.corrupted_macro.utils;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import net.minecraft.client.Minecraft;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ScoreboardUtils {

    public static String getScoreboardTitle() {

        Scoreboard scoreboard = Minecraft.getMinecraft().theWorld.getScoreboard();

        // Récupérer l'objectif principal affiché sur le scoreboard
        ScoreObjective objective = scoreboard.getObjectiveInDisplaySlot(1); // Slot 1 correspond au display slot principal

        // Vérifier si l'objectif existe
        if (objective != null) {
            // Retourner le titre de l'objectif
            return objective.getDisplayName();
        }
        return "Objective not found";
    }


    public static boolean scoreboardContains(String string) {
        for (String line : getScoreboard()) {
            if (StringUtils.removeFormatting(cleanSB(line)).contains(string)) {
                return true;
            }
        }
        return false;
    }

    public static boolean scoreboardContains(String string, List<String> scoreboard) {
        for (String line : scoreboard) {
            if (StringUtils.removeFormatting(cleanSB(line)).contains(string)) {
                return true;
            }
        }
        return false;
    }

    public static String cleanSB(String scoreboard) {
        char[] nvString = StringUtils.removeFormatting(scoreboard).toCharArray();
        StringBuilder cleaned = new StringBuilder();

        for (char c : nvString) {
            if ((int) c > 20 && (int) c < 127) {
                cleaned.append(c);
            }
        }

        return cleaned.toString();
    }

    @SuppressWarnings({"ExecutionException", "IllegalArgumentException"})
    public static List<String> getScoreboard() {
        List<String> lines = new ArrayList<>();
        if (Minecraft.getMinecraft().theWorld == null) return lines;
        Scoreboard scoreboard = Minecraft.getMinecraft().theWorld.getScoreboard();
        if (scoreboard == null) return lines;

        ScoreObjective objective = scoreboard.getObjectiveInDisplaySlot(1);
        if (objective == null) return lines;

        Collection<Score> scores = scoreboard.getSortedScores(objective);
        List<Score> list = scores.stream()
                .filter(input -> input != null && input.getPlayerName() != null && !input.getPlayerName()
                        .startsWith("#"))
                .collect(Collectors.toList());

        if (list.size() > 15) {
            scores = Lists.newArrayList(Iterables.skip(list, scores.size() - 15));
        } else {
            scores = list;
        }

        for (Score score : scores) {
            ScorePlayerTeam team = scoreboard.getPlayersTeam(score.getPlayerName());
            lines.add(ScorePlayerTeam.formatPlayerName(team, score.getPlayerName()));
        }

        return lines;
    }
}