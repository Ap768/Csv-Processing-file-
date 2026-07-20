import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

function FileList() {
  const [fileList, setFileList] = useState([]);
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();

  useEffect(() => {
    setLoading(true);
    fetch("http://localhost:8080/upload-document-0.0.1-SNAPSHOT/api/documents/getFileList")
      .then((response) => {
        if (!response.ok) {
          setFileList([]);
          return null;
        }
        return response.json();
      })
      .then((data) => {
        if (!data) return;
        if (Array.isArray(data)) setFileList(data);
        else if (Array.isArray(data.fileList)) setFileList(data.fileList);
        else if (Array.isArray(data.files)) setFileList(data.files);
        else setFileList([]);
      })
      .catch((err) => {
        console.error(err);
        setFileList([]);
      })
      .finally(() => setLoading(false));
  }, []);

  const getBadgeStyle = (status) => {
    if (status === "Success") return { background: "#d1fae5", color: "#065f46" };
    if (status === "Error")   return { background: "#fee2e2", color: "#991b1b" };
    return                           { background: "#fef3c7", color: "#92400e" };
  };

  const getBadgeLabel = (status) => {
    if (status === "Success") return "✅ Success";
    if (status === "Error")   return "❌ Error";
    return "⏳ Pending";
  };

  return (
    <div>
      <div className="app-header">
        <h1>📋 All Uploaded Files</h1>
        <p>Complete list of all uploaded documents</p>
      </div>

      <div className="container">
        <div className="card">
          {loading ? (
            <p style={{ textAlign: "center", padding: "2rem" }}>Loading files...</p>
          ) : fileList.length === 0 ? (
            <div style={{ textAlign: "center", padding: "2rem" }}>
              <div style={{ fontSize: "3rem" }}>📁</div>
              <p style={{ marginTop: "1rem", color: "var(--text-light)" }}>
                No files uploaded yet. Start by uploading your first CSV file!
              </p>
              <button
                onClick={() => navigate("/")}
                className="btn btn-primary"
                style={{ marginTop: "1rem" }}
              >
                ⬆️ Upload Files
              </button>
            </div>
          ) : (
            <>
              <p style={{ marginBottom: "1.5rem", color: "var(--text-light)" }}>
                {fileList.length} file(s) found
              </p>
              <ul className="file-list">
                {fileList.map((item, index) => (
                  <li key={index} className="list-item">
                    <div
                      className="list-item-icon"
                      style={{ background: "rgba(102, 126, 234, 0.1)", color: "var(--primary)" }}
                    >
                      📄
                    </div>
                    <div className="list-item-content">
                      <div className="list-item-title">
                        {item.fileName || "Unnamed File"}
                      </div>
                      <div className="list-item-subtitle">
                        📅 {item.dateTime || "Date not available"}
                      </div>
                    </div>

                    {/* ✅ Dynamic badge */}
                    <span style={{
                      padding: "0.3rem 0.8rem",
                      borderRadius: "999px",
                      fontSize: "0.85rem",
                      fontWeight: "600",
                      ...getBadgeStyle(item.status)
                    }}>
                      {getBadgeLabel(item.status)}
                    </span>

                  </li>
                ))}
              </ul>
            </>
          )}

          <div style={{ marginTop: "2rem" }}>
            <button
              onClick={() => navigate("/")}
              className="nav-btn"
              style={{ background: "var(--primary)", color: "white" }}
            >
              ← Back to Home
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default FileList;