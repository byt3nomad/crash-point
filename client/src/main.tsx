import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import { BrowserRouter, Route, Routes } from "react-router";
import Home from "./pages/Home";
import { Provider } from "./components/ui/provider.tsx";
import Header from "./components/header.tsx";

createRoot(document.getElementById("root")!).render(
  <StrictMode>
    <Provider defaultTheme="dark">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Header />}>
            <Route index element={<Home />} />
          </Route>
        </Routes>
      </BrowserRouter>
    </Provider>
  </StrictMode>
);
