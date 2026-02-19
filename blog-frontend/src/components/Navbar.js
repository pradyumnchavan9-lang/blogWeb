import { Link } from "react-router-dom";
import "./Navbar.css";
import { useState, useEffect } from "react";
import api from "../api/api";

function Navbar() {
    const [search, setSearch] = useState("");
    const [articles, setArticles] = useState([]);
    const [showDropdown, setShowDropdown] = useState(false);

    useEffect(() => {
        const delayDebounce = setTimeout(async () => {
            if (!search.trim()) {
                setArticles([]);
                setShowDropdown(false);
                return;
            }

            try {
                const response = await api.post("/article/search", {
                    query: search
                });

                setArticles(response.data.content);
                setShowDropdown(true);
            } catch (error) {
                console.error("Search error:", error);
            }
        }, 300); // debounce delay

        return () => clearTimeout(delayDebounce);
    }, [search]);

    return (
        <nav className="navbar">
            <h2 className="logo">BlogWeb</h2>

            <div className="search-container">
                <input
                    type="text"
                    value={search}
                    placeholder="Search Article..."
                    onChange={(e) => setSearch(e.target.value)}
                    onFocus={() => search && setShowDropdown(true)}
                />

                {showDropdown && articles.length > 0 && (
                    <div className="search-dropdown">
                        {articles.map((article) => (
                            <Link
                                key={article.id}
                                to={`/articles/${article.id}`}
                                onClick={() => {
                                    setShowDropdown(false);
                                    setSearch("");
                                }}
                                className="dropdown-item"
                            >
                                {article.title}
                            </Link>
                        ))}
                    </div>
                )}
            </div>

            <div className="nav-links">
                <Link to="/">Home</Link>
                <Link to="/login">Login</Link>
                <Link to="/register">Register</Link>
            </div>
        </nav>
    );
}

export default Navbar;
