import { Client, messageCallbackType, StompConfig } from "@stomp/stompjs";

export const createStompClient = (messageCallback: messageCallbackType) => {
  const brokerURL = import.meta.env.VITE_SERVER_WEBSOCKET_ENDPOINT;
  const crashPointRoute = import.meta.env.VITE_SERVER_CRASH_POINT_ROUTE;

  const client = new Client({ brokerURL });
  client.onConnect = () => {
    client.subscribe(crashPointRoute, messageCallback);
  };

  return client;
};

export interface RoundMessage {
  multiplier: number;
  time: number;
}

export const defaultRoundMessage: RoundMessage = {
  multiplier: 1.0,
  time: 0,
};
