import axios from "axios"
import { useState } from "react";

function Resources(){

    const[subject,setSubject]=useState("");
    const[description,setDescription]=useState("");
    const[form,setForm]=useState(null);
function ahandleresources(){
    const firm=new FormData();
    firm.append("file",form)
    axios.post(`http://localhost:7000/homework/addresources/${subject}`,firm,
        {
            params:{
                description:description
            },
            headers:{
                Authorization:`Bearer ${localStorage.getItem("token")}`
            }
        }
    )
    .then(r=>{
        alert("Resources Added Successfully");
        console.log(r.data);
    }
)
.catch(e=>{
    console.log(e);
})

}
    return(
        <>
        <div className="upload-container">
      <h2 className="title">Upload Resources</h2>
      <div className="form-group">
        <input
          type="text"
          value={subject}
          onChange={(e) => setSubject(e.target.value)}
          placeholder="Enter Subject"
          className="input-field"
        />
        <input
          type="text"
          value={description}
          onChange={(e) => setDescription(e.target.value)}
          placeholder="Enter Description"
          className="input-field"
        />
        <input
          type="file"
          onChange={(e) => setForm(e.target.files[0])}
          className="file-input"
        />

        <button onClick={ahandleresources} className="upload-button">Upload</button>
      </div>
    </div>
        </>
    );
}

export default Resources;