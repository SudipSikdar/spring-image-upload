import logo from './logo.svg';
import './App.css';
import React, {useState, useEffect, useCallback} from 'react';
import axios from 'axios';
import {useDropzone} from 'react-dropzone'

const UserProfiles = () => {

  const [userProfiles, setUserProfiles] = useState([]);

  const fetchUserProfiles = () => {

    axios.get("http://localhost:8080/api/v1/user-profile").then(res => {
      console.log(res);
      setUserProfiles(res.data);

    });
  }

  useEffect (() => {
    fetchUserProfiles();
  }, [])

  return userProfiles.map((userProfile, index) => {

    return (
      <div key={index}>
       {userProfile.userProfileId ? (
       <img alt='download'
          src= {`http://localhost:8080/api/v1/user-profile/${userProfile.userProfileId}/image/download`}
        />
       )
        : null}
          <br/>
          <br/>
          <h1>{userProfile.userName}</h1>
          <p1>{userProfile.userProfileId}</p1>
          <MyDropzone {...userProfile}/>
          <br/>
      </div>
    );
  });
}

function MyDropzone({userProfileId}) {
  const onDrop = useCallback(acceptedFiles => {
    // Do something with the files
    console.log("clicked")
    const file = acceptedFiles[0];
    const formData = new FormData();
    formData.append("file", file);
    axios.post(`http://localhost:8080/api/v1/user-profile/${userProfileId}/image/upload`,
    formData,
    {
      headers:{
        "Content-Type":"multipart/form-data"
      }
    }
    ).then( () => {
      console.log("file uploaded successfully")
    }).catch( err => {
      console.log(err);
    })
    console.log(file)
  }, [])
  const {getRootProps, getInputProps, isDragActive} = useDropzone({onDrop})

  return (
    <div {...getRootProps()}>
      <input {...getInputProps()} />
      {
        isDragActive ?
          <p>Drop the image here ...</p> :
          <p>Drag 'n' drop image here, or click to select image</p>
      }
    </div>
  )
}

function App() {
  return (
    <div className="App">
      <UserProfiles />
    </div>
  );
}

export default App;
