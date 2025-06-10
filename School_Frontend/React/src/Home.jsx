import { useState } from "react";
import axios from "axios";
import "./HomePage.css";
import { Auth } from "./Utils/auth";
import { Link} from "react-router-dom";

function Home() {
  const role = Auth();

  const [date, setDate] = useState("");
  const [homework, setHomework] = useState([]);

  function fetchHomework() {
    if (!date) {
      alert("Please select a date");
      return;
    }

    axios.get(`http://localhost:7000/homework/getwork/${date}`,{
      headers:{
        Authorization:`Bearer ${localStorage.getItem("token")}`
      }
    })
      .then(response => {
        setHomework(response.data);
      })
      .catch(error => {
        console.error("Error fetching homework:", error);
        alert("Failed to fetch homework");
      });
  };
 
  
  const [subject, setSubject] = useState("");
  const [description, setDescription] = useState("");
  const [deadline, setDeadline] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault(); // prevent form reload
    try {
      const response = await axios.post(
        "http://localhost:7000/homework/addhomework",
        {
          subject: subject,
          description: description,
          deadline: deadline,
        },
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`
          },
        }
      );
      console.log("Homework saved:", response.data);
      alert("Submitted");
    } catch (error) {
      console.error("Error saving homework:", error);
    }
  };

  // Conditional rendering based on role
  if (role === "USER") {
     return (
      <div className="upload-container">
        <h2 className="title">🏫 HILTON MATRIC HIGHER SECONDARY SCHOOL</h2>
        <p className="subtitle">Old Courtallam</p>

        <label className="label">
          📅 Select Date:
          <input
            type="date"
            value={date}
            onChange={(e) => setDate(e.target.value)}
            className="input-field"
          />
        </label>

        <div className="button-group">
          <button className="upload-button" onClick={fetchHomework}>
            🧾 GET HOMEWORK FOR DATE
          </button>
          <Link to={"/resourceview"}><button className="upload-button">
            GET RESOURCES
          </button></Link>
        </div>

        {homework.length > 0 && (
          <div className="homework-list">
            <h3>📌 Homework for {date}</h3>
            <ul>
              {homework.map((item, index) => (
                <li key={index}>
                  <strong>Subject:</strong> {item.subject}<br />
                  <strong>Description:</strong> {item.description}<br />
                  <strong>Deadline:</strong> {item.deadline}
                </li>
              ))}
            </ul>
          </div>
        )}

          
        
      </div>
     )
  
  }

  if (role === "TEACHER") {
    return (
      <div className="form-container">
        <form className="homework-form" onSubmit={handleSubmit}>
          <h2>Add Homework</h2>

          <label>
            Subject
            <input
              type="text"
              value={subject}
              onChange={(e) => setSubject(e.target.value)}
              required
              placeholder="e.g., Science"
            />
          </label>

          <label>
            Description
            <textarea
              value={description}
              onChange={(e) => setDescription(e.target.value)}
              required
              placeholder="Homework details..."
              rows="4"
            ></textarea>
          </label>

          <label>
            Deadline
            <input
              type="text"
              value={deadline}
              onChange={(e) => setDeadline(e.target.value)}
              required
            />
          </label>

          <button type="submit">Submit Homework</button>
          <Link to={"/addresources"}><button type="button">Add Resources</button>
          </Link>
        </form>
      </div>
    );
  }

  return null; 
}

export default Home;
