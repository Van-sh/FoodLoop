import { lazy } from "react";
import { BrowserRouter, Navigate, Route, Routes } from "react-router";

import { ThemeProvider } from "@/context/theme/theme-provier";

const HomePage = lazy(() => import("@/pages/HomePage"));
const LoginPage = lazy(() => import("@/pages/LoginPage"));
const SignupPage = lazy(() => import("@/pages/SignupPage"));

function App() {
  return (
    <>
      <ThemeProvider>
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<HomePage />} />
            <Route path="/signup/">
              <Route index element={<Navigate to={"/"} />} />
              <Route path=":role" element={<SignupPage />} />
            </Route>
            <Route path="/login" element={<LoginPage />} />

            {/* Fallback */}
            <Route path="*" element={<Navigate to={"/"} />} />
          </Routes>
        </BrowserRouter>
      </ThemeProvider>
    </>
  );
}

export default App;
