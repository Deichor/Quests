/*
 * Copyright (c) PikaMug and contributors
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
 * LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package me.pikamug.quests.tasks;

import com.github.Anon8281.universalScheduler.UniversalRunnable;
import me.pikamug.quests.quests.Quest;
import me.pikamug.quests.player.Quester;
import me.pikamug.quests.util.BukkitLang;
import me.pikamug.quests.util.BukkitMiscUtil;
import org.bukkit.ChatColor;

public class BukkitActionTimer extends UniversalRunnable {

    private final Quester quester;
    private final Quest quest;
    private final int time;

    public BukkitActionTimer(final Quester quester, final Quest quest, final int time) {
        this.quester = quester;
        this.quest = quest;
        this.time = time;
    }

    @Override
    public void run() {
        if (time < 1) {
            quest.failQuest(quester, false);
            quester.updateJournal();
        } else if (quester.getOfflinePlayer().isOnline()){
            quester.getPlayer().sendMessage(ChatColor.GREEN + BukkitLang.get(quester.getPlayer(), "timerMessage")
                    .replace("<time>", BukkitMiscUtil.getTime(time * 1000L)).replace("<quest>", quest.getName()));
        }
    }

}
