package net.yscxy.picture.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Author WangFuKun
 * @create 2020/10/24 8:22
 */
@Component
public class ServerConfig implements ApplicationListener<WebServerInitializedEvent> {
    private int serverPort;

    public String getUrl() {
        return "http://" + "39.102.66.229" + ":" + this.serverPort;
    }

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        this.serverPort = event.getWebServer().getPort();
    }

}