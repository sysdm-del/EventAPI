import me.sysdm.net.eventapi.EventAPI;
import me.sysdm.net.eventapi.events.Events;
import org.bukkit.event.player.PlayerJoinEvent;

public class Test {

    public void onJoin() {
        Events.listen(PlayerJoinEvent.class)
                .filter(e -> e.getPlayer().hasPlayedBefore())
                .handler(e -> {
                    e.setJoinMessage("Welcome back" + e.getPlayer().getName() + "!");
                });
    }
}
