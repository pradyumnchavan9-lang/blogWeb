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

  return(
             <div>
               <h1>Home</h1>
                    {isLoggedIn ? (
                          <>
                                <button onClick={logout}>Logout</button>{" "}
                                <Link to="/profile">
                                  <button>Profile</button>
                                </Link>
                              </>
                            ) : (
                              <>
                                <a href="/login">Login</a> | <a href="/register">Register</a>
                          </>
                    )}

                {isLoggedIn && (
                  <>
                    <br /><br />
                    <a href="/articles/new">Create Article</a>
                  </>
                )}

               <hr />

               <div className = "container">
               <h2>Articles</h2>

               <ul>
                    {articles.map(article => (
                        <li className = "article-card" key = {article.id}>
                                <h3>
                                    <a href = {`/articles/${article.id}`}>{article.title}
                                    </a>
                                </h3>
                                <p className = "article-meta">
                                    Author Name:-{article.author.username}<br></br>
                                    Difficulty • {article.difficulty}
                                </p>
                                <p>{article.summary}</p>
                        </li>
                    ))}

               </ul>
                </div>
             </div>
         );
}

export default Home;
