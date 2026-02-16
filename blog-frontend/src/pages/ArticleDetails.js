import { useEffect,useState } from "react";
import { useParams,useNavigate } from "react-router-dom";
import api from "../api/api";
import "./ArticleDetails.css";

function ArticleDetails(){

    const {id} = useParams();
    const [article,setArticle] = useState(null);
    const [comment,setComment] = useState([]);
    const [currentUser,setCurrentUser] = useState({});
    const navigate = useNavigate();


    useEffect(()=>{
        fetchArticle();
        fetchCurrentUser();
    },[id]);

    // Fetching Articles
    async function fetchArticle(){
        const response = await api.get(`/article/get-article/${id}`);
        setArticle(response.data);
        console.log("Fetched comments:", response.data.comments);
    }


    //Fetch Logged-In user
    async function fetchCurrentUser(){
        try{
            const response = await api.get("/user/me");
            setCurrentUser(response.data);
        }catch(error){
            console.log("Failed to load user");
        }
    }

    //Handling Comment Submit
async function handleCommentSubmit(e){
    e.preventDefault();
    try {
        const response = await api.post(`/article/${id}/comments`, {
            content: comment
        });

        // Optimistically update state so new comment appears instantly
        setArticle(prev => ({
            ...prev,
            comments: [...prev.comments, response.data]
        }));

        setComment("");
    } catch(error) {
        console.log(error);
    }
}

    //Handling Comment Delete
async function handlingDeleteComment(commentId){
    try{
        await api.delete(`/article/${id}/comments/${commentId}`);

        // Remove deleted comment immediately from state
        setArticle(prev => ({
            ...prev,
            comments: prev.comments.filter(c => c.id !== commentId)
        }));

    }catch(error){
        console.log(error);
    }
}

    //Adding the AI chat
    function openAIChat(){
        navigate(`/ai-chat/${id}`);
    }

    if(!article) return <p className = "loading-text"> Loading... </p>;

    console.log("Current User:", currentUser);
    console.log("Comments:", article.comments);

    return(
            <div className="article-container">
                <div className="article-card">
                    <h2 className="article-title"> Article </h2>

                <div className="article-summary">
                    <b>Summary</b>
                    <p>{article.summary}</p>
                </div>

                <div className="article-content">
                    <p>{article.content}</p>
                </div>

                <div className="article-actions">
                    <button
                        className="ai-chat-button"
                        onClick={openAIChat}
                    >
                        💬 Ask AI about this article
                    </button>
                </div>

                    <h3 className="comments-title">Comments</h3>

                <div className="comments-section">
                    {
                        article?.comments?.map(c=>(
                            <div key={c.id} className="comment-card">
                                <span className="comment-author">
                                    {c.author.username}
                                </span>
                                <span className="comment-text">
                                    {c.content}
                                </span>
                                { currentUser.id === c.author.id || currentUser.role === "USER" ?
                                    (<button
                                        className = "comment-delete"
                                        onClick = {() => handlingDeleteComment(c.id)}
                                    >
                                    Delete
                                    </button>
                                    ) : null
                                }

                            </div>
                        ))
                    }
                </div>

                <hr className="article-divider" />

                      <form onSubmit={handleCommentSubmit} className = "comment-form">
                            <input
                              type="text"
                              placeholder="Add comment"
                              value={comment}
                              onChange={(e) => setComment(e.target.value)}
                            />
                            <button type="submit" className="comment-button">Post</button>
                      </form>

                </div>
            </div>
    )
}

export default ArticleDetails;