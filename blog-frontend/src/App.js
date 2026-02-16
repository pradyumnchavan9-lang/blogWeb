import { Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import Login from "./pages/Login";
import Register from "./pages/Register";
import Navbar from "./components/Navbar";
import CreateArticle from "./pages/CreateArticle.js";
import ArticleDetails from "./pages/ArticleDetails.js";
import AuthGuard from "./pages/AuthGuard";
import AIChat from "./pages/AIChat.js";
import MyProfile from "./pages/MyProfile.js";

function App() {
  return (
  <>
    <Navbar />

    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/login" element={<Login />} />
      <Route path="/register" element={<Register />} />
      <Route path="/articles/new" element={<AuthGuard><CreateArticle /></AuthGuard>} />
      <Route path="/articles/:id" element={<ArticleDetails />} />
      <Route path="/ai-chat/:id" element={<AIChat />} />
      <Route path="/profile" element={<MyProfile />} />
    </Routes>

  </>
  );
}

export default App;
