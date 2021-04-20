package inf112.skeleton.app.server.packets;

import java.io.Serializable;

public class ConfigureClient implements Serializable {
    public String nickname;

    public ConfigureClient(String nickname) {
        this.nickname = nickname;
    }
}
