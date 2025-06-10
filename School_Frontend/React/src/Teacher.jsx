import axios from "axios";
import { useState } from "react";

function Teacher(){
    const[subject,setSubject]=useState("");
    const[description,setDescription]=useState("");
        const[deadline,setDeadline]=useState("");


    axios.post("http://localhost:8000/homework/addhomework",{
        subject:subject,
        description:description,
        deadline:deadline
    },{
        headers:{
            Authorization:`Bearer ${localStorage.getItem("token")}`
        }
    }
)
.then(r=>r.data);



    return(
        <>
        <div className="form-container">
      <form className="homework-form">
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

       <button onClick={handleSubmit}>Submit Homework</button>
      </form>
    </div>
    
        </>
    );
}


export default Teacher;