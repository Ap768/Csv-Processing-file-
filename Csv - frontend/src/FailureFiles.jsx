import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

function FailureFiles() {
  const [files, setFiles] = useState([]);
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();

  useEffect(() => {
    setLoading(true);
    fetch("http://localhost:8080/upload-document-0.0.1-SNAPSHOT/api/failureFile")
      .then((response) => {
        if (!response.ok) {
          console.error("Failed to fetch failure files", response.status);
          setFiles([]);
          return null;
        }
        return response.json();
      })
      .then((data) => {
        if (!data) return;
        if (Array.isArray(data)) setFiles(data);
        else if (Array.isArray(data.failureFiles)) setFiles(data.failureFiles);
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
        <h1>❌ Failure Files</h1>
        <p>Files that encountered errors during processing</p>
      </div>

      <div className="container">
        <div className="card">
          {loading ? (
            <p style={{ textAlign: "center", padding: "2rem" }}>Loading files...</p>
          ) : files.length === 0 ? (
            <div style={{ textAlign: "center", padding: "2rem" }}>
              <div style={{ fontSize: "3rem" }}>✅</div>
              <p style={{ marginTop: "1rem", color: "var(--text-light)" }}>
                Great! No failure files. All uploads were successful!
              </p>
              <button
                onClick={() => navigate("/")}
                className="btn btn-primary"
                style={{ marginTop: "1rem" }}
              >
                ⬆️ Upload More Files
              </button>
            </div>
          ) : (
            <>
              <p style={{ marginBottom: "1.5rem", color: "var(--text-light)" }}>
                {files.length} file(s) failed processing
              </p>
              <ul className="file-list">
                {files.map((file, index) => (
                  <li key={index} className="list-item">
                    <div className="list-item-icon" style={{ background: "rgba(239, 68, 68, 0.1)", color: "var(--danger)" }}>
                      ⚠️
                    </div>
                    <div className="list-item-content">
                      <div className="list-item-title">{file}</div>
                      <div className="list-item-subtitle">Processing error</div>
                    </div>
                    <span className="badge badge-danger">Failed</span>
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

export default FailureFiles;