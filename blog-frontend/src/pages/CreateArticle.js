import { useState,useEffect } from "react";
import api from "../api/api";
import { useNavigate } from "react-router-dom";
import "./CreateArticle.css";

function CreateArticle(){

    const [title,setTitle] = useState("");
    const [summary,setSummary] = useState("");
    const [content,setContent] = useState("");
    const [difficulty,setDifficulty] = useState("");
    const [articleType,setArticleType] = useState("");
    const [error,setError] = useState("");
    const [tags,setTags] = useState([]);
    const [tagIds,setTagIds] = useState([]);

    const navigate = useNavigate();

    useEffect(()=>{
        fetchTags();
    },[]);

    async function fetchTags(){
        try{
            const response = await api.get("/tag");
            setTags(response.data.content);
        }catch(error){
            console.log("Failed to load tags");
        }

    }

    async function handleSubmit(e){

        e.preventDefault();
        setError("");

        try{
            await api.post("/article",{
                title,
                summary,
                content,
                difficulty,
                articleType,
                tagIds
            });

            navigate("/");
        }catch(error){
            setError("Failed to Create Article");
        }
    }

    function handleTagChange(tagId){
        setTagIds(prev=>
            prev.includes(tagId)
                ? prev.filter(id => id !== 1)
                : [...prev,tagId]
        );
    }

    return(
          <div className="create-container">
                <div className="create-card">
                    <h2 className="create-title"> Create Article </h2>

                    {error && <p className="create-error"> {error} </p>}

                    <form onSubmit = {handleSubmit}>

                        <input
                            type = "text"
                            placeholder="Title"
                            value={title}
                            onChange={(e) => setTitle(e.target.value)}
                        />

                        <input
                            type="text"
                            placeholder="Summary"
                            value={summary}
                            onChange={(e) => setSummary(e.target.value)}
                       />

                       <textarea
                            placeholder="Content"
                            value={content}
                            onChange={(e)=>setContent(e.target.value)}
                            rows="6"
                       />

                       <input
                            type="text"
                            placeholder="Article Type"
                            value={articleType}
                            onChange={(e)=>setArticleType(e.target.value)}
                       />

                       <input
                            type="text"
                            placeholder="Difficulty"
                            value={difficulty}
                            onChange={(e)=>setDifficulty(e.target.value)}
                       />

                        <div className="tags-section">
                              <h4>Select Tags</h4>
                                  <div className="tags-grid">
                                    {tags.map(tag => (
                                          <div key={tag.id} className="tag-item">
                                            <input
                                              type="checkbox"
                                              value={tag.id}
                                              checked={tagIds.includes(tag.id)}
                                              onChange={() => handleTagChange(tag.id)}
                                            />
                                            <label>{tag.name}</label>
                                      </div>
                                ))}
                              </div>
                        </div>

                       <button type="submit"  className="create-button">Publish</button>

                    </form>

                </div>
          </div>
    )

}

export default CreateArticle;