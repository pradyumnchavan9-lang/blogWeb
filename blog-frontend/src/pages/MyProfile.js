import { useState,useEffect } from "react";
import api from "../api/api";
import { Link } from "react-router-dom";
import "./MyProfile.css";

function MyProfile(){

    const [user,setUser] = useState({});
    const [articles,setArticles] = useState([]);
    const [loading,setLoading] = useState(true);

    useEffect(()=>{
        fetchUserAndArticles();
    },[]);

    async function fetchUserAndArticles(){
          setLoading(true);
          try {
                const userRes = await api.get("/user/me");
                setUser(userRes.data);


                const articlesRes = await api.get(`/article/${userRes.data.id}`);
                setArticles(articlesRes.data.content);
          } catch (err) {
                console.log(err);
          } finally {
                setLoading(false);
          }
    }

    return (

        <div className = "container">
            <h2 className = "username"> {user.username} </h2>
            {loading && (<p key="loading" className="Loading"> Loading... </p>)}
            {!loading && articles.length === 0 && <p>No articles yet.</p>}
            <div className = "user-articles">
                <ul>
                    {articles.map(article=>(
                        <li className = "article-card" key ={article.id}>
                        <h3>
                        <Link to={`/articles/${article.id}`}>{article.title}</Link>
                        </h3>
                        <div className = "article-meta">
                            Difficulty • {article.difficulty}
                            <p className = "article-summary">{article.summary}</p>
                        </div>
                        </li>
                    ))}
                </ul>
            </div>
        </div>

    )

}

export default MyProfile;