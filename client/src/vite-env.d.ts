/// <reference types="vite/client" />

interface ImportMetaEnv {
  readonly VITE_SERVER_WEBSOCKET_ENDPOINT: string;
  readonly VITE_SERVER_CRASH_POINT_ROUTE: string;
}

interface ImportMeta {
  readonly env: ImportMetaEnv;
}
