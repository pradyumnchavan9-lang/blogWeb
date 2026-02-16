import { useState } from "react";
import api from "../api/api";
import { useParams } from "react-router-dom";
import "./AIChat.css";

function AIChat(){

    const {id} = useParams();
    const [messages,setMessages] = useState([]);
    const [input,setInput] = useState("");
    const [loading,setLoading] = useState(false);

    async function handleSend(){
        if(!input.trim()) return;

        const userMessage = {
            role: "user",
            content: input
        };

        setMessages(prev => [...prev,userMessage]);
        setInput("");
        setLoading(true);

        try{
            const response = await api.post(`/article/${id}/chat`,{
                question:input
            });

            const aiMessage = {
                role:"assistant",
                content:response.data.choices[0].message.content
            };

            setMessages(prev => [...prev,aiMessage]);
        }catch(error){
            setMessages(prev => [
                ...prev,
                {
                    role:"assistant",
                    content:"Something Went Wrong Try Again"
                }
            ]);
        }finally{
            setLoading(false);
        }
    }

    return(
        <div className = "ai-container">
            <h2> AI Assistant </h2>

            <div className = "chat-box">
                {messages.map((msg,idx)=>(
                    <div
                        key={idx}
                        className={`chat-message ${msg.role}`}
                    >
                        {msg.content}
                    </div>

                ))}

                {loading && (
                    <div className = "chat-message assistant">
                    Thinking...
                    </div>
                )}

                  <div className="chat-input">
                        <input
                          type="text"
                          placeholder="Ask anything..."
                          value={input}
                          onChange={(e) => setInput(e.target.value)}
                          onKeyDown={(e) => e.key === "Enter" && handleSend()}
                        />
                        <button onClick={handleSend}>Send</button>
                  </div>
            </div>
        </div>
    )
}

export default AIChat;