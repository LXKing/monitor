package mmp.gps.monitor.websocket;

import mmp.gps.common.util.JsonMapper;
import mmp.gps.common.util.MessageKinds;
import mmp.gps.domain.locate.GodpDataBlock;
import mmp.gps.domain.security.IdentityDto;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class SystemWebSocketHandler implements WebSocketHandler {
    // private static final Logger logger = LoggerFactory.getLogger(SystemWebSocketHandler.class);
    private static final Map<String, List<WebSocketSession>> userSessions = new ConcurrentHashMap<String, List<WebSocketSession>>();
    private static String serverTimestamp = UUID.randomUUID().toString();

    /**
     * 用户是否在线
     */
    public static boolean isOnline(String userId) {
        List<WebSocketSession> list = userSessions.get(userId);
        return list == null ? false : list.size() > 0;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // IdentityDto identity = (IdentityDto) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        IdentityDto identity = (IdentityDto) session.getAttributes().get("user");
        String userId = identity.getId();
        // logger.debug(identity.getAccount() + ":" + session.getRemoteAddress().toString() + " connect to the " +
        //         "websocket" + " success......");
        List<WebSocketSession> sessions = userSessions.get(userId);
        if (sessions == null) {
            sessions = new ArrayList<>();
            userSessions.put(userId, sessions);
        }
        sessions.add(session);

        List<GodpDataBlock> blocks = new ArrayList<GodpDataBlock>();
        GodpDataBlock block = new GodpDataBlock();
        block.setKind(MessageKinds.server_timestamp);
        block.setData(serverTimestamp);
        blocks.add(block);

        String json = JsonMapper.toJson(blocks);
        session.sendMessage(new TextMessage(json));
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        IdentityDto identity = (IdentityDto) session.getAttributes().get("user");
        // logger.debug(identity.getAccount() + ":" + session.getRemoteAddress().toString() + message.toString());
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        IdentityDto identity = (IdentityDto) session.getAttributes().get("user");
        String userId = identity.getId();
        if (session.isOpen()) {
            session.close();
        }
        // logger.debug(identity.getAccount() + ":" + session.getRemoteAddress().toString() + " websocket connection " +
        //         "closed......");

        List<WebSocketSession> sessions = userSessions.get(userId);
        sessions.remove(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        IdentityDto identity = (IdentityDto) session.getAttributes().get("user");
        String userId = identity.getId();
        // logger.debug(identity.getAccount() + ":" + session.getRemoteAddress().toString() + " websocket connection " +
        //         "closed......");
        List<WebSocketSession> sessions = userSessions.get(userId);
        sessions.remove(session);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 给所有在线用户发送消息
     *
     * @param message
     */
    public void sendMessageToUsers(TextMessage message) {
        for (Map.Entry<String, List<WebSocketSession>> entry : userSessions.entrySet()) {
            for (WebSocketSession session : entry.getValue()) {
                try {
                    if (session.isOpen()) {
                        session.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 给某个用户发送消息
     *
     * @param userId
     * @param message
     */
    public void sendMessageToUser(String userId, TextMessage message) {

        List<WebSocketSession> sessions = userSessions.get(userId);
        if (sessions == null)
            return;
        for (WebSocketSession session : sessions) {
            try {
                if (session.isOpen()) {
                    session.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessageToUser(Map<String, List<GodpDataBlock>> map) {
        // 下发数据
        if (map.size() > 0) {
            SystemWebSocketHandler handler = new SystemWebSocketHandler();
            for (Map.Entry<String, List<GodpDataBlock>> entry : map.entrySet()) {
                String json = JsonMapper.toJson(entry.getValue());
                handler.sendMessageToUser(entry.getKey(), new TextMessage(json));
            }
        }
    }
}
