import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Register from './Register';
import './Register.css'
import Login from './Login'
import Home from './Home'
import Teacher from './Teacher';
import './Home.css'
import Resources from './Resources'
import'./Home.css'
import ResourceViewer from './ResourceViewer';
import'./Login.css'

const router = createBrowserRouter([
  {
    path:'/',
    element:<Register/>
  },
  {
    path:'/login',
    element:<Login/>
  },
  {
    path:'/home',
    element:<Home/>
  },{
    path:'/teacher',
    element:<Teacher/>
  },
  {
    path:'/addresources',
    element:<Resources/>
  },
  {
    path:'/resourceview',
    element:<ResourceViewer/>
  }
]);

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <RouterProvider router={router} />
  </StrictMode>,

)
