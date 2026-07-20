import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

function SuccessFiles() {
  const [files, setFiles] = useState([]);
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();

  useEffect(() => {
    setLoading(true);
    fetch("http://localhost:8080/upload-document-0.0.1-SNAPSHOT/api/successFile")
      .then((response) => {
        if (!response.ok) {
          console.error("Failed to fetch success files", response.status);
          setFiles([]);
          return null;
        }
        return response.json();
      })
      .then((data) => {
        if (!data) return;
        if (Array.isArray(data)) setFiles(data);
        else if (Array.isArray(data.successFiles)) setFiles(data.successFiles);
        else setFiles([]);
      })
      .catch((err) => {
        console.error(err);
        setFiles([]);
      })
      .finally(() => setLoading(false));
  }, []);

  return (
    <div>
      <div className="app-header">
        <h1>✅ Success Files</h1>
        <p>Files that were processed successfully</p>
      </div>

      <div className="container">
        <div className="card">
          {loading ? (
            <p style={{ textAlign: "center", padding: "2rem" }}>Loading files...</p>
          ) : files.length === 0 ? (
            <div style={{ textAlign: "center", padding: "2rem" }}>
              <div style={{ fontSize: "3rem" }}>📭</div>
              <p style={{ marginTop: "1rem", color: "var(--text-light)" }}>
                No success files yet. Upload some files to get started!
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
                {files.length} file(s) processed successfully
              </p>
              <ul className="file-list">
                {files.map((file, index) => (
                  <li key={index} className="list-item">
                    <div className="list-item-icon" style={{ background: "rgba(133, 167, 155, 0.1)", color: "var(--success)" }}>
                      📄
                    </div>
                    <div className="list-item-content">
                      <div className="list-item-title">{file}</div>
                      <div className="list-item-subtitle">CSV File</div>
                    </div>
                    <span className="badge badge-success">Success</span>
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

export default SuccessFiles;