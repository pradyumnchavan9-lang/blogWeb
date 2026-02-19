import { useEffect,useState } from "react";
import api from "../api/api";
import "./Home.css";
import { Link } from "react-router-dom";



function Home() {

    const [articles,setArticles] = useState([]);

    useEffect(() => {
        fetchArticles();
    },[]);

    async function fetchArticles(){

        try{
            const response = await api.get("/article");
            setArticles(response.data.content);
        }catch(error){
            console.error("Failed To Load Articles");
        }
    }

    function logout(){
        localStorage.removeItem("token");
        window.location.reload();
    }

    const isLoggedIn = !!localStorage.getItem("token");

  return (
    <div className="home-wrapper">

      <nav className="navbar">
        <h1 className="logo">DevArticles</h1>

        <div className="nav-actions">
          {isLoggedIn ? (
            <>
              <Link to="/articles/new" className="nav-link">Create</Link>
              <Link to="/profile" className="nav-link">Profile</Link>
              <button onClick={logout} className="logout-btn">Logout</button>
            </>
          ) : (
            <>
              <Link to="/login" className="nav-link">Login</Link>
              <Link to="/register" className="nav-link primary">Register</Link>
            </>
          )}
        </div>
      </nav>

      <div className="container">
        <h2 className="section-title">Latest Articles</h2>

        <ul className="articles-list">
          {articles.map(article => (
            <li className="article-card" key={article.id}>
              <Link to={`/articles/${article.id}`} className="article-title">
                {article.title}
              </Link>

              <p className="article-meta">
                By <strong>{article.author.username}</strong> • {article.difficulty}
              </p>

              <p className="article-summary">
                {article.summary}
              </p>
            </li>
          ))}
        </ul>
      </div>

    </div>
  );

}

export default Home;
