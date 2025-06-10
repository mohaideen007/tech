import React, { useState } from "react";
import axios from "axios";
import "./ResourceViewer.css";

function ResourceViewer() {
  const [subject, setSubject] = useState("");
  const [resources, setResources] = useState([]);

  const fetchResources = async () => {
    if (!subject.trim()) return;
    try {
      const response = await axios.get(`http://localhost:7000/homework/getresources/${subject}`,
        {
          headers:{
            Authorization:`Bearer ${localStorage.getItem("token")}`
          }
        }
      );
      setResources(response.data);
    } catch (error) {
      console.error("Error fetching resources:", error);
      setResources([]);
    }
  };

  const isPDF = (url) => url.toLowerCase().endsWith(".pdf");

  return (
    <div className="container">
      <h1 className="title">View Resources by Subject</h1>
      <input
        type="text"
        placeholder="Enter Subject"
        value={subject}
        onChange={(e) => setSubject(e.target.value)}
        onKeyDown={(e) => e.key === "Enter" && fetchResources()}
        className="subject-input"
      />
      <div className="resources">
       {resources.map((res, index) => {
  const fileUrl = res.startsWith("http") ? res : `http://localhost:7000/${res}`;

  return (
    <div key={index} className="card">
      {isPDF(res) ? (
        <a
          href={fileUrl}
          target="_blank"
          rel="noopener noreferrer"
          type="application/pdf"
          className="button"
        >
          📄 View PDF
        </a>
      ) : (
        <>
          <img src={fileUrl} alt={`resource-${index}`} className="image" />
          <a href={fileUrl} target="_blank" rel="noopener noreferrer" className="button">
            🖼️ View Image
          </a>
        </>
      )}
    </div>
  );
})}


      </div>
    </div>
  );
}

export default ResourceViewer;
