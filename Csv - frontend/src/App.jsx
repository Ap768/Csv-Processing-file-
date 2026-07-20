import { useState } from "react";
import { Routes, Route, Navigate, useNavigate } from "react-router-dom";
import FileList from "./FileList";
import SuccessFiles from "./SuccessFiles";
import FailureFiles from "./FailureFiles";

  function Home() {
    const navigate = useNavigate();
    const [files, setFiles] = useState([]);
    const [uploadResult, setUploadResult] = useState([]);
    const [loading, setLoading] = useState(false);

    const API_URL =
      "http://localhost:8080/upload-document-0.0.1-SNAPSHOT/api/documents";

    const handleFileChange = (e) => {
      setFiles(e.target.files);
    };

    const uploadFiles = async () => {
      if (!files.length) {
        alert("Please select files first");
        return;
      }

      setLoading(true);
      const formData = new FormData();

      for (let i = 0; i < files.length; i++) {
        formData.append("files", files[i]);
      }

      try {
        const response = await fetch(`${API_URL}/upload`, {
          method: "POST",
          body: formData,
        });

        if (!response.ok) {
          console.error("Upload failed", response.status);
          setUploadResult([]);
          alert("Upload failed. Please try again.");
          return;
        }

        const data = await response.json();
        if (Array.isArray(data)) setUploadResult(data);
        else if (Array.isArray(data.uploadResult)) setUploadResult(data.uploadResult);
        else if (Array.isArray(data.successFiles)) setUploadResult(data.successFiles);
        else setUploadResult([]);

        alert("Files uploaded successfully!");
      } catch (error) {
        console.error(error);
        alert("An error occurred during upload.");
      } finally {
        setLoading(false);
      }
    };

  return (
    <div>
      <div className="app-header">
        <h1>📄 CSV File Manager</h1>
        <p>Upload and manage your CSV files with ease</p>
      </div>

      <div className="container">
        <div className="card">
          <h2>Upload CSV Files</h2>
          <div className="upload-section">
            <div style={{ fontSize: "3rem" }}>📁</div>
            <p style={{ marginTop: "1rem", fontSize: "1.1rem" }}>
              Click to select or drag and drop your CSV files
            </p>
            <input
              type="file"
              multiple
              accept=".csv"
              onChange={handleFileChange}
              id="file-input"
            />
            <label htmlFor="file-input" className="file-input-label btn btn-primary">
              Choose Files
            </label>
            {files.length > 0 && (
              <p style={{ marginTop: "1rem", color: "black" }}>
                ✓ {files.length} file(s) selected
              </p>
            )}
          </div>

          <div style={{ marginTop: "1.5rem", display: "flex", gap: "1rem", justifyContent: "center", flexWrap: "wrap" }}>
            <button
              onClick={uploadFiles}
              className="btn btn-primary"
              disabled={loading}
            >
              {loading ? "Uploading..." : "⬆️ Upload Files"}
            </button>
          </div>
        </div>

        {uploadResult.length > 0 && (
          <div className="card">
            <h2>✅ Upload Results</h2>
            <ul className="file-list">
              {uploadResult.map((item, index) => (
                <li key={index} className="list-item">
                  <div className="list-item-icon" style={{ background: "rgba(16, 185, 129, 0.1)" }}>
                    📄
                  </div>
                  <div className="list-item-content">
                    <div className="list-item-title">{item.fileName}</div>
                    <div className="list-item-subtitle">{item.dateTime}</div>
                  </div>
                  <span className="badge badge-success">Success</span>
                </li>
              ))}
            </ul>
          </div>
        )}

        <div className="card">
          <h2>📊 Quick Navigation</h2>
          <div className="nav-buttons">
            <button onClick={() => navigate("/files")} className="nav-btn">
              📋 All Files
            </button>
            <button onClick={() => navigate("/success")} className="nav-btn">
              ✅ Success Files
            </button>
            <button onClick={() => navigate("/failure")} className="nav-btn">
              ❌ Failure Files
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}

function App() {

  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/files" element={<FileList />} />
      <Route path="/success" element={<SuccessFiles />} />
      <Route path="/failure" element={<FailureFiles />} />
      <Route path="*" element={<Navigate to="/" replace />} />
    </Routes>
  );
}

export default App;