import { useEffect, useState } from "react";
import { createStompClient, defaultRoundMessage, RoundMessage } from "./stomp";
import { IMessage, messageCallbackType } from "@stomp/stompjs";
import { Link } from "react-router";

const Home = () => {
  const [multi, setMulti] = useState<RoundMessage>(defaultRoundMessage);
  const messageCallback: messageCallbackType = (message: IMessage) => {
    const body: RoundMessage = JSON.parse(message.body);
    setMulti(body);
  };

  useEffect(() => {
    const client = createStompClient(messageCallback);
    client.activate();

    return () => {
      client.deactivate();
    };
  }, []);

  return (
    <main>
      {multi.multiplier}
      <br />
      {multi.time}
      <br />
      <Link to={"/sami"}>Sami</Link>
    </main>
  );
};

export default Home;
