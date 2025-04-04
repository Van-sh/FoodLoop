import { lazy } from "react";
import { BrowserRouter, Route, Routes } from "react-router";
import { ThemeProvider } from "@/context/theme/theme-provier";

const HomePage = lazy(() => import("@/pages/HomePage"));

function App() {
  return (
    <>
      <ThemeProvider>
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<HomePage />} />
          </Routes>
        </BrowserRouter>
      </ThemeProvider>
    </>
  );
}

export default App;
