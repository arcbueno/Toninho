import com.jessecorbett.diskord.api.model.UserStatus
import com.jessecorbett.diskord.api.websocket.model.ActivityType
import com.jessecorbett.diskord.api.websocket.model.UserStatusActivity
import com.jessecorbett.diskord.dsl.bot
import com.jessecorbett.diskord.dsl.command
import com.jessecorbett.diskord.dsl.commands
import kotlin.random.Random

private val BOT_TOKEN =try {
    ClassLoader.getSystemResource("dataToken.txt").readText().trim()
} catch (error: Exception) {
    throw RuntimeException("Failed to load bot token. Make sure to create a file named bot-token.txt in" +
            " src/main/resources and paste the bot token into that file.", error)
}

const val PERMISSION_INTEGER = 2148005952

suspend fun main() {
    val links = Links()
    bot(BOT_TOKEN) {
        // . as default prefix
        var guildList = this.clientStore.guilds;
        started {
            setStatus(UserStatus.ONLINE, activity = UserStatusActivity("Ape Out", ActivityType.GAME));
        }
        commands {
            command("mamaco") {
                var mamacoList = links.linkMamaco
                reply(mamacoList[getRandomIndex(mamacoList.size)])
            }
            command("zap") {
                var zapList = links.linkZap
                reply(zapList[getRandomIndex(zapList.size)])
            }
            command("genshin") {
                var genshinList = links.linkGenshin
                reply(genshinList[getRandomIndex(genshinList.size)])
            }
            command("flip") {
                delete()
                reply(links.monkyFlip)
            }
            command("top") {
                delete()
                reply("TOPZERA IRMÃO :ok_hand: :ok_hand: :ok_hand: :ok_hand: :ok_hand: :rofl: :rofl:")
            }
            command("violencia") {
                delete()
                var guild = guildList.get(this.guildId!!);
                var guildMembers = guild.getMembers(1000);
                var currentIndex = getRandomIndex(guildMembers.size)
                reply("Vô dale em ${guildMembers[currentIndex].user!!.username}");
            }

            command("disputa") {
                delete()
                var guild = guildList.get(this.guildId!!);
                var guildMembers = guild.getMembers(1000);
                var currentIndex = getRandomIndex(guildMembers.size)
                reply("Bicho, quem ta certo é ${guildMembers[currentIndex].user!!.username}");
            }
            command("toninho") {
                reply("UHHHUHHH AAAA AAAHHHH UHUUHU UGA UHHHHH (gritos de mamaco)");
            }
        }
    }
}

fun getRandomIndex(maxLength: Int): Int {
    return Random.nextInt(0, maxLength);
}